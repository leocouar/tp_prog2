public class Usuarios{
    private Usuario raiz;
    
    public void Usuarios(){
        this.raiz = null;
    }
    
    public Usuario getRaiz(){
        return raiz;
    }
    
    public void cargarUsuarios(){}
    
    //Funcion principal para crear usuarios
    public void crearUsuario(){
        
    }
    public void mostrarOrdenado(){
        mostrarOrdenadoRec(raiz);
    }
    
    private void mostrarOrdenadoRec(Usuario actual){
        if (actual != null){
            mostrarOrdenadoRec(actual.getAnt());
            System.out.println(actual.getNick());
            mostrarOrdenadoRec(actual.getSig());
        }
    }
    
   //Primer llamado para la Insercion de Usuario
    public void insertarUsuario(Usuario nuevo){
        raiz = insertarUsuarioRec(raiz,nuevo);
    }
    
    //Inserta un Usuario de manera Recursiva
    private Usuario insertarUsuarioRec(Usuario actual,Usuario nuevo){
        
        if(actual == null){
            actual = nuevo;
            
        }else{
            int comparacion = nuevo.getNick().compareTo(actual.getNick());
            
            if(comparacion < 0){
                // nuevo.getNick es "menor" → va al anterior
                actual.setAnt(insertarUsuarioRec(actual.getAnt(),nuevo));
            }else if (comparacion > 0){
                // nuevo.nick es "mayor" → va al siguiente
                actual.setSig(insertarUsuarioRec(actual.getSig(),nuevo));
            }else {
                // comparacion == 0 → nicks iguales → NO insertar repetido
                System.out.println("nick repetido: " + nuevo.getNick());
            }
        }
        
        return actual;
    }
    
    //verifica si Existe el Usuario
    public boolean verificarUsuario(String nick){
        boolean resultado;
        Usuario usuario = buscarUsuarioRec(raiz, nick);
        if (usuario != null){
            resultado = true;
        }else{
            resultado = false;
        }
        return resultado;
    }
    
    public Usuario buscarUsuario(String nick){
        return buscarUsuarioRec(raiz, nick);
    }
    
    private Usuario buscarUsuarioRec(Usuario actual, String nick){
        if (actual == null) {
            return null; // no existe
        }
        
        //comparo con equals porque me daba error
        if (actual.getNick().equals(nick)) {
            return actual;
        }
    
        int comparacion = nick.compareTo(actual.getNick());
        if (comparacion < 0) {
            return buscarUsuarioRec(actual.getAnt(), nick);
        } else {
            return buscarUsuarioRec(actual.getSig(), nick);
        }
        //No hay condicion que no este contemplada, 
        //Si usaba una variable y un retono sigue ejecutandose incluso despues de encontrar el usuario
        //Por lo menos no puse un Break xD
    }
    
    public Usuario UsuarioMasTextos(){
        return buscarMasTextosRec(raiz);
    }
    
    private Usuario buscarMasTextosRec(Usuario actual){
        if (actual == null) {
            return null; // no existe
        }
        
        Usuario anterior = buscarMasTextosRec(actual.getAnt());
        Usuario siguiente = buscarMasTextosRec(actual.getSig());
        Usuario mayor = actual;
        
        if(anterior != null && anterior.cantidadTextos() > mayor.cantidadTextos()){
            mayor = anterior;
        }else if(siguiente != null && siguiente.cantidadTextos() > mayor.cantidadTextos()){
            mayor = siguiente;
        }
        return mayor;
    }
    
    

}