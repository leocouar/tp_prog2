import java.time.LocalDateTime;

public class Texto {
    private LocalDateTime fecha;
    private String texto;
    private int vistas;
    private Texto siguiente;
    private Texto menosVisto;
    
    public Texto(LocalDateTime fecha, String texto, int vistas) {
        this.fecha = fecha;
        this.texto = texto;
        this.vistas = vistas;
        this.siguiente = null;
        this.menosVisto = null;
    }

    // GETTERS
    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getTexto() {
        return texto;
    }

    public int getVistas() {
        return vistas;
    }

    public Texto getMenosVisto() {
        return menosVisto;
    }
    public Texto getSiguiente(){
        return siguiente;
    }

    // SETTERS
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setVistas(int vistas) {
        this.vistas = vistas;
    }

    public void setMenosVisto(Texto menosVisto) {
        this.menosVisto = menosVisto;
    }
    
    public void setSiguiente(Texto siguiente){
        this.siguiente = siguiente;
    }

}
