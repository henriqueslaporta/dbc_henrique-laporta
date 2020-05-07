import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ElfoTest
{
    private final double DELTA = 0.01;

    @After
    public void tearDown(){
        System.gc();
        System.runFinalization();
    }
    
    @Test
    public void criarElfoInformandoNome(){
        Elfo legolas = new Elfo("Legolas");
        assertEquals("Legolas", legolas.getNome());
    }

    @Test
    public void legolasAtiraUmaFlecha(){
        Elfo legolas = new Elfo("Legolas");
        legolas.atirarFlecha(new Dwarf("Mickey"));
        assertEquals(6, legolas.getFlecha().getQuantidade());
        assertEquals(1, legolas.getExperiencia());
    }

    @Test
    public void legolasAtira8Flechas(){
        Elfo legolas = new Elfo("Legolas");
        legolas.atirarFlecha(new Dwarf("Mickey")); //1
        legolas.atirarFlecha(new Dwarf("Mickey")); //2
        legolas.atirarFlecha(new Dwarf("Mickey")); //3
        legolas.atirarFlecha(new Dwarf("Mickey")); //4
        legolas.atirarFlecha(new Dwarf("Mickey")); //5
        legolas.atirarFlecha(new Dwarf("Mickey")); //6
        legolas.atirarFlecha(new Dwarf("Mickey")); //7
        legolas.atirarFlecha(new Dwarf("Mickey")); //8
        assertEquals(0, legolas.getFlecha().getQuantidade());
        assertEquals(7, legolas.getExperiencia());
    }

    @Test
    public void atirarFlechaEmUmDwarf(){
        Elfo legolas = new Elfo("Legolas");
        Dwarf dwarf = new Dwarf("Gimli");
        legolas.atirarFlecha(dwarf);
        assertEquals(6, legolas.getFlecha().getQuantidade());
        assertEquals(1, legolas.getExperiencia());
        assertEquals(100, dwarf.getVida(), DELTA);
    }

    @Test
    public void criarUmElfoEReceberQtdDeElfosCriadodos(){
        int antes = Elfo.qtdElfosCriados;
        Elfo legolas = new Elfo("Legolas");

        assertEquals(antes + 1, Elfo.qtdElfosCriados);
    }

    @Test
    public void criarElfosEReceberQtdDeElfosCriadodos(){
        Elfo legolas = new Elfo("Legolas");
        Personagem p1 = new ElfoVerde("v");
        Personagem p2 = new ElfoNoturno("v");
        Personagem p3 = new ElfoDaLuz("v");

        assertEquals(4, Elfo.qtdElfosCriados);
    }

    @Test
    public void criarDwarfEReceberQtdDeElfosCriadodos(){
        Personagem p1 = new Dwarf("dwarf");

        assertEquals(0, Elfo.qtdElfosCriados);
    }
}