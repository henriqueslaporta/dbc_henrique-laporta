import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ElfoVerdeTest
{
    @After
    public void tearDown(){
        System.gc();
        System.runFinalization();
    }
    
    @Test
    public void legolasAtiraUmaFlecha(){
        ElfoVerde legolas = new ElfoVerde("Legolas");
        legolas.atirarFlecha(new Dwarf("Mickey"));
        assertEquals(6, legolas.getFlecha().getQuantidade());
        assertEquals(2, legolas.getExperiencia());
    }

    @Test
    public void legolasAtira8Flechas(){
        ElfoVerde legolas = new ElfoVerde("Legolas");
        legolas.atirarFlecha(new Dwarf("Mickey")); //1
        legolas.atirarFlecha(new Dwarf("Mickey")); //2
        legolas.atirarFlecha(new Dwarf("Mickey")); //3
        legolas.atirarFlecha(new Dwarf("Mickey")); //4
        legolas.atirarFlecha(new Dwarf("Mickey")); //5
        legolas.atirarFlecha(new Dwarf("Mickey")); //6
        legolas.atirarFlecha(new Dwarf("Mickey")); //7
        legolas.atirarFlecha(new Dwarf("Mickey")); //8
        assertEquals(0, legolas.getFlecha().getQuantidade());
        assertEquals(14, legolas.getExperiencia());
    }

    @Test
    public void ganharUmArcoDeVidro(){
        ElfoVerde legolas = new ElfoVerde("Legolas");
        legolas.ganharItem(new Item("Arco de Vidro", 1));
        Inventario inventario = legolas.getInventario();
        assertEquals(new Item("Arco de Vidro", 1), inventario.buscar(new Item("Arco de Vidro", 1)));
    }

    @Test
    public void ganharUmaBota(){
        ElfoVerde legolas = new ElfoVerde("Legolas");
        legolas.ganharItem(new Item("Bota", 1));
        Inventario inventario = legolas.getInventario();
        assertNull(inventario.buscar(new Item("Arco de Vidro", 1)));
    }

    @Test
    public void perderUmArcoDeVidro(){
        ElfoVerde legolas = new ElfoVerde("Legolas");
        legolas.ganharItem(new Item("Arco de Vidro", 1));
        Inventario inventario = legolas.getInventario();
        legolas.perderItem(new Item("Arco de Vidro", 1));
        assertNull(inventario.buscar(new Item("Arco de Vidro", 1)));
    }

    @Test
    public void perderUmaBota(){
        ElfoVerde legolas = new ElfoVerde("Legolas");
        legolas.perderItem(new Item("Bota", 1));
        Inventario inventario = legolas.getInventario();
        assertNull(inventario.buscar(new Item("Bota", 1)));
    }

}
