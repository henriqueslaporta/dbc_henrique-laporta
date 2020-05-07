
public class EstatisticasInventario
{
    private Inventario inventario;

    public EstatisticasInventario(Inventario inventario){
        this.inventario = inventario;
    }

    public double calcularMedia(){
        if(inventario.getQuantidadeItens() == 0) return 0.0;

        double soma = 0.0;
        for(int i = 0; i < inventario.getQuantidadeItens(); i++){
            soma += inventario.obter(i).getQuantidade();
        }
        return soma / inventario.getQuantidadeItens();
    }

    public double calcularMediana(){
        if(inventario.getQuantidadeItens() == 0) 
            return 0.0;
        if(inventario.getQuantidadeItens() == 1) 
            return inventario.obter(0).getQuantidade();

        inventario.ordenarItens();
        int meio = inventario.getQuantidadeItens() / 2;
        //Caso numero PAR de itens
        if(inventario.getQuantidadeItens() % 2 == 0){
            return (inventario.obter(meio - 1).getQuantidade() + inventario.obter(meio).getQuantidade()) / 2.0;
        }
        //Caso numero IMPAR de itens
        return inventario.obter(meio).getQuantidade();
    }

    public int qtdItensAcimaDaMedia(){
        int acimaMedia = 0;
        double media = this.calcularMedia();

        for(int i = 0; i < inventario.getQuantidadeItens(); i++){
            if(inventario.obter(i).getQuantidade() > media)
                acimaMedia++;
        }

        return acimaMedia;
    }
}
