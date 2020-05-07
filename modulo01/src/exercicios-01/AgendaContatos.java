import java.util.*;

public class AgendaContatos
{
    LinkedHashMap<String, String> agenda = new LinkedHashMap<>();

    public AgendaContatos()
    {

    }

    public void adicionarContato(String nome, String numero){
        this.agenda.put(nome, numero);
    }

    public String consultar(String nome){
        return this.agenda.get(nome);
    }

    public String consultarNumero(String numero){
        for (String key : agenda.keySet()) {
            if (agenda.get(key).equals(numero)) {
                return key;
            }
        }
        return null;
    }

    public String csv(){
        if(agenda.size() == 0) return "";
        StringBuilder csv = new StringBuilder();
        for (String key : agenda.keySet()) {
            csv.append(String.format("%s,%s%s", key, agenda.get(key), System.lineSeparator()));
        }
        return csv.toString();
    }
}
