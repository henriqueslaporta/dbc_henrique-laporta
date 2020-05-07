import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class AtaqueIntercaladoTest
{
    @Test
    public void atacarComExercitoUmDeCada(){
        AtaqueIntercalado estrategia = new AtaqueIntercalado();

        Elfo teste1 = new ElfoVerde("teste1");
        Elfo teste2 = new ElfoNoturno("teste2");
        List<Elfo> original = new ArrayList<>(Arrays.asList(teste1,teste2));

        List<Elfo> esperado = new ArrayList<>(Arrays.asList(teste1,teste2));

        assertEquals(esperado, estrategia.getOrdemDeAtaque(original));
    }
    
    @Test
    public void atacarComExercitoJaIntercalado(){
        AtaqueIntercalado estrategia = new AtaqueIntercalado();

        Elfo teste1 = new ElfoVerde("teste1");
        Elfo teste2 = new ElfoNoturno("teste2");
        Elfo teste3 = new ElfoVerde("teste3");
        Elfo teste4 = new ElfoNoturno("teste4");
        List<Elfo> original = new ArrayList<>(Arrays.asList(teste1,teste2,teste3,teste4));

        List<Elfo> esperado = new ArrayList<>(Arrays.asList(teste1,teste2,teste3,teste4));

        assertEquals(esperado, estrategia.getOrdemDeAtaque(original));
    }
    
    @Test
    public void atacarComExercito(){
        AtaqueIntercalado estrategia = new AtaqueIntercalado();

        Elfo teste1 = new ElfoNoturno("teste1");
        Elfo teste2 = new ElfoVerde("teste2");
        Elfo teste3 = new ElfoVerde("teste3");
        Elfo teste4 = new ElfoNoturno("teste4");
        Elfo teste5 = new ElfoNoturno("teste5");
        Elfo teste6 = new ElfoVerde("teste6");
        List<Elfo> original = new ArrayList<>(Arrays.asList(teste1,teste2,teste3,teste4,teste5,teste6));

        List<Elfo> esperado = new ArrayList<>(Arrays.asList(teste2,teste1,teste3,teste4,teste6,teste5));

        assertEquals(esperado, estrategia.getOrdemDeAtaque(original));
    }
}
