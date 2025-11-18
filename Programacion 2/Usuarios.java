public class Usuarios{
    private Usuario raiz;
    
    public void Usuarios(){
        this.raiz = null;
    }
    
    public void cargarUsuarios(){}
    
    //Funcion principal para crear usuarios
    public void crearUsuario(){
        
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
                // nuevo.nick es "menor" → va al anterior
                actual.setAnt(insertarUsuarioRec(actual.getAnt(),nuevo));
            }else if (comparacion > 0){
                // nuevo.nick es "mayor" → va al siguiente
                actual.setSig(insertarUsuarioRec(actual.getSig(),nuevo));
            }else {
                // comparacion == 0 → nombres iguales → NO insertar repetido
                System.out.println("Nombre repetido: " + nuevo.getNick());
            }
        }
        
        return actual;
    }
    
    //verifica si Existe el Usuario
    public boolean verificarUsuario(String nick){
        return verificarUserRec(raiz,nick);
    }
    
    private boolean verificarUserRec(Usuario actual , String nick){
        boolean retorno = false;
        if(actual == null){
            retorno = false;
        }
        return retorno;
    }

}