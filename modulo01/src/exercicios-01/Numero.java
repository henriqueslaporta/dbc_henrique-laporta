
public class Numero
{
    private int numero;
    
    public Numero(int numero){
        this.numero = numero;
    }
    
    public boolean impar(){
        if(numero % 2 == 0) // e Impar
            return false;
        else
            return true;    // é Par
    }
    
    public boolean verificarSomaDivisivel(int numero){
        if(numero == 0) return true;
        
        int aux_numero = numero;
        int soma = 0;
        int resto;
        while(aux_numero != 0){
            resto = aux_numero % 10;
            soma += resto;
            aux_numero = (aux_numero - resto) / 10;
            
        }
        
        if(soma % this.numero == 0)
            return true;
        else
            return false;
    }
}