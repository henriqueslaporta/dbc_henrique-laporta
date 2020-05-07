import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class ElfoDaLuzTest
{
    private final double DELTA = 0.01;

    @After
    public void tearDown(){
        System.gc();
        System.runFinalization();
    }
    
    @Test
    public void atacarDwarfUmaVezComEspadaDeGalvorn(){
        ElfoDaLuz elfoDaLuz = new ElfoDaLuz("Iluminado");
        elfoDaLuz.atacarComEspada(new Dwarf("Dwarf"));

        assertEquals(79, elfoDaLuz.getVida(), DELTA);
        assertEquals(1, elfoDaLuz.getExperiencia(), DELTA);
    }

    @Test
    public void atacarDwarfDuasVezesComEspadaDeGalvorn(){
        ElfoDaLuz elfoDaLuz = new ElfoDaLuz("Iluminado");
        elfoDaLuz.atacarComEspada(new Dwarf("Dwarf"));
        elfoDaLuz.atacarComEspada(new Dwarf("Dwarf"));

        assertEquals(89, elfoDaLuz.getVida(), DELTA);
        assertEquals(2, elfoDaLuz.getExperiencia(), DELTA);
    }

    @Test
    public void atacarDwarfAteMorrer(){
        ElfoDaLuz elfoDaLuz = new ElfoDaLuz("Iluminado");
        elfoDaLuz.atacarComEspada(new Dwarf("Dwarf"));
        elfoDaLuz.atacarComEspada(new Dwarf("Dwarf"));
        elfoDaLuz.atacarComEspada(new Dwarf("Dwarf"));
        elfoDaLuz.atacarComEspada(new Dwarf("Dwarf"));
        elfoDaLuz.atacarComEspada(new Dwarf("Dwarf"));
        elfoDaLuz.atacarComEspada(new Dwarf("Dwarf"));
        elfoDaLuz.atacarComEspada(new Dwarf("Dwarf"));
        elfoDaLuz.atacarComEspada(new Dwarf("Dwarf"));
        elfoDaLuz.atacarComEspada(new Dwarf("Dwarf"));
        elfoDaLuz.atacarComEspada(new Dwarf("Dwarf"));
        elfoDaLuz.atacarComEspada(new Dwarf("Dwarf"));
        elfoDaLuz.atacarComEspada(new Dwarf("Dwarf"));
        elfoDaLuz.atacarComEspada(new Dwarf("Dwarf"));
        elfoDaLuz.atacarComEspada(new Dwarf("Dwarf"));
        elfoDaLuz.atacarComEspada(new Dwarf("Dwarf"));
        elfoDaLuz.atacarComEspada(new Dwarf("Dwarf"));
        elfoDaLuz.atacarComEspada(new Dwarf("Dwarf"));

        assertEquals(0, elfoDaLuz.getVida(), DELTA);
        assertEquals(17, elfoDaLuz.getExperiencia(), DELTA);
        assertEquals(Status.MORTO, elfoDaLuz.getStatus());
    }

    @Test
    public void elfoNucaPerdeAEspadaDeGalvorn(){
        ElfoDaLuz elfoDaLuz = new ElfoDaLuz("Iluminado");

        elfoDaLuz.perderItem(new ItemImperdivel("Espada de Galvorn",1));

        ArrayList<Item> esperado = new ArrayList<>(Arrays.asList(
                    new Item("Arco", 1),
                    new Item("Flecha", 7),
                    new Item("Espada de Galvorn", 1)
                ));
        assertEquals(esperado, elfoDaLuz.getInventario().getItens());
    }

    @Test
    public void esfoDaLuzSoAtacaComUnidadeDeEspada(){
        ElfoDaLuz elfoDaLuz = new ElfoDaLuz("Iluminado");
        elfoDaLuz.getInventario().getItens().get(2).setQuantidade(0);
        elfoDaLuz.atacarComEspada(new Dwarf("Gul"));
        assertEquals(79, elfoDaLuz.getVida(), DELTA);
    }

}
