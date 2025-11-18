import java.io.Serializable;

public class Usuario implements Serializable {

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
    
    
}
