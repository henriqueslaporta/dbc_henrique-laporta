import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FuncionarioTest
{
    @Test
    public void lucroDeUmFuncionario(){
        double delta = 1e-15;
        assertEquals(585.0, new Funcionario("Capitão América", 500.0, 1000.0).getLucro(), delta);
        assertEquals(1508.4675, new Funcionario("Rogue", 500.0, 7840.50).getLucro(), delta);
        
    }
}
