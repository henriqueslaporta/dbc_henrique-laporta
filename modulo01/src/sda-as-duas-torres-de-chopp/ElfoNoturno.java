public class ElfoNoturno extends Elfo
{

    public ElfoNoturno(String nome)
    {
        super(nome);
        ganhoExp *= 3;
        QTD_DANO = 15.0;
    }
    
    protected void perderVida(){
        super.perderVida();
        if(this.getStatus() == Status.MORTO) ExercitoElfo.QUANTOS_MORRERAM++;
    }
}
