import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EstatisticasInventarioTest
{
    private final double DELTA = 0.01;

    @Test
    public void calculaMediaQuatroItens(){
        Inventario inventario = new Inventario(4);
        EstatisticasInventario estatisticas = new EstatisticasInventario(inventario);
        Item madeira = new Item("Madeira" , 2);
        Item espada = new Item("Espada" , 1);
        Item capa = new Item("Capa" , 2);
        Item escudo = new Item("Escudo" , 1);

        inventario.adicionar(madeira);
        inventario.adicionar(espada);
        inventario.adicionar(capa);
        inventario.adicionar(escudo);

        assertEquals(1.5 , estatisticas.calcularMedia(), DELTA);
    }

    @Test
    public void calculaMediaUmItem(){
        Inventario inventario = new Inventario(1);
        EstatisticasInventario estatisticas = new EstatisticasInventario(inventario);
        Item madeira = new Item("Madeira" , 2);

        inventario.adicionar(madeira);

        assertEquals(2 , estatisticas.calcularMedia(), DELTA);
    }

    @Test
    public void calculaMediaSemItens(){
        Inventario inventario = new Inventario(1);
        EstatisticasInventario estatisticas = new EstatisticasInventario(inventario);

        assertEquals(0 , estatisticas.calcularMedia(), DELTA);
    }

    @Test
    public void calculaMedianaNumeroParDeItens(){
        Inventario inventario = new Inventario(4);
        EstatisticasInventario estatisticas = new EstatisticasInventario(inventario);
        Item madeira = new Item("Madeira" , 2);
        Item espada = new Item("Espada" , 1);
        Item capa = new Item("Capa" , 2);
        Item escudo = new Item("Escudo" , 1);

        inventario.adicionar(madeira);
        inventario.adicionar(espada);
        inventario.adicionar(capa);
        inventario.adicionar(escudo);

        assertEquals(1.5 , estatisticas.calcularMediana(), DELTA);
    }

    @Test
    public void calculaMedianaNumeroImparDeItens(){
        Inventario inventario = new Inventario(4);
        EstatisticasInventario estatisticas = new EstatisticasInventario(inventario);
        Item madeira = new Item("Madeira" , 3);
        Item espada = new Item("Espada" , 1);
        Item pocao = new Item("Poção" , 7);
        Item capa = new Item("Capa" , 2);
        Item escudo = new Item("Escudo" , 1);

        inventario.adicionar(madeira);
        inventario.adicionar(espada);
        inventario.adicionar(pocao);
        inventario.adicionar(capa);
        inventario.adicionar(escudo);

        assertEquals(2 , estatisticas.calcularMediana(), DELTA);
    }

    @Test
    public void calculaMedianaUmItem(){
        Inventario inventario = new Inventario();
        EstatisticasInventario estatisticas = new EstatisticasInventario(inventario);
        Item espada = new Item("Espada" , 1);

        inventario.adicionar(espada);

        assertEquals(1 , estatisticas.calcularMediana(), DELTA);
    }

    @Test
    public void calculaMedianaNumeroZeroItens(){
        Inventario inventario = new Inventario();
        EstatisticasInventario estatisticas = new EstatisticasInventario(inventario);

        assertEquals(0 , estatisticas.calcularMediana(), DELTA);
    }

    @Test
    public void qtdItensAcimaDaMediaVarios(){
        Inventario inventario = new Inventario();
        EstatisticasInventario estatisticas = new EstatisticasInventario(inventario);

        Item madeira = new Item("Madeira" , 2);
        Item espada = new Item("Espada" , 1);
        Item capa = new Item("Capa" , 2);
        Item pocao = new Item("Poção" , 2);
        Item escudo = new Item("Escudo" , 1);

        inventario.adicionar(madeira);
        inventario.adicionar(espada);
        inventario.adicionar(capa);
        inventario.adicionar(pocao);
        inventario.adicionar(escudo);

        assertEquals(3 , estatisticas.qtdItensAcimaDaMedia());
    }

    @Test
    public void qtdItensAcimaDaMediaUmItem(){
        Inventario inventario = new Inventario();
        EstatisticasInventario estatisticas = new EstatisticasInventario(inventario);

        Item escudo = new Item("Escudo" , 1);

        inventario.adicionar(escudo);

        assertEquals(0 , estatisticas.qtdItensAcimaDaMedia());
    }

    @Test
    public void qtdItensAcimaDaMediaZeroItens(){
        Inventario inventario = new Inventario();
        EstatisticasInventario estatisticas = new EstatisticasInventario(inventario);

        assertEquals(0 , estatisticas.qtdItensAcimaDaMedia());
    }
}
