import java.io.Serializable;
import java.time.LocalDateTime;

public class Texto implements Serializable {
    private LocalDateTime fecha;
    private String texto;
    private int vistas;
    private Texto siguiente;
    private Texto anterior;
    
    public Texto(LocalDateTime fecha, String texto, int vistas) {
        this.fecha = fecha;
        this.texto = texto;
        this.vistas = vistas;
        this.siguiente = null;
        this.anterior = null;
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

    public Texto getSiguiente() {
        return siguiente;
    }

    public Texto getAnterior() {
        return anterior;
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

    public void setSiguiente(Texto siguiente) {
        this.siguiente = siguiente;
    }

    public void setAnterior(Texto anterior) {
        this.anterior = anterior;
    }

}
