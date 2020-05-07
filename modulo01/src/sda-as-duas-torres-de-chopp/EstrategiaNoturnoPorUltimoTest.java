import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class EstrategiaNoturnoPorUltimoTest
{
    @Test
    public void atacarComExercitoUmDeCada(){
        EstrategiaNoturnoPorUltimo estrategia = new EstrategiaNoturnoPorUltimo();

        Elfo teste1 = new ElfoVerde("teste1");
        Elfo teste2 = new ElfoNoturno("teste2");

        List<Elfo> original = new ArrayList<>(Arrays.asList(teste2,teste1));

        List<Elfo> esperado = new ArrayList<>(Arrays.asList(teste1,teste2));

        assertEquals(esperado, estrategia.getOrdemDeAtaque(original));
    }

    @Test
    public void atacarComExercito(){
        EstrategiaNoturnoPorUltimo estrategia = new EstrategiaNoturnoPorUltimo();

        Elfo teste1 = new ElfoVerde("teste1");
        Elfo teste2 = new ElfoNoturno("teste2");
        Elfo teste3 = new ElfoVerde("teste3");
        Elfo teste4 = new ElfoNoturno("teste4");
        Elfo teste5 = new ElfoVerde("teste5");
        List<Elfo> original = new ArrayList<>(Arrays.asList(teste1,teste2,teste3,teste4,teste5));

        List<Elfo> esperado = new ArrayList<>(Arrays.asList(teste1,teste3,teste5,teste2,teste4));

        assertEquals(esperado, estrategia.getOrdemDeAtaque(original));
    }

    @Test
    public void atacarComExercitoInverso(){
        EstrategiaNoturnoPorUltimo estrategia = new EstrategiaNoturnoPorUltimo();

        Elfo teste1 = new ElfoVerde("teste1");
        Elfo teste2 = new ElfoNoturno("teste2");
        Elfo teste3 = new ElfoVerde("teste3");
        Elfo teste4 = new ElfoNoturno("teste4");
        Elfo teste5 = new ElfoVerde("teste5");
        List<Elfo> original = new ArrayList<>(Arrays.asList(teste2,teste4,teste1,teste3,teste5));

        List<Elfo> esperado = new ArrayList<>(Arrays.asList(teste1,teste3,teste5,teste2,teste4));

        assertEquals(esperado, estrategia.getOrdemDeAtaque(original));
    }
}
