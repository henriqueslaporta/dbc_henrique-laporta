
public class Programa
{
    public static void main(String[] args){
        //ler entrada
        int entrada = 2;
        int x = 5;
        int y = 10;
        
        FormaGeometrica formaGeometrica = FormaGeometricaFactory.criar(entrada, x, y);
        System.out.println(formaGeometrica.calcularArea());
    }
}
