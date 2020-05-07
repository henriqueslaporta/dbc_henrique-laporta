import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AgendaContatosTest
{
    @Test
    public void adicionarUmContato(){
        AgendaContatos agenda = new AgendaContatos();

        agenda.adicionarContato("Henrique", "51987456321");

        assertEquals("51987456321", agenda.consultar("Henrique"));
    }

    @Test
    public void adicionarVariosContatos(){
        AgendaContatos agenda = new AgendaContatos();

        agenda.adicionarContato("Henrique", "51987456321");
        agenda.adicionarContato("Joao", "51123456789");
        agenda.adicionarContato("Snow", "51456123789");

        assertEquals("51987456321", agenda.consultar("Henrique"));
        assertEquals("51123456789", agenda.consultar("Joao"));
        assertEquals("51456123789", agenda.consultar("Snow"));
    }

    @Test
    public void obterTelefoneDeUmContatoRepitido(){
        AgendaContatos agenda = new AgendaContatos();

        agenda.adicionarContato("Henrique", "51987456321");
        agenda.adicionarContato("Henrique", "51123456789");

        assertEquals("51123456789", agenda.consultar("Henrique"));
    }

    @Test
    public void buscarContatoQueNaoExiste(){
        AgendaContatos agenda = new AgendaContatos();

        assertNull(agenda.consultar("Henrique"));
    }

    @Test
    public void obterContatoPeloNumero(){
        AgendaContatos agenda = new AgendaContatos();

        agenda.adicionarContato("Henrique", "51987456321");
        agenda.adicionarContato("Joao", "51123456789");
        agenda.adicionarContato("Snow", "51456123789");

        assertEquals("Henrique", agenda.consultarNumero("51987456321"));
    }

    @Test
    public void obterContatoPeloNumeroQueNaoExiste(){
        AgendaContatos agenda = new AgendaContatos();

        agenda.adicionarContato("Henrique", "51987456321");
        agenda.adicionarContato("Joao", "51123456789");
        agenda.adicionarContato("Snow", "51456123789");

        assertNull(agenda.consultarNumero("9999999999"));
    }

    @Test
    public void obterCsvUmItem(){
        AgendaContatos agenda = new AgendaContatos();

        agenda.adicionarContato("Henrique", "51987456321");
        String separador = System.lineSeparator();
        String esperado = String.format("Henrique,51987456321%s", separador);
        assertEquals(esperado , agenda.csv());
    }

    @Test
    public void obterCsvDoisItem(){
        AgendaContatos agenda = new AgendaContatos();

        agenda.adicionarContato("Henrique", "51987456321");
        agenda.adicionarContato("Joao", "51123456789");

        String separador = System.lineSeparator();
        String esperado = String.format("Henrique,51987456321%sJoao,51123456789%s", separador, separador);

        assertEquals(esperado, agenda.csv());
    }

    @Test
    public void obterCsvVazio(){
        AgendaContatos agenda = new AgendaContatos();

        assertEquals("", agenda.csv());
    }

}
