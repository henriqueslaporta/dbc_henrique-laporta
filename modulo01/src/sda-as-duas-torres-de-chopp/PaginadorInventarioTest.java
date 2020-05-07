import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class PaginadorInventarioTest
{
    @Test
    public void pularUmaPosicao(){
        Inventario inventario = new Inventario();
        PaginadorInventario paginador = new PaginadorInventario(inventario);

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

        paginador.pular(1);

        assertEquals(1 , paginador.getMarcador());
    }

    @Test
    public void pularCicoPosicoes(){
        Inventario inventario = new Inventario();
        PaginadorInventario paginador = new PaginadorInventario(inventario);

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

        paginador.pular(5);

        assertEquals(5 , paginador.getMarcador());
    }

    @Test
    public void limitarUmaPosicaoMarcadorEmZero(){
        Inventario inventario = new Inventario();
        PaginadorInventario paginador = new PaginadorInventario(inventario);

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

        ArrayList<Item> esperado = new ArrayList<>();
        esperado.add(madeira);

        assertEquals(esperado,  paginador.limitar(1));
    }

    @Test
    public void limitarAteFinalMarcadorMeio(){
        Inventario inventario = new Inventario();
        PaginadorInventario paginador = new PaginadorInventario(inventario);

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

        ArrayList<Item> esperado = new ArrayList<>();
        esperado.add(capa);
        esperado.add(pocao);
        esperado.add(escudo);

        paginador.pular(2);

        assertEquals(esperado,  paginador.limitar(3));
    }

    @Test
    public void limitarDepoisFinalEMarcadorMeio(){
        Inventario inventario = new Inventario();
        PaginadorInventario paginador = new PaginadorInventario(inventario);

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

        ArrayList<Item> esperado = new ArrayList<>();
        esperado.add(capa);
        esperado.add(pocao);
        esperado.add(escudo);

        paginador.pular(2);

        assertEquals(esperado,  paginador.limitar(4));
    }

    @Test
    public void pularTodaListaELimitarUmItem(){
        Inventario inventario = new Inventario();
        PaginadorInventario paginador = new PaginadorInventario(inventario);

        Item madeira = new Item("Madeira" , 2);


        inventario.adicionar(madeira);

        ArrayList<Item> esperado = new ArrayList<>();

        paginador.pular(1);

        assertEquals(esperado,  paginador.limitar(1));
    }
}
