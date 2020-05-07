import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class ExercitoElfoTest
{
    @After
    public void tearDown(){
        System.gc();
        System.runFinalization();
    }
    
    @Test
    public void alistarUmElfoVerde(){
        ExercitoElfo exercito = new ExercitoElfo();

        exercito.alistar(new ElfoVerde("verde"));

        HashMap<String,Personagem> esperado = new HashMap<>();
        esperado.put("verde", new ElfoVerde("verde"));
        assertEquals(esperado, exercito.buscar(Status.VIVO));
    }

    @Test
    public void alistarUmElfoNoturno(){
        ExercitoElfo exercito = new ExercitoElfo();

        exercito.alistar(new ElfoNoturno("noturno"));

        HashMap<String,Personagem> esperado = new HashMap<>();
        esperado.put("noturno",new ElfoNoturno("noturno"));
        assertEquals(esperado, exercito.buscar(Status.VIVO));
    }

    @Test
    public void alistarUmElfoNoturnoEUmElfoVerde(){
        ExercitoElfo exercito = new ExercitoElfo();

        exercito.alistar(new ElfoNoturno("noturno"));
        exercito.alistar(new ElfoVerde("verde"));

        HashMap<String,Personagem> esperado = new HashMap<>();
        esperado.put("verde", new ElfoVerde("verde"));
        esperado.put("noturno",new ElfoNoturno("noturno"));

        assertEquals(esperado, exercito.buscar(Status.VIVO));
    }

    @Test
    public void alistarUmElfoNoturnoMorto(){
        ExercitoElfo exercito = new ExercitoElfo();

        ElfoNoturno noturno = new ElfoNoturno("Noturninho");

        noturno.atirarFlecha(new Dwarf("Mickey")); //1
        noturno.atirarFlecha(new Dwarf("Mickey")); //2
        noturno.atirarFlecha(new Dwarf("Mickey")); //3
        noturno.atirarFlecha(new Dwarf("Mickey")); //4
        noturno.atirarFlecha(new Dwarf("Mickey")); //5
        noturno.atirarFlecha(new Dwarf("Mickey")); //6
        noturno.atirarFlecha(new Dwarf("Mickey")); //7

        exercito.alistar(noturno);

        HashMap<String,Personagem> esperado = new HashMap<>();
        esperado.put(noturno.getNome(), noturno);
        assertEquals(esperado, exercito.buscar(Status.MORTO));
    }

    @Test
    public void alistarUmElfoDaLuz(){
        ExercitoElfo exercito = new ExercitoElfo();

        exercito.alistar(new ElfoDaLuz("Iluminado"));

        assertNull(exercito.buscar(Status.VIVO));
    }

    @Test
    public void alistarUmElfoEDepoisMatalo(){
        ExercitoElfo exercito = new ExercitoElfo();

        ElfoNoturno noturno = new ElfoNoturno("Noturninho");

        exercito.alistar(noturno);

        noturno.atirarFlecha(new Dwarf("Mickey")); //1
        noturno.atirarFlecha(new Dwarf("Mickey")); //2
        noturno.atirarFlecha(new Dwarf("Mickey")); //3
        noturno.atirarFlecha(new Dwarf("Mickey")); //4
        noturno.atirarFlecha(new Dwarf("Mickey")); //5
        noturno.atirarFlecha(new Dwarf("Mickey")); //6
        noturno.atirarFlecha(new Dwarf("Mickey")); //7

        HashMap<String,Personagem> esperado = new HashMap<>();
        esperado.put(noturno.getNome(), noturno);

        assertEquals(new HashMap<String,Personagem>(), exercito.buscar(Status.VIVO));
        assertEquals(esperado, exercito.buscar(Status.MORTO));
    }

    @Test
    public void alistarTresElfosEDepoisMatarUm(){
        ExercitoElfo exercito = new ExercitoElfo();

        ElfoNoturno noturno = new ElfoNoturno("Noturninho");
        exercito.alistar(new ElfoNoturno("noturno"));
        exercito.alistar(new ElfoVerde("verde"));

        exercito.alistar(noturno);

        noturno.atirarFlecha(new Dwarf("Mickey")); //1
        noturno.atirarFlecha(new Dwarf("Mickey")); //2
        noturno.atirarFlecha(new Dwarf("Mickey")); //3
        noturno.atirarFlecha(new Dwarf("Mickey")); //4
        noturno.atirarFlecha(new Dwarf("Mickey")); //5
        noturno.atirarFlecha(new Dwarf("Mickey")); //6
        noturno.atirarFlecha(new Dwarf("Mickey")); //7

        HashMap<String,Personagem> mortos = new HashMap<>();
        mortos.put(noturno.getNome(), noturno);

        HashMap<String,Personagem> vivos = new HashMap<>();
        vivos.put("verde", new ElfoVerde("verde"));
        vivos.put("noturno",new ElfoNoturno("noturno"));
        
        assertEquals(vivos, exercito.buscar(Status.VIVO));
        assertEquals(mortos, exercito.buscar(Status.MORTO));
    }
}
