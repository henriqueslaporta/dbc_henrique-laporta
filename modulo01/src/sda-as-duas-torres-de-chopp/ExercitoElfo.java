import java.util.*;
public class ExercitoElfo extends Exercito
{
    private final static ArrayList<Class> TIPOS_PERIMITIDOS = new ArrayList(
                Arrays.asList(
                    ElfoVerde.class, 
                    ElfoNoturno.class
                )
            );
    
    protected void alistar(Personagem elfo){
        boolean podeAlistar = TIPOS_PERIMITIDOS.contains(elfo.getClass());
        if(podeAlistar){
            super.alistar(elfo);
        }
    }
}
