public abstract class Personagem
{
    protected String nome;
    protected double vida;
    protected Status status;
    protected double QTD_DANO;
    protected static double QTD_GANHO;
    protected Inventario mochila;
    protected int qtdAtaques;

    protected Personagem(String nome, double vida, Status status) {
        this.nome = nome;
        this.vida = vida;
        this.status = status;
        this.qtdAtaques = 0;
        this.mochila = new Inventario();
    }

    public String getNome(){
        return this.nome;
    }

    public double getVida(){
        return this.vida;
    }

    public Status getStatus(){
        return this.status;
    }

    protected void setVida(double vida){
        this.vida = vida;
    }

    protected void setStatus(Status status){
        this.status = status;
    }

    public void ganharItem(Item novoItem){
        this.mochila.adicionar(novoItem);
    }

    protected void perderItem(Item item){
        this.mochila.remover(item);
    }

    public Inventario getInventario(){
        return this.mochila;
    }

    protected void perderVida(){
        double newVida = (this.getVida() - QTD_DANO > 0) ? this.getVida() - QTD_DANO : 0;
        this.setVida(newVida);
        if(newVida == 0) this.setStatus(Status.MORTO);
    }

    protected void ganharVida(){
        if(this.getStatus() == Status.MORTO) return;
        this.setVida(this.getVida() + QTD_GANHO);
    }

    public boolean equals(Object outroPersonagem){
        Personagem outro = (Personagem) outroPersonagem;
        boolean iguais = this.nome.equals(outro.getNome())
            && this.vida == outro.getVida()
            && this.status == outro.getStatus();            
        return iguais;
    }
}
