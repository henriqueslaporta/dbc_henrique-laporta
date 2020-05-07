import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NumerosTest
{
    private final double DELTA = 0.1;
    @Test
    public void calculaMediaMatrizEntrada(){
        double[] entrada = new double[] { 1.0, 3.0, 5.0, 1.0, -10.0 };
        double[] esperado = new double[] {2.0,4.0,3.0,-4.5};
        Numeros numero = new Numeros(entrada);
        assertArrayEquals(esperado ,numero.calcularMediaSeguinte(), DELTA);
    }

    @Test
    public void calculaMediaMatrizDeUmElemento(){
        double[] entrada = new double[] { 1.0 };
        double[] esperado = new double[] { };
        Numeros numero = new Numeros(entrada);
        assertArrayEquals(esperado ,numero.calcularMediaSeguinte(), DELTA);
    }
    
    @Test
    public void calculaMediaMatrizDeVazio(){
        double[] entrada = new double[] { };
        double[] esperado = new double[] { };
        Numeros numero = new Numeros(entrada);
        assertArrayEquals(esperado ,numero.calcularMediaSeguinte(), DELTA);
    }

    @Test
    public void calculaMediaMatrizDe10Elementos(){
        double[] entrada = new double[] { 1.0, 3.0, 5.0, 1.0, -10.0, 1.0, 3.0, 5.0, 1.0, -10.0 };
        double[] esperado = new double[] {2.0, 4.0, 3.0, -4.5, -4.5, 2.0, 4.0 ,3.0, -4.5};
        Numeros numero = new Numeros(entrada);
        assertArrayEquals(esperado ,numero.calcularMediaSeguinte(), 0.01);
    }
}
