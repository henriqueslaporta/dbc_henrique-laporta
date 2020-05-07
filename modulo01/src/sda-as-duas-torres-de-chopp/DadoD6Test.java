import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class DadoD6Test
{
    ArrayList<Integer> numerosValidosD6 = new ArrayList<>(Arrays.asList(1,2,3,4,5,6));
    
    @Test
    public void testaDadoRandom(){
        DadoD6 dado = new DadoD6();

        assertTrue(numerosValidosD6.contains(dado.sortear()));
    }
}
