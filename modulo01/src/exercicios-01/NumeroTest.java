import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NumeroTest
{
    @Test
    public void numero5Impar(){
        Numero cinco = new Numero(5);
        assertTrue(cinco.impar());
    }
    
    @Test
    public void numero17Impar(){
        Numero cinco = new Numero(17);
        assertTrue(cinco.impar());
    }
    
    @Test
    public void numero2018NaoEhImpar(){
        Numero cinco = new Numero(2018);
        assertFalse(cinco.impar());
    }
    
    @Test
    public void somaNumeroEDivisivel(){
        assertEquals(true, new Numero(9).verificarSomaDivisivel(1892376));
        assertEquals(false, new Numero(3).verificarSomaDivisivel(17) );
        assertEquals(true, new Numero(0).verificarSomaDivisivel(0) );
    }
    
    @Test
    public void soma1892376EhDivisivelPor9(){
        Numero divisor = new Numero(9);
        assertTrue(divisor.verificarSomaDivisivel(1892376));
    }
    
    @Test
    public void soma17NaoEhDivisivelPor3(){
        Numero divisor = new Numero(3);
        assertFalse(divisor.verificarSomaDivisivel(17));
    }
    
    @Test
    public void soma0EVerdadeiro(){
        Numero divisor = new Numero(15151);
        assertTrue(divisor.verificarSomaDivisivel(0));
    }
}
