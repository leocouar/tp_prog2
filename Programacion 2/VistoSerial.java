import java.io.Serializable;

public class VistoSerial implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nickCreador;
    private String texto;
    private String nick;

    public VistoSerial(String nickCreador, String texto, String nick) {
        this.nickCreador = nickCreador;
        this.texto = texto;
        this.nick = nick;
    }
    public String getNickCreador() {
        return nickCreador;
    }
    public String getTexto() {
        return texto;
    }
    public String getNick() {
        return nick;
    }
    
    public void setNickCreador(String nickCreador) {
        this.nickCreador = nickCreador;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }
    public void setNick(String nick) {
        this.nick = nick;
    }

    @Override
    public String toString() {
        return "VistoSerial{" +
                "nickCreador='" + nickCreador + '\'' +
                ", texto='" + texto + '\'' +
                ", nick='" + nick + '\'' +
                '}';
    }

}
