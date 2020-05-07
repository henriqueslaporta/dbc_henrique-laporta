import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DwarfBarbaLongaTest
{
    private final double DELTA = 0.01; 

    @Test
    public void dwarfDevePerderVida1(){
        DadoFake dadoFake = new DadoFake();
        dadoFake.simularValor(1);
        Dwarf balin = new DwarfBarbaLonga("Balin", dadoFake);
        balin.perderVida();
        assertEquals(100.0, balin.getVida(), DELTA);
    }
    
        @Test
    public void dwarfDevePerderVida4(){
        DadoFake dadoFake = new DadoFake();
        dadoFake.simularValor(4);
        Dwarf balin = new DwarfBarbaLonga("Balin", dadoFake);
        balin.perderVida();
        assertEquals(100.0, balin.getVida(), DELTA);
    }

    @Test
    public void dwarfNaoDevePerderVida5(){
        DadoFake dadoFake = new DadoFake();
        dadoFake.simularValor(5);
        Dwarf balin = new DwarfBarbaLonga("Balin", dadoFake);
        balin.perderVida();
        assertEquals(110.0, balin.getVida(), DELTA);
    }
    
        @Test
    public void dwarfNaoDevePerderVida6(){
        DadoFake dadoFake = new DadoFake();
        dadoFake.simularValor(6);
        Dwarf balin = new DwarfBarbaLonga("Balin", dadoFake);
        balin.perderVida();
        assertEquals(110.0, balin.getVida(), DELTA);
    }
}
