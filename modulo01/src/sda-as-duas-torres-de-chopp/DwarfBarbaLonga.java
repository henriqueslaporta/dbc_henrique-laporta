
public class DwarfBarbaLonga extends Dwarf
{

    private Sorteador dado;
    
    public DwarfBarbaLonga(String nome, Sorteador sorteador)
    {
        super(nome);
        this.dado = sorteador;
    }

    protected void perderVida(){
        if(dado.sortear() <= 4){
            super.perderVida();
        }
    }

}
