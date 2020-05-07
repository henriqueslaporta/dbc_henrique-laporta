
public class TradutorParaIngles implements Tradutor{

    public String traduzir(String textoEmPortugues){
        switch(textoEmPortugues){
            case "Obrigado":
                return "Thank you";
            case "Sim":
                return "Yes";
            default:
                return "";
        }
    }

}
