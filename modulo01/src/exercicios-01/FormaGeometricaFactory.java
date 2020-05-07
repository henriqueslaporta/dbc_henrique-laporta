public class FormaGeometricaFactory
{
    public static FormaGeometrica criar(int tipo, int x, int y){
        FormaGeometrica fomaGeometrica;
        switch(tipo){
            case 1: 
                //criar Retangulo
                fomaGeometrica = new Retangulo();
                break;
            case 2:
                //criar Quadrado
                fomaGeometrica = new Quadrado();
                break;
            default: 
                //criar Retangulo
                fomaGeometrica = new Retangulo();
                break;
        }
        
        fomaGeometrica.setX(x);
        fomaGeometrica.setY(y);
        
        return fomaGeometrica;
    }
}
