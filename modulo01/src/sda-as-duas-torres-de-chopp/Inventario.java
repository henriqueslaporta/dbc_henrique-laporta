import java.util.ArrayList;

public class Inventario
{
    private ArrayList<Item> itens;

    public Inventario(int quantidadeItens){
        itens = new ArrayList<>(quantidadeItens);
    }

    public Inventario(){
        this(10);
    }

    public void adicionar(Item novoItem){
        itens.add(novoItem);
    }

    public ArrayList<Item> getItens(){
        return this.itens;
    }
    
    public Item obter(int posicao){
        if(itens.isEmpty() || posicao >= itens.size()){
            return null;
        }
        return itens.get(posicao);
    }

    public void remover(int posicao){
        itens.remove(posicao);
    }
    
    public void remover(Item item){
        itens.remove(item);
    }

    public int getQuantidadeItens(){
        return itens.size();
    }

    public String getDescricoesItens(){
        if(itens.isEmpty()){
            return "";
        }

        StringBuilder descricoes = new StringBuilder();
        descricoes.append(itens.get(0).getDescricao());

        for(int i = 1; i < itens.size(); i++){
            descricoes.append(",");
            descricoes.append(itens.get(i).getDescricao());
        }

        return descricoes.toString();
    }

    public Item getItemComMaiorQuantidade(){
        if(itens.isEmpty()) return null;
        Item maior = itens.get(0);
        for(int i = 1; i < itens.size(); i++){
            if(itens.get(i).getQuantidade() > maior.getQuantidade())
                maior = itens.get(i);
        }
        return maior;
    }

    public Item buscar(String busca){
        for(int i = 1; i < itens.size(); i++){
            Item itemAtual = itens.get(i);
            if(busca.equals(itemAtual.getDescricao()))
                return itemAtual;
        }
        return null;
    }
    
    public Item buscar(Item busca){
        for(int i = 1; i < itens.size(); i++){
            if(itens.get(i).equals(busca))
                return itens.get(i);
        }
        return null;
    }

    public ArrayList<Item> inverter(){
        if(itens.isEmpty()) return itens;
        ArrayList<Item> invertido = new ArrayList<>(itens.size());
        int itensSize = itens.size() - 1;
        for(int i = 0; i <= itensSize; i++){
            invertido.add(itens.get(itensSize-i));
        }
        return invertido;
    }

    public void ordenarItens(){
        this.ordenarItens(TipoOrdenacao.ASC);
    }

    public void ordenarItens(TipoOrdenacao ordem){
        if(itens.size() <= 1) return;

        Item anterior, proximo;
        boolean ordenado = false;
        boolean troca;
        
        while(!ordenado){
            troca = false;
            for(int i = 0; i < itens.size() - 1; i++){
                if(ordem == TipoOrdenacao.ASC){
                    if(itens.get(i).getQuantidade() > itens.get(i+1).getQuantidade()){
                        anterior = itens.get(i);
                        proximo = itens.get(i+1);
                        itens.set(i+1, anterior);
                        itens.set(i, proximo);
                        troca = true;
                    }
                }else{
                    if(itens.get(i).getQuantidade() < itens.get(i+1).getQuantidade()){
                        anterior = itens.get(i);
                        proximo = itens.get(i+1);
                        itens.set(i+1, anterior);
                        itens.set(i, proximo);
                        troca = true;
                    }
                }
            }
            if(troca == false) ordenado = true;

        }
    }
}
