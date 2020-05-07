import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PersonagemTest
{
    @After
    public void tearDown(){
        System.gc();
        System.runFinalization();
    }
    
    @Test
    public void ganharUmItem(){
        Personagem personagem = new Elfo("Legolas");
        Item botas = new Item("Botas", 2);
        
        personagem.ganharItem(botas);
        
        Inventario inventario = personagem.getInventario();
        Item buscado = inventario.buscar(botas);
        boolean result = botas.equals(botas);
        assertTrue(result);
    }
    
    @Test
    public void ganharDoisItenERemoverUmItem(){
        Personagem personagem = new Elfo("Legolas");
        Item botas = new Item("Botas", 2);
        Item escudo = new Item("Escudo", 2);
        Item espada = new Item("Espada", 2);
        
        personagem.ganharItem(botas);
        personagem.ganharItem(espada);
        personagem.ganharItem(escudo);
        
        personagem.perderItem(espada);
        
        Inventario inventario = personagem.getInventario();
        
        assertNull(inventario.buscar(espada));
    }
}
