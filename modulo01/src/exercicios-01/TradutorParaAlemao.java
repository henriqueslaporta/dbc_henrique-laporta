
public class TradutorParaAlemao implements Tradutor
{

    public String traduzir(String textoEmPortugues){
        switch(textoEmPortugues){
            case "Obrigado":
                return "Danke";
            case "Sim":
                return "Ya";
            default:
                return "";
        }
    }
}
