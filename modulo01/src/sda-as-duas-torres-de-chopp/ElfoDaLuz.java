import java.util.*;

public class ElfoDaLuz extends Elfo
{
    private static final ArrayList<String> ITENS_OBRIGATORIOS = new ArrayList<>(Arrays.asList("Espada de Galvorn"));

    public ElfoDaLuz(String nome)
    {
        super(nome);
        QTD_DANO = 21.0;
        QTD_GANHO = 10.;
        super.ganharItem(new ItemImperdivel("Espada de Galvorn", 1));
    }

    protected void atacarComEspada(Dwarf dwarf){
        experiencia += this.ganhoExp;
        dwarf.perderVida();
        if(++qtdAtaques % 2 == 0)
            this.ganharVida();                
        else
            this.perderVida();
    }

    protected void perderItem(Item item){
        if(ITENS_OBRIGATORIOS.contains(item.getDescricao())) return;
        super.perderItem(item);
    }
}
