public class Item {
    protected String descricao;
    protected int quantidade;
    
    public Item (String descricao, int quantidade){
        this.descricao = descricao;
        this.setQuantidade(quantidade);
    }
    
    public String getDescricao(){
        return this.descricao;
    }
    
    public int getQuantidade(){
        return this.quantidade;
    }
    
    public void setQuantidade(int novaQuantidade){
        this.quantidade = novaQuantidade;
    }
    
    public boolean equals(Object outroItem){
        Item outro = (Item) outroItem;
        boolean iguais = this.descricao.equals(outro.getDescricao())
                        && this.quantidade == outro.getQuantidade();            
        return iguais;
    }
}