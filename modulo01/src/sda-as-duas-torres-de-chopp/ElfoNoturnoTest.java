import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ElfoNoturnoTest
{
    private final double DELTA = 0.01;
    @After
    public void tearDown(){
        System.gc();
        System.runFinalization();
    }
    
    @Test
    public void elfoNoturnoAtiraUmaFlecha(){
        ElfoNoturno legolas = new ElfoNoturno("Legolas");
        legolas.atirarFlecha(new Dwarf("Mickey"));
        assertEquals(6, legolas.getFlecha().getQuantidade());
        assertEquals(3, legolas.getExperiencia());
        assertEquals(85, legolas.getVida(), DELTA);
    }

    @Test
    public void elfoNoturnoAtira8Flechas(){
        ElfoNoturno legolas = new ElfoNoturno("Legolas");
        
        legolas.atirarFlecha(new Dwarf("Mickey")); //1
        legolas.atirarFlecha(new Dwarf("Mickey")); //2
        legolas.atirarFlecha(new Dwarf("Mickey")); //3
        legolas.atirarFlecha(new Dwarf("Mickey")); //4
        legolas.atirarFlecha(new Dwarf("Mickey")); //5
        legolas.atirarFlecha(new Dwarf("Mickey")); //6
        legolas.atirarFlecha(new Dwarf("Mickey")); //7
        legolas.atirarFlecha(new Dwarf("Mickey")); //8
        
        assertEquals(0, legolas.getFlecha().getQuantidade());
        assertEquals(21, legolas.getExperiencia());
        assertEquals(0, legolas.getVida(), DELTA);
        assertEquals(Status.MORTO, legolas.getStatus());
    }
}
