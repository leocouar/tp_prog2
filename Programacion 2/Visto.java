import java.io.Serializable;

public class Visto implements Serializable{
    private Texto textoVisto;
    private Visto sigVisto;
    
    public void Visto(){
        this.textoVisto=null;
        this.sigVisto=null;
    }
}