import java.util.ArrayList;

public class PaginadorInventario
{
    private Inventario inventario;
    private int marcador = 0;
    
    public PaginadorInventario(Inventario inventario)
    {
        this.inventario = inventario;
    }
    
    public int getMarcador(){
        return marcador;
    }
    
    public void pular(int marcador){
        this.marcador = marcador;
    }
    
    public ArrayList<Item> limitar(int limite){
        ArrayList<Item> nItens = new ArrayList<>();
        
        for(int i = 0; i < limite && i + marcador < this.inventario.getQuantidadeItens(); i++){
            nItens.add(inventario.obter(marcador+i));
        }
        
        return nItens;
    }
}
