/**
 *
 * @author Henrique La Porta
 */

// doc: http://commons.apache.org/proper/commons-lang/javadocs/api-3.8.1/index.html
import org.apache.commons.lang3.StringUtils;

public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println("- StringUtils.capitalize -");
        
        System.out.println(StringUtils.capitalize("henrique"));

        System.out.println("- StringUtils.isBlank -");
        
        System.out.println(StringUtils.isBlank(""));
        System.out.println(StringUtils.isBlank("Henrique"));
        System.out.println(StringUtils.isBlank("  "));

        System.out.println("- StringUtils.leftPad -");

        //completar com espaco
        System.out.println(StringUtils.leftPad("273112", 10));
        // adicionar o nono digito no telefone
        System.out.println(StringUtils.leftPad("84226308", 9, '9'));
        // adicionar o nono digito e espaco no telefone
        System.out.println(StringUtils.leftPad("8422-6308", 11, "9 "));
        System.out.println(StringUtils.leftPad("Henrique", 10 , "RRR"));

        System.out.println("- StringUtils.rightPad -");
        
        //completar com espaco
        System.out.println(StringUtils.rightPad("273112", 10));
        // adicionar a direita
        System.out.println(StringUtils.rightPad("84226308", 9, '/'));
        // completar data com ano atual
        System.out.println(StringUtils.rightPad("09/10", 10, "/2018"));
        // completar valor "int"(max 999) com ".00
        System.out.println(StringUtils.rightPad("550", 6 , ".00"));

        System.out.println("- StringUtils.trimToNull -");
        
        System.out.println(StringUtils.trimToNull("   abc     "));
        System.out.println(StringUtils.trimToNull(" \t  abc     "));
        System.out.println(StringUtils.trimToNull(""));
        System.out.println(StringUtils.trimToNull("        "));
        
        System.out.println("- StringUtils.equals -");
        
        System.out.println(StringUtils.equals("oi", "oi"));
        System.out.println(StringUtils.equals("Oi", "oi"));
        System.out.println(StringUtils.equals("", null));
        System.out.println(StringUtils.equals(" ", "  "));
        
        System.out.println("- StringUtils.contains -");
        
        System.out.println(StringUtils.contains("oi", "oi"));
        System.out.println(StringUtils.contains("Oi", "i"));
        System.out.println(StringUtils.contains("Henrique", "que"));
        System.out.println(StringUtils.contains("   ", ""));
        System.out.println(StringUtils.contains( null, ""));
        
    }
    
}
