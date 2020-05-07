import java.util.ArrayList;
import java.util.Arrays;

public class ElfoVerde extends Elfo
{
    private final ArrayList<String> itensPermitidos = new ArrayList<>(Arrays.asList("Espada de aço valiriano", "Arco de Vidro", "Flecha de Vidro"));

    public ElfoVerde(String nome)
    {
        super(nome);
        ganhoExp *= 2;
    }

    protected void perderVida(){
        super.perderVida();
        if(this.getStatus() == Status.MORTO) ExercitoElfo.QUANTOS_MORRERAM++;
    }

    public void ganharItem(Item novoItem){
        if(!itensPermitidos.contains(novoItem.getDescricao())) return;

        this.mochila.adicionar(novoItem);
    }

    public void perderItem(Item item){
        if(!itensPermitidos.contains(item.getDescricao())) return;

        this.mochila.remover(item);
    }
}
