import java.io.Serializable;

public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nick;
    private int pass;
    private Texto textos;
    private Visto visto;
    private Usuario ant;
    private Usuario sig;

    public Usuario(String nick, int pass) {
        this.nick = nick;
        this.pass = pass;
        this.textos = null;
        this.visto = null;
        this.ant = null;
        this.sig = null;
    }
    
    // GETTERS

    public String getNick() {
        return nick;
    }
    
    public int getPass() {
        return pass;
    }

    public Texto getTextos() {
        return textos;
    }
    
    public Visto getVisto() {
        return visto;
    }
    
    public Usuario getAnt() {
        return ant;
    }

    public Usuario getSig() {
        return sig;
    }
    
    public void setNick(String nick) {
        this.nick = nick;
    }
    
    // SETTERS
    public void setPass(int pass) {
        this.pass = pass;
    }

    public void setTextos(Texto textos) {
        this.textos = textos;
    }

    public void setVisto(Visto visto) {
        this.visto = visto;
    }

    public void setAnt(Usuario ant) {
        this.ant = ant;
    }

    public void setSig(Usuario sig) {
        this.sig = sig;
    }
    
    public boolean validarPass(int passIngresada){
        boolean validacion;
        if (passIngresada == this.pass){
            validacion = true;
        }else{
            validacion = false;
        }
        return validacion;
    }
    
    //Lista DE VISTOS POR EL USUARIO
    //insertar nuevo en vistos
    public void insertarVisto(Visto nuevo){
        nuevo.setSigVisto(visto);
        this.visto = nuevo;
    }
    
    public boolean fueVisto(Texto texto){
        Visto actual = visto;
        boolean valido = false;
    
        while(actual != null && !valido){
            if(actual.getTextoVisto().equals(texto)){
                valido = true;
            } else {
                actual = actual.getSigVisto();
            }
        }
    
        return valido;
    }
    
    @Override
    public String toString() {
        return "Usuario{nick='" + nick + "', pass=" + pass + "}";
    }
    
    //LISTA DE TEXTOS CREADOS POR EL USUARIO
    public void insertarTexto(Texto nuevo){
        textos = insertarTextoRec(textos,nuevo);
    }
    
    private Texto insertarTextoRec(Texto actual,Texto nuevo){
        Texto resultado = actual;
        
        if (actual == null || actual.getFecha().isBefore(nuevo.getFecha())){
            nuevo.setSiguiente(actual);
            resultado = nuevo;
        }else{
            actual.setSiguiente(insertarTextoRec(actual.getSiguiente(),nuevo));
        }
        return resultado;
    }
    
    public boolean verificarExist(String cadena){
        boolean valido = false;
        Texto actual = textos;
        while(actual != null && !valido){
            if(actual.getTexto().equals(cadena)){
                valido = true;
            }else{
                actual= actual.getSiguiente();
            }
        }
        return valido;
    }
    
    public boolean fueEscrito(Texto texto){
        boolean valido = false;
        Texto actual = textos;
        while(actual != null && !valido){
            //Utilizo equals porque comparo objetos
            if(actual.equals(texto)){
                valido = true;
            }else{
                actual = actual.getSiguiente();
            }
            
        }
        return valido;
    }
    
    public int cantidadTextos(){
        Texto actual = textos;
        int cantidad = 0;
    
        while (actual != null){
            cantidad++;
            actual = actual.getSiguiente();
        }
    
        return cantidad;
    }
    
    public Texto BuscarTextoUser(String textoBuscado){
        Texto actual = textos;
        Texto retorno = null;
        while(actual != null && !actual.getTexto().equals(textoBuscado)){
            actual.getSiguiente();
        }
        
        if (actual != null){
            retorno = actual;
        }
        return retorno;
    }

    
}
