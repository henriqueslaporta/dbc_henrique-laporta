
public class Quadrado implements FormaGeometrica
{
    private int x, y;
    
    public void setX(int x){
        this.x = x;
        this.y = x;
    }

    public void setY(int y){
        this.y = y;
        this.x = y;
    }

    public int getX(int x)
    {
        return this.x;
    }

    public int getY(int y)
    {
        return this.y;
    }

    public int calcularArea()
    {
        return this.x * this.y;
    }
}
