public class Visto{
    private Texto textoVisto;
    private Visto sigVisto;
    
    public void Visto(){
        this.textoVisto=null;
        this.sigVisto=null;
    }
    public Texto getTextoVisto() {
        return textoVisto;
    }
    public void setTextoVisto(Texto textoVisto) {
        this.textoVisto = textoVisto;
    }
    public Visto getSigVisto() {
        return sigVisto;
    }
    public void setSigVisto(Visto sigVisto) {
        this.sigVisto = sigVisto;
    }
}