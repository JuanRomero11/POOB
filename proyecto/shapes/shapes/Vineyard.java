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
    private Rectangle viñedo;
    private int numRandon;
    private int xinicial;
    private int xfinal;
    private String color;
    private boolean isVisible;
    private int distance=10;
    public Vineyard(String name,int xi,int xf)
    {
        String [] colores= {"green","magenta","red","white"};
        int numRandom;
        viñedo= new Rectangle(xf-xi,10);
        numRandom = (int) Math.round(Math.random() * 4 ); 
        viñedo.changeColor(colores[numRandom]);
        viñedo.moveHorizontal(xi);
        xinicial=(xi);
        xfinal=(xf);

    }

    public void makeVisible(){
        viñedo.makeVisible();
    }

    public void makeInvisible(){
        viñedo.makeInvisible();
    }
    public void moveVertical(int distance){
            viñedo.moveVertical(distance);
     }
    public void changeSize(int x){
        distance+=x;
        viñedo.changeSize(10,(xfinal-xinicial)+x);
        viñedo.moveVertical(x);
    }
}
