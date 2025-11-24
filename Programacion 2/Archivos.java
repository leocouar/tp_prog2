import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
//Output
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
//Input
import java.io.FileInputStream;
import java.io.ObjectInputStream;
public class Archivos{
    private static final long serialiVersionUID = 1L;
    private static final String RUTA_GENERAL = "/work";
    private static final String PREFIJO = "/LeoMaxi_";

    public Archivos(){
        verificacion("Usuarios.ser");
        verificacion("Textos.ser");
        verificacion("Vistas.ser");
        
    }
    private void verificacion(String nombre){
        String fullPath = RUTA_GENERAL + PREFIJO + nombre;
        if(verificarArchivo(fullPath)){
            System.out.println("Existe el Archivo en la direccion " + fullPath);
        }else{
            System.out.println("No se pudo crear el archivo en la dirreccion " + fullPath);
        }
    }
    //verificar/crear generico
    private boolean verificarArchivo(String rutaArchivo){
        boolean archivoExiste = Files.exists(Paths.get(rutaArchivo));
        if(!archivoExiste){
            //crear archivo
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))){
                bw.newLine();
                System.out.println("Archivos creados exitosamente.");
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return Files.exists(Paths.get(rutaArchivo));
    }
    
    //FUNCION DE LIMPIESA, No usar Pero es muy necesaria en el desarrollo
    public void limpiarArchivo(String archivo) {
        String fullPath = RUTA_GENERAL + PREFIJO + archivo;
        try (FileOutputStream fos = new FileOutputStream(fullPath)) {
            // archivo limpiado
            System.out.println(archivo +" limpiado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    // ===============================================
    //                  USUARIOS
    // ===============================================
    // ------------------------
    // GUARDAR USUARIOS
    // ------------------------
    public void guardarUsuarios(Usuarios usuarios){
        String fullPath = RUTA_GENERAL + PREFIJO + "Usuarios.ser";
    
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fullPath))) {
            guardarUsuariosRec(usuarios.getRaiz(), out);
            System.out.println("Usuarios guardados correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Recorrido IN-ORDER del árbol
    private void guardarUsuariosRec(Usuario actual, ObjectOutputStream out) throws IOException {
        if (actual != null) {
            guardarUsuariosRec(actual.getAnt(), out);
    
            // Escribimos una copia sin punteros (para evitar bucles)
            Usuario temp = new Usuario(actual.getNick(), actual.getPass());
            out.writeObject(temp);
    
            guardarUsuariosRec(actual.getSig(), out);
        }
    }
    
    // ------------------------
    // CARGAR USUARIOS
    // ------------------------
    public void cargarUsuarios(Usuarios usuarios){
        String fullPath = RUTA_GENERAL + PREFIJO + "Usuarios.ser";
        File archivo = new File(fullPath);
    
        // Si el archivo está vacío → no intentar leer nada
        if (!archivo.exists() || archivo.length() == 0) {
            System.out.println("Usuarios.ser vacío. No hay usuarios para cargar.");
            return;
        }
    
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fullPath))) {
    
            while (true) {
                Usuario user = (Usuario) in.readObject();
                usuarios.insertarUsuario(user); // insertamos en el árbol
            }
    
        } catch (EOFException eof) {
            // Fin del archivo → todo OK
            System.out.println("Usuarios cargados correctamente.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // ===============================================
    //                  TEXTOS
    // ===============================================
    
    // ------------------------
    // GUARDAR USUARIOS
    // ------------------------
    public void guardarTextos(Usuarios usuarios){
        String fullPath = RUTA_GENERAL + PREFIJO + "Textos.ser";
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fullPath))) {
            recorrerYGuardarTextos(usuarios.getRaiz(),out);
            System.out.println("Textos guardados exitosamente" );
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    private void recorrerYGuardarTextos(Usuario actual, ObjectOutputStream out) throws IOException {
        if (actual != null) {
            recorrerYGuardarTextos(actual.getAnt(), out);
            guardarTextosIn(actual, out);
            recorrerYGuardarTextos(actual.getSig(), out);
        }
    }
    
    private void guardarTextosIn(Usuario usuario,ObjectOutputStream out)throws IOException{
        Texto actual = usuario.getTextos();
        while (actual != null ){
            TextoSerial textonuevo = new TextoSerial(actual,usuario.getNick());
            out.writeObject(textonuevo);
            actual= actual.getSiguiente();
        }
    }

    // ------------------------
    // CARGAR USUARIOS
    // ------------------------    
    public void cargarTextos(Usuarios usuarios, TextosMasVistos textosMasVistos){
        String fullPath = RUTA_GENERAL + PREFIJO + "Textos.ser";
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fullPath))) {
            
            Usuario usuario = null;
    
            while (true) {
                TextoSerial textoserial = (TextoSerial) in.readObject();
    
                // Si cambia el usuario dueño del texto → lo busco en el árbol
                if (usuario == null || !usuario.getNick().equals(textoserial.getNick())) {
                    usuario = usuarios.buscarUsuario(textoserial.getNick());
                }
    
                Texto texto = textoserial.toTexto();
                usuario.insertarTexto(texto);           
                textosMasVistos.crearTexto(texto);
            }
    
        } catch (EOFException eo) {
            // Fin del archivo, correcto
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Clase TextoSerial no encontrada");
            e.printStackTrace();
        }
    }
    
    // ===============================================
    //                  VISTOS
    // ===============================================
    // ------------------------
    // GUARDAR VISTOS
    // ------------------------
    public void guardarVisto(Usuarios usuarios,TextosMasVistos textosMasVistos){
        String fullPath = RUTA_GENERAL + PREFIJO + "Vistas.ser";
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fullPath))){
            recorreYGuardarVistos(usuarios.getRaiz(),usuarios,textosMasVistos,out);
            System.out.println("Vistos guardados exitosamente" );
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    private void recorreYGuardarVistos(Usuario actual,Usuarios usuarios,TextosMasVistos textosMasVistos, ObjectOutputStream out) throws IOException {
        if (actual != null) {
            recorreYGuardarVistos(actual.getAnt(),usuarios,textosMasVistos, out);
            
            guardarVistosIn(actual,usuarios,textosMasVistos, out);
            
            recorreYGuardarVistos(actual.getSig(),usuarios,textosMasVistos, out);
        }
    }
    
    private void guardarVistosIn(Usuario usuario,Usuarios usuarios,TextosMasVistos textosMasVistos,ObjectOutputStream out) throws IOException {
        Texto actual = usuario.getTextos();
        while (actual != null ){
            machVisto(usuario,actual,usuarios,textosMasVistos,out);
            actual= actual.getSiguiente();
        }
    }
    private void machVisto(Usuario usuario,Texto texto,Usuarios usuarios,TextosMasVistos textosMasVistos,ObjectOutputStream out) throws IOException {
        String nombreCreador = usuario.getNick();
        buscarMachRec(usuarios.getRaiz(),texto,nombreCreador,textosMasVistos,out);
    }
    
    private void buscarMachRec(Usuario actual, Texto texto, String nombreCreador,TextosMasVistos textosMasVistos,ObjectOutputStream out) throws IOException {
        if(actual != null){
            buscarMachRec(actual.getAnt(),texto,nombreCreador,textosMasVistos,out);
            
                if(actual.fueVisto(texto)){
                    VistoSerial vistoSer = new VistoSerial(nombreCreador,texto.getTexto(),actual.getNick());
                    texto.setVistas(texto.getVistas() + 1);
                    textosMasVistos.reordenarTexto(texto);
                    out.writeObject(vistoSer);
                    
                }
                
            buscarMachRec(actual.getSig(),texto,nombreCreador,textosMasVistos,out);
        }
    }
    
    
    // ------------------------
    // CARGAR VISTOS
    // ------------------------
    public void cargarVistos(Usuarios usuarios, TextosMasVistos textosMasVistos){
        String fullPath = RUTA_GENERAL + PREFIJO + "Vistas.ser";
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fullPath))) {
            Usuario usuario = null;
            Texto texto = null;
            
            while(true){
                VistoSerial vistoSer = (VistoSerial) in.readObject();
                
                // Si cambia el usuario dueño del texto → lo busco en el árbol
                if (usuario == null || !usuario.getNick().equals(vistoSer.getNickCreador())) {
                    usuario = usuarios.buscarUsuario(vistoSer.getNickCreador());
                }
                
                if (texto == null || !texto.getTexto().equals(vistoSer.getTexto())){
                    texto = usuario.BuscarTextoUser(vistoSer.getTexto());
                }
                Visto visto = new Visto();
                
                visto.setTextoVisto(texto);
                Usuario espectador = usuarios.buscarUsuario(vistoSer.getNick());
                espectador.insertarVisto(visto);
                texto.setVistas(texto.getVistas() + 1);
                
            }

        } catch (EOFException eo) {
            // Fin del archivo, correcto
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Clase TextoSerial no encontrada");
            e.printStackTrace();
        }
    }
    
    
    
} 