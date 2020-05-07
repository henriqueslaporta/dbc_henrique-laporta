import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ItemEspecialTest
{
    @Test
    public void criaItemEspecialETestaQuantidade(){
        Item item = new ItemImperdivel("Especial", 1);

        assertEquals("Especial", item.getDescricao());
        assertEquals( 1, item.getQuantidade());

    }

    @Test
    public void criaItemEspecialComQuantidadeZeroETestaQuantidade(){
        Item item = new ItemImperdivel("Especial", 0);

        assertEquals("Especial", item.getDescricao());
        assertEquals( 1, item.getQuantidade());

    }

    @Test
    public void tentaSetQuantidadeZero(){
        Item item = new ItemImperdivel("Especial",  1);

        item.setQuantidade(0);

        assertEquals( 1, item.getQuantidade());

    }

    @Test
    public void tentaSetQuantidadeDois(){
        Item item = new ItemImperdivel("Especial",  1);

        item.setQuantidade(2);

        assertEquals( 2, item.getQuantidade());

    }

}
