import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class InventarioTest
{
    @Test
    public void adicionarUmArcoEObterArcoDoInventario(){
        Inventario inventario = new Inventario();
        Item arco = new Item("Arco", 1);
        inventario.adicionar(arco);
        Item itemObtido = inventario.obter(0);
        assertEquals(arco, itemObtido);
    }

    @Test
    public void adicinarDoisItensNoInventarioDeUmaPosicao(){
        Inventario inventario = new Inventario(1);
        Item arco = new Item("Arco", 1);
        Item flechas = new Item("Flechas", 1);

        inventario.adicionar(arco);
        inventario.adicionar(flechas);

        Item itemObtido = inventario.obter(0);
        assertEquals(arco, itemObtido);
    }

    @Test
    public void removerArcoDoInventario(){
        Inventario inventario = new Inventario();
        Item arco = new Item("Arco", 1);

        inventario.adicionar(arco);
        inventario.remover(0);

        Item itemObtido = inventario.obter(0);
        assertNull(itemObtido);
    }

    @Test
    public void removerItemDoInventario(){
        Inventario inventario = new Inventario();
        Item arco = new Item("Arco", 1);
        Item escudo = new Item("Escudo", 1);
        Item espada = new Item("Espada", 1);

        inventario.adicionar(arco);
        inventario.adicionar(escudo);
        inventario.adicionar(espada);
        inventario.remover(escudo);

        assertNull(inventario.buscar(escudo));
    }
    
    @Test
    public void removerItemQueNaoExisteDoInventario(){
        Inventario inventario = new Inventario();
        Item arco = new Item("Arco", 1);
        Item escudo = new Item("Escudo", 1);
        Item espada = new Item("Espada", 1);

        inventario.adicionar(arco);
        inventario.adicionar(espada);
        
        inventario.remover(escudo);

        assertNull(inventario.buscar(escudo));
    }
    
    @Test
    public void obterNomesDosItensNoInventarioCom1(){
        Inventario inventario = new Inventario();
        Item arco = new Item("Arco", 1);

        inventario.adicionar(arco);
        String nomes = inventario.getDescricoesItens();

        assertEquals("Arco", nomes);
    }

    @Test
    public void obterNomesDosItensNoInventarioCom2(){
        Inventario inventario = new Inventario();
        Item arco = new Item("Arco", 1);
        Item flecha = new Item("Flecha", 1);

        inventario.adicionar(arco);
        inventario.adicionar(flecha);
        String nomes = inventario.getDescricoesItens();

        assertEquals("Arco,Flecha", nomes);
    }

    @Test
    public void obterNomesDosItensInventarioVazio(){
        Inventario inventario = new Inventario();

        String nomes = inventario.getDescricoesItens();

        assertEquals("", nomes);
    }

    @Test
    public void obterItemDeMaiorQuantidadeTodosDiferentes(){
        Inventario inventario = new Inventario();
        Item flecha = new Item("Flecha", 2);
        Item arco = new Item("Arco", 1);

        inventario.adicionar(flecha);
        inventario.adicionar(arco);

        Item maiorQuantiade = inventario.getItemComMaiorQuantidade();

        assertEquals(flecha, maiorQuantiade);
    }

    @Test
    public void obterItemDeMaiorQuantidadeTodosIguais(){
        Inventario inventario = new Inventario();
        Item flecha = new Item("Flecha", 2);
        Item arco = new Item("Arco", 2);

        inventario.adicionar(flecha);
        inventario.adicionar(arco);

        Item maiorQuantiade = inventario.getItemComMaiorQuantidade();

        assertEquals(flecha, maiorQuantiade);
    }

    @Test
    public void obterItemDeMaiorQuantidadeIventarioVazio(){
        Inventario inventario = new Inventario();
        Item maiorQuantiade = inventario.getItemComMaiorQuantidade();

        assertEquals(null, maiorQuantiade);
    }

    @Test
    public void buscarItemPelaStringExistente(){
        Inventario inventario = new Inventario();
        Item madeira = new Item("Madeira" , 20);
        Item espada = new Item("Espada" , 1);
        Item capa = new Item("Capa" , 2);

        inventario.adicionar(madeira);
        inventario.adicionar(espada);
        inventario.adicionar(capa);

        Item buscado = inventario.buscar("Espada");

        assertEquals(espada, buscado);
    }

    @Test
    public void buscarItemPelaStringQueNaoExiste(){
        Inventario inventario = new Inventario();
        Item madeira = new Item("Madeira" , 20);
        Item espada = new Item("Espada" , 1);
        Item capa = new Item("Capa" , 2);

        inventario.adicionar(madeira);
        inventario.adicionar(espada);
        inventario.adicionar(capa);

        Item buscado = inventario.buscar("Escudo");

        assertNull(buscado);
    }

    @Test
    public void buscarItemPelaStringInventarioVazio(){
        Inventario inventario = new Inventario();

        Item buscado = inventario.buscar("Escudo");

        assertNull(buscado);
    }

    @Test
    public void buscarItemExistente(){
        Inventario inventario = new Inventario();
        Item madeira = new Item("Madeira" , 20);
        Item espada = new Item("Espada" , 1);
        Item capa = new Item("Capa" , 2);

        inventario.adicionar(madeira);
        inventario.adicionar(espada);
        inventario.adicionar(capa);

        Item buscado = inventario.buscar(espada);

        assertEquals(espada, buscado);
    }

    @Test
    public void buscarItemQueNaoExiste(){
        Inventario inventario = new Inventario();
        Item madeira = new Item("Madeira" , 20);
        Item espada = new Item("Espada" , 1);
        Item capa = new Item("Capa" , 2);
        Item escudo = new Item("Escudo" , 1);

        inventario.adicionar(madeira);
        inventario.adicionar(espada);
        inventario.adicionar(capa);

        Item buscado = inventario.buscar(escudo);

        assertNull(buscado);
    }

    @Test
    public void buscarItemInventarioVazio(){
        Inventario inventario = new Inventario();
        Item espada = new Item("Espada" , 1);
        Item buscado = inventario.buscar(espada);

        assertNull(buscado);
    }

    @Test
    public void receberItensInvertidosNumeroItensImpar(){
        Inventario inventario = new Inventario();

        Item madeira = new Item("Madeira" , 20);
        Item espada = new Item("Espada" , 1);
        Item capa = new Item("Capa" , 2);

        inventario.adicionar(madeira);
        inventario.adicionar(espada);
        inventario.adicionar(capa);

        ArrayList<Item> invertido= inventario.inverter();

        assertEquals(capa,invertido.get(0));
        assertEquals(espada,invertido.get(1));
        assertEquals(madeira,invertido.get(2));
    }

    @Test
    public void receberItensInvertidosNumeroItensPar(){
        Inventario inventario = new Inventario();

        Item madeira = new Item("Madeira" , 20);
        Item espada = new Item("Espada" , 1);
        Item capa = new Item("Capa" , 2);
        Item escudo = new Item("Escudo" , 1);

        inventario.adicionar(madeira);
        inventario.adicionar(espada);
        inventario.adicionar(capa);
        inventario.adicionar(escudo);

        ArrayList<Item> invertido= inventario.inverter();

        assertEquals(escudo,invertido.get(0));
        assertEquals(capa,invertido.get(1));
        assertEquals(espada,invertido.get(2));
        assertEquals(madeira,invertido.get(3));
    }

    @Test
    public void receberItensInvertidosInventarioVazio(){
        Inventario inventario = new Inventario();

        ArrayList<Item> invertido= inventario.inverter();

        assertTrue(invertido.isEmpty());
    }

    @Test
    public void inverterNaoAlteraAListaOriginalDeItens(){
        Inventario inventario = new Inventario();

        Item madeira = new Item("Madeira" , 20);
        Item espada = new Item("Espada" , 1);
        Item capa = new Item("Capa" , 2);
        Item escudo = new Item("Escudo" , 1);

        inventario.adicionar(madeira);
        inventario.adicionar(espada);
        inventario.adicionar(capa);
        inventario.adicionar(escudo);

        ArrayList<Item> invertido= inventario.inverter();

        assertEquals("Madeira,Espada,Capa,Escudo",inventario.getDescricoesItens());
    }

    @Test
    public void ordenarEmOrdemAscendente(){
        Inventario inventario = new Inventario();

        Item madeira = new Item("Madeira" , 20);
        Item espada = new Item("Espada" , 1);
        Item capa = new Item("Capa" , 2);
        Item escudo = new Item("Escudo" , 1);

        inventario.adicionar(madeira);
        inventario.adicionar(espada);
        inventario.adicionar(capa);
        inventario.adicionar(escudo);

        inventario.ordenarItens();

        assertEquals("Espada,Escudo,Capa,Madeira",inventario.getDescricoesItens());
    }

    @Test
    public void ordenarTipoOrdenacaoASC(){
        Inventario inventario = new Inventario();

        Item madeira = new Item("Madeira" , 20);
        Item espada = new Item("Espada" , 1);
        Item capa = new Item("Capa" , 2);
        Item escudo = new Item("Escudo" , 1);

        inventario.adicionar(madeira);
        inventario.adicionar(espada);
        inventario.adicionar(capa);
        inventario.adicionar(escudo);

        inventario.ordenarItens(TipoOrdenacao.ASC);

        assertEquals("Espada,Escudo,Capa,Madeira",inventario.getDescricoesItens());
    }

    @Test
    public void ordenarTipoOrdenacaoDESC(){
        Inventario inventario = new Inventario();

        Item madeira = new Item("Madeira" , 20);
        Item espada = new Item("Espada" , 1);
        Item capa = new Item("Capa" , 2);
        Item escudo = new Item("Escudo" , 1);

        inventario.adicionar(madeira);
        inventario.adicionar(espada);
        inventario.adicionar(capa);
        inventario.adicionar(escudo);

        inventario.ordenarItens(TipoOrdenacao.DESC);

        assertEquals("Madeira,Capa,Espada,Escudo",inventario.getDescricoesItens());
    }

    @Test
    public void ordenarInventarioVazio(){
        Inventario inventario = new Inventario();

        inventario.ordenarItens();

        assertEquals("",inventario.getDescricoesItens());
    }

    @Test
    public void ordenarUmItem(){
        Inventario inventario = new Inventario();

        Item escudo = new Item("Escudo" , 1);

        inventario.adicionar(escudo);

        inventario.ordenarItens();

        assertEquals("Escudo",inventario.getDescricoesItens());
    }

    @Test
    public void ordenarInventarioVazioTipoASC(){
        Inventario inventario = new Inventario();

        inventario.ordenarItens(TipoOrdenacao.ASC);

        assertEquals("",inventario.getDescricoesItens());
    }

    @Test
    public void ordenarUmItemTipoOrdenacaoDESC(){
        Inventario inventario = new Inventario();

        Item escudo = new Item("Escudo" , 1);

        inventario.adicionar(escudo);

        inventario.ordenarItens(TipoOrdenacao.DESC);

        assertEquals("Escudo",inventario.getDescricoesItens());
    }
}

