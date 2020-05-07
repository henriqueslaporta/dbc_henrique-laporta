import java.util.*;

public class EstrategiaNoturnoPorUltimo implements Estrategias
{
    public List<Elfo> getOrdemDeAtaque(List<Elfo> atacantes){

        Collections.sort (atacantes, new Comparator<Elfo>() {
                public int compare(Elfo o1, Elfo o2) {
                    if(o1.getClass().equals(ElfoVerde.class)){
                        return (o2.getClass().equals(ElfoVerde.class)) ? 0 : -1;
                    }
                    return 1;
                }
            });

        return atacantes;
    }
}
