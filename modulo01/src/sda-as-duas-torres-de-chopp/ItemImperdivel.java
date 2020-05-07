
public class ItemImperdivel extends Item
{

    public ItemImperdivel(String descricao, int quantidade){
        super(descricao, quantidade);
    }

    public void setQuantidade(int novaQuantidade){
        this.quantidade = novaQuantidade > 0 ? novaQuantidade : 1;
    }
}