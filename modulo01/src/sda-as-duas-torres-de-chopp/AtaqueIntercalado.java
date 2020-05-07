import java.util.*;

public class AtaqueIntercalado implements Estrategias
{
    public List<Elfo> getOrdemDeAtaque(List<Elfo> atacantes){
        int size = atacantes.size();
        List<Elfo> ordenado = new ArrayList<>(size);
        Iterator<Elfo> iteratorVerdes = atacantes.iterator();
        Iterator<Elfo> iteratorNoturnos = atacantes.iterator();

        while(iteratorVerdes.hasNext()){
            Elfo elfoVerde = iteratorVerdes.next();

            if(elfoVerde.getClass().equals(ElfoVerde.class)){ //Quando achar o primeiro elfoVerde add na lista ordenado
                ordenado.add(elfoVerde);

                while(iteratorNoturnos.hasNext()){  //Procura pelo primeiro elfo noturno e add em ordenado

                    Elfo elfoNoturno = iteratorNoturnos.next();
                    if(elfoNoturno.getClass().equals(ElfoNoturno.class)){
                        ordenado.add(elfoNoturno);
                        break;
                    }

                }

            }

        }

        return ordenado;
    }
}