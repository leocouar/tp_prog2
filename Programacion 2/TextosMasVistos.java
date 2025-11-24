
/*
    textosMasVistos es el inicio de la lista (en el ejemplo se visualiza con flechas verdes)
    en la que figuran todos los textos ordenados siempre por las cantidades de vistas que tiene cada uno,
    de mayor cantidad a menor cantidad.
*/

public class TextosMasVistos{
    private Texto primero; // utilizamos el puntero menosVisto para esta lista
    
    public TextosMasVistos(){
        this.primero=null;
    }
    
    public Texto getPrimero() {
        return primero;
    }
    
    public void setPrimero(Texto primero) {
        this.primero = primero;
    }
    
    //crear texto
    public void crearTexto(Texto nuevoTexto){
        primero = insertarTexto(nuevoTexto, primero);
    }
    //insertar texto
    private Texto insertarTexto(Texto nuevoTexto, Texto actual){
        Texto resultado = actual;
        
        if(actual == null || actual.getVistas() > nuevoTexto.getVistas()){
            nuevoTexto.setMenosVisto(actual);
            resultado = nuevoTexto;
        }else{
            actual.setMenosVisto(insertarTexto(nuevoTexto,actual.getMenosVisto()));
        }
        return resultado;
    }
    
    private void borrarTexto(Texto texto){
        Texto actual = primero;
        Texto anterior = null;
    
        // Buscar el nodo
        while (actual != null && actual != texto){
            anterior = actual;
            actual = actual.getMenosVisto();
        }
    
        // Si no lo encontró, no hacemos nada
        if (actual != null){
    
            // Caso 1: borrar el primero
            if (anterior == null){
                primero = actual.getMenosVisto();
            }
    
            // Caso 2: borrar intermedio o último
            else {
                anterior.setMenosVisto(actual.getMenosVisto());
            }
        }
    }
    
    public void reordenarTexto(Texto texto){
        borrarTexto(texto);
        crearTexto(texto);
    }
}