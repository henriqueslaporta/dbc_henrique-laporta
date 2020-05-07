
public class Numeros
{
    private double[] array;
    
    public Numeros(double[] entrada){
        array = entrada;
    }
    
    public double[] calcularMediaSeguinte(){
        if(array.length == 0){ return new double[]{};}
        double[] medias = new double[array.length - 1];
        
        for(int i = 0; i < array.length - 1; i++){
            medias[i] = (array[i] + array[i+1]) / 2;
        }
        
        return medias;
    }
    
}