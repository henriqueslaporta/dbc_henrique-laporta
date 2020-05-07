import java.util.*;

public class Exercito
{

    HashMap<Status, HashMap<String, Personagem> > exercito;

    protected static int QUANTOS_MORRERAM = 0;

    public Exercito(){
        exercito = new HashMap<>();
    }

    protected void alistar(Personagem personagem){
        HashMap<String,Personagem> personagens = this.exercito.get(personagem.getStatus());

        if(personagens == null){
            personagens = new HashMap<>();
            exercito.put(personagem.getStatus(), personagens);
        }
        personagens.put(personagem.getNome(), personagem);

    }

    private void update(){
        HashMap<String,Personagem> vivos = this.exercito.get(Status.VIVO);
        if(vivos == null) 
            return;

        for (String key : vivos.keySet()) {
            if (vivos.get(key).getStatus() == Status.MORTO) {
                this.alistar(vivos.get(key));
                vivos.remove(key);
                if(--QUANTOS_MORRERAM == 0)
                    return;
            }
        }
    }

    protected HashMap<String,Personagem> buscar(Status status){
        if(QUANTOS_MORRERAM > 0) this.update();
        return exercito.get(status);
    }
}
