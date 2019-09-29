package shapes;
import java.util.*;
import java.awt.geom.Area;
/**
 * Write a description of class Vineyard here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Vineyard
{
    private Rectangle vi�edo;
    private int numRandon;
    private int xinicial;
    private int xfinal;
    private String color;
    private String name;
    private boolean isVisible;
    private int distance=10;
    public Vineyard(String name,int xi,int xf)
    {
        String [] colores= {"green","magenta","red","white"};
        int numRandom;
        this.name=name;
        vi�edo= new Rectangle(xf-xi,10);
        numRandom = (int) Math.round(Math.random() * 4 ); 
        color=colores[numRandom];
        vi�edo.changeColor(color);
        vi�edo.moveHorizontal(xi);
        xinicial=(xi);
        xfinal=(xf);

    }
    public String getName(){
        return this.name;
    }
    public void makeVisible(){
        vi�edo.makeVisible();
    }
    public int[] posiciones(){
        return new int[]{xinicial,xfinal};
    }
    public void makeInvisible(){
        vi�edo.makeInvisible();
    }
    public void moveVertical(int distance){
            vi�edo.moveVertical(distance);
     }
    public void changeSize(int x){
        distance+=x;
        vi�edo.changeSize(10,(xfinal-xinicial)+x);
        vi�edo.moveVertical(x);
    }
    
    public String getColor(){
        return this.color;
    }
    public void setColor(String color){
        this.color=color;
    }
}
