import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DwarfTest
{
    private final double DELTA = 0.01;
    
    @Test
    public void dwarfPerde10DeViva(){
        Dwarf dwarf = new Dwarf("Bernardinho");
        dwarf.perderVida();
        assertEquals(100.0, dwarf.getVida(), DELTA);
    }
    
    @Test
    public void dwarfNasceComStatusVivo(){
        Dwarf dwarf = new Dwarf("Dwarf");
        assertEquals(Status.VIVO, dwarf.getStatus());
    }
    
    @Test
    public void dwarfVidaZeroComStatusMorto(){
        Dwarf dwarf = new Dwarf("Dwarf");
        dwarf.perderVida(); //1
        dwarf.perderVida(); //2
        dwarf.perderVida(); //3
        dwarf.perderVida(); //4
        dwarf.perderVida(); //5
        dwarf.perderVida(); //6
        dwarf.perderVida(); //7
        dwarf.perderVida(); //8
        dwarf.perderVida(); //9
        dwarf.perderVida(); //10
        dwarf.perderVida(); //11
        assertEquals(Status.MORTO, dwarf.getStatus());
        assertEquals(0.0, dwarf.getVida(), DELTA);
    }
    
    @Test
    public void dwarfSofre120DeDanoFicaComZeroVidaEMorto(){
        Dwarf dwarf = new Dwarf("Dwarf");
        dwarf.perderVida(); //1
        dwarf.perderVida(); //2
        dwarf.perderVida(); //3
        dwarf.perderVida(); //4
        dwarf.perderVida(); //5
        dwarf.perderVida(); //6
        dwarf.perderVida(); //7
        dwarf.perderVida(); //8
        dwarf.perderVida(); //9
        dwarf.perderVida(); //10
        dwarf.perderVida(); //11
        dwarf.perderVida(); //12
        assertEquals(Status.MORTO, dwarf.getStatus());
        assertEquals(0.0, dwarf.getVida(), DELTA);
    }
}
