package shapes;
import java.util.*;
import java.awt.geom.Area;
/**
 * Write a description of class Vineyard here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Vineyard extends Rectangle
{


    private int xinicial;
    private int xfinal;
    private String color;
    private String name;
    private boolean isVisible;
    private int distance=10;
    public Vineyard(String name,int xi,int xf)
    {
        super(xf-xi,10);
        String [] colores= {"green","magenta","red","white"};
        int numRandom;
        this.name=name;
        //super=new Rectangle(xf-xi,10);
        numRandom = (int) Math.round(Math.random() * 4 ); 
        color=colores[numRandom];
        super.changeColor(color);
        super.moveHorizontal(xi);
        xinicial=(xi);
        xfinal=(xf);

    }

    public String getName(){
        return this.name;
    }

    public void makeVisible(){
        super.makeVisible();
    }

    public int[] posiciones(){
        return new int[]{xinicial,xfinal};
    }

    public void makeInvisible(){
        super.makeInvisible();
    }

    public void moveVertical(int distance){
        super.moveVertical(distance);
    }

    public void changeSize(int x){
        distance+=x;
        super.changeSize(10,(xfinal-xinicial)+x);
        super.moveVertical(x);
    }

    public String getColor(){
        return this.color;
    }

    public void setColor(String color){
        this.color=color;
    }
}
