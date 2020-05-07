public class Elfo extends Personagem {

    protected int experiencia;  
    protected int ganhoExp;
    protected static int qtdElfosCriados;

    {
        experiencia = 0;
        ganhoExp = 1;
        QTD_DANO = 0;
        QTD_GANHO = 0;
        super.ganharItem(new Item("Arco", 1));
        super.ganharItem(new Item("Flecha", 7));
        qtdElfosCriados++;
    }

    public Elfo(String nomeInformado){
        super(nomeInformado, 100.0, Status.VIVO);
    }

    public String getNome(){
        return nome;
    }

    public int getExperiencia(){
        return experiencia;
    }

    public Item getFlecha(){
        return this.mochila.buscar("Flecha");
    }

    public void atirarFlecha(Dwarf anao){
        if(this.getFlecha().getQuantidade() > 0){
            this.getFlecha().setQuantidade(this.getFlecha().getQuantidade() - 1);
            experiencia += this.ganhoExp;
            anao.perderVida();
            this.perderVida();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        qtdElfosCriados--;
        super.finalize();
    }
}