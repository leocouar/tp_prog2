import java.util.Scanner;
import java.time.LocalDateTime;

class TexTok {
    public static void main(String[] args){
        Usuarios usuarios = new Usuarios();
        TextosMasVistos textosMasVistos = new TextosMasVistos();
        Scanner sc = new Scanner(System.in);
        
        //Carga de Archivos
        Archivos archivo = new Archivos();
        
        /*
        //Esto sirve para vaciar los Usuarios
        archivo.limpiarArchivo("Usuarios.ser");
        archivo.limpiarArchivo("Textos.ser");
        archivo.limpiarArchivo("Vistas.ser");
        */
        
        archivo.cargarUsuarios(usuarios);
        archivo.cargarTextos(usuarios,textosMasVistos);
        archivo.cargarVistos(usuarios,textosMasVistos);
        
        
        //MENU
        menu(usuarios,textosMasVistos,sc);
        
        //Guardado de archivos
        archivo.guardarUsuarios(usuarios);
        archivo.guardarTextos(usuarios);
        archivo.guardarVisto(usuarios, textosMasVistos);
        
    }
    
    private static void menu(Usuarios usuarios,TextosMasVistos textosMasVistos,Scanner sc) {
        int op = 0;
        Usuario usuario;
        
        while(op != 6) {
    
            System.out.println("===== MENU =====");
            System.out.println("----------------------------");
            System.out.println("1) Identificarse");
            System.out.println("2) Crear un usuario");
            System.out.println("3) Dar de baja a un usuario (promocion) ");
            System.out.println("4) Devolver los datos del usuario que más textos creó");
            System.out.println("5) Devolver los datos del usuario que creó los textos más visto (promocion)");
            System.out.println("6) Salir");
            System.out.println("----------------------------");
            System.out.print("Opción: ");
    
            op = leerEntero(sc);
    
            if (op == 1) {
                // Identificarse
                usuario = identificar(usuarios,sc);
                if(usuario != null){
                    segundoMenu(usuario,textosMasVistos,sc);
                }
            } 
            else if (op == 2) {
                // Crear un usuario
                crearUsuario(usuarios,sc);
            } 
            else if (op == 3) {
                // Dar de baja a un usuario
            } 
            else if (op == 4) {
                // Usuario que más textos creó
                BuscarUsuarioMasTextos(usuarios,sc);
            } 
            else if (op == 5) {
                // Usuario que creó los textos más vistos
            } 
            else if (op == 6) {
                System.out.println("Saliendo...");
            } 
            else {
                System.out.println("Opción incorrecta");
            }
    
            limpiar(); // salto de línea
        }
    }
    
    
    //identificar usuario, devuelve el usuario identificado
    private static Usuario identificar(Usuarios usuarios,Scanner sc){
        //limpio el buffer
        sc.nextLine();
        
        //Pido el usuario
        System.out.print("Ingrese su Nick de Usuario: ");
        String nick = leerTexto(sc);
        boolean identificado;
        
        //valido si existe
        Usuario usuarioIngresado = usuarios.buscarUsuario(nick);
        if (usuarioIngresado == null){
            //no se encontro usuario
            identificado = false;
            System.out.println("No se encontro Usuario con ese nick...");
            presionarEnter(sc);
        }else{
            //valido la clave
            System.out.print("Ingrese su clave: ");
            int clave = leerEntero(sc);
            
            boolean validarClave = usuarioIngresado.validarPass(clave);
            if (validarClave){
                identificado = true;
                System.out.println("Inicio de Sesion exitoso..");
            }else{
                identificado = false;
                System.out.println("Clava Incorrecta....");
            }
            //limpio el buffer
            sc.nextLine();
            presionarEnter(sc);
            
        }
        
        if(identificado){
            return usuarioIngresado;
        }else{
            return null;
        }
    }
    
    //Funcion principal para crear usuarios
    public static void crearUsuario(Usuarios usuarios, Scanner sc) {
        //limpio el buffer
        sc.nextLine();
        System.out.println("Ingrese nombre de nuevo usuario:");
        String nickNuevo = leerTexto(sc);
        
        if (!usuarios.verificarUsuario(nickNuevo)) {
            int clave, clave1, clave2;

            System.out.println("Ingrese la clave:");
            clave1 = leerEntero(sc);
            System.out.println("Ingrese deneuvo la clave:");
            clave2 = leerEntero(sc);
            sc.nextLine();
            if (clave1 == clave2) {
                clave = clave1;
                Usuario usuario = new Usuario(nickNuevo, clave);
                usuarios.insertarUsuario(usuario);
                System.out.println("Usuario creado exitosamente");
            } else {
                System.out.println("Las claves no coinciden");
            }
        } else {
            System.out.println("El nombre ya existe");
        }
        presionarEnter(sc);
    }
    
    private static void BuscarUsuarioMasTextos(Usuarios usuarios,Scanner sc){
        Usuario usuario = usuarios.UsuarioMasTextos();
        int cantidad = usuario.cantidadTextos();
        System.out.println("El Usuario con mas Textos es " + usuario.getNick() + ", con una cantidad de " + cantidad + " textos creados" );
        sc.nextLine();
        presionarEnter(sc);
    }
    
    private static void segundoMenu(Usuario usuario,TextosMasVistos textosMasVistos,Scanner sc){
        int op = 0;
        
        while(op != 4){
            System.out.println("===== MENÚ DE USUARIO =====");
            System.out.println("Usuario: " + usuario.getNick());
            System.out.println("----------------------------");
            System.out.println("1) Crear un texto");
            System.out.println("2) Visualizar un texto");
            System.out.println("3) Cambiar la password");
            System.out.println("4) Volver al menú anterior");
            System.out.println("----------------------------");
            System.out.print("Elija una opción: ");
            
            op = leerEntero(sc);
            
            if (op == 1) {
                CrearTexto(usuario,textosMasVistos,sc);
            }
            else if (op == 2) {
                verTexto(usuario,textosMasVistos,sc);
            }
            else if (op == 3) {
                cambiarPass(usuario,sc);
            }
            else if (op == 4) {
                System.out.println("Volver al menu principal...");
                presionarEnter(sc);
            } 
            else {
                System.out.println("Opción incorrecta");
                System.out.println("Volver al menu...");
                presionarEnter(sc);
            }
            limpiar();
        }
    }
    private static void CrearTexto(Usuario usuario,TextosMasVistos textosMasVistos,Scanner sc){
        sc.nextLine();
        
        System.out.println("Ingrese el Texto:");
        String contenido = leerTexto(sc);
        

        boolean existeContenido = usuario.verificarExist(contenido);
        if(!existeContenido){
            LocalDateTime ahora = LocalDateTime.now();
            Texto textoNuevo = new Texto(ahora,contenido,0);
            usuario.insertarTexto(textoNuevo);
            textosMasVistos.crearTexto(textoNuevo);
            System.out.println("Texto Creado exitosamente");
        }else{
            System.out.println("El Texto ya ah sido creado anteriormente");
        }
        

        
        presionarEnter(sc);
    }
    
    private static void verTexto(Usuario usuario, TextosMasVistos textosMasVistos, Scanner sc){
        sc.nextLine();
        Texto actual = textosMasVistos.getPrimero();
        Texto anterior = null;
        Texto encontrado = null;
        
        while (actual != null && encontrado == null){
            if(!usuario.fueVisto(actual) && !usuario.fueEscrito(actual)){
                
                System.out.println(actual.getTexto() + " ");
                actual.setVistas(actual.getVistas() + 1);
                System.out.println("Vistas: " + actual.getVistas() + " ");
                System.out.println(actual.getFecha());
                
                
                if(anterior != null && anterior.getVistas() < actual.getVistas()){
                    textosMasVistos.reordenarTexto(actual);
                }
                
                Visto nuevo = new Visto();
                nuevo.setTextoVisto(actual);
                usuario.insertarVisto(nuevo);
                encontrado = actual;
            } else {
                anterior = actual;
                actual = actual.getMenosVisto();
            }
        }
    
        if(actual == null && encontrado == null){
            System.out.println("Te viste todos los textos de la plataforma ");
        }
    
        presionarEnter(sc);
    }

    
    private static void cambiarPass(Usuario usuario,Scanner sc){
        sc.nextLine();
        
        System.out.println("Ingrese la clave antigua:");
        int claveAnt =leerEntero(sc);
        if(usuario.validarPass(claveAnt)){
            System.out.println("Ingrese la clave nueva:");
            int claveNueva =leerEntero(sc);
            usuario.setPass(claveNueva);
            System.out.println("Clave cambiada exitosamente");
            sc.nextLine();
            presionarEnter(sc);
        }else{
            System.out.println("La clave no es correcta");
            sc.nextLine();
            presionarEnter(sc);
        }
    }
    //SCANNER  
    private static void limpiar() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    private static void presionarEnter(Scanner sc) {
        System.out.println("Presiona ENTER para continuar...");
        sc.nextLine();
        limpiar();
    }
    
    private static int leerEntero(Scanner sc) {
        int valor = sc.nextInt();
        return valor;
    }

    private static String leerTexto(Scanner sc) {
        return sc.nextLine();
    }
}