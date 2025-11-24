import java.io.Serializable;
import java.time.LocalDateTime;

public class TextoSerial implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nick;
    private LocalDateTime fecha;
    private String texto;

    public TextoSerial(Texto texto, String nick) {
        this.nick = nick;
        this.fecha = texto.getFecha();
        this.texto = texto.getTexto();
    }
    public String getNick() {
        return nick;
    }
    public LocalDateTime getFecha() {
        return fecha;
    }
    public String getTexto() {
        return texto;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return "ArchivoTexto{" +
                "nick='" + nick + '\'' +
                ", fecha=" + fecha +
                ", texto='" + texto + '\'' +
                '}';
    }

    public Texto toTexto() {
        return new Texto(fecha, texto, 0);
    }
}
