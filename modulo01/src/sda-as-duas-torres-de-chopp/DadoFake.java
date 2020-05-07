import java.util.Random;

public class DadoFake implements Sorteador
{
    int valor = 0;
    
    public void simularValor(int valor){
        this.valor = valor;
    }
    
    public int sortear()
    {
        return valor;
    }
}
