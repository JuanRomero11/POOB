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
    private boolean isVisible;
    private int distance=10;
    public Vineyard(String name,int xi,int xf)
    {
        String [] colores= {"green","magenta","red","white"};
        int numRandom;
        vi�edo= new Rectangle(xf-xi,10);
        numRandom = (int) Math.round(Math.random() * 4 ); 
        vi�edo.changeColor(colores[numRandom]);
        vi�edo.moveHorizontal(xi);
        xinicial=(xi);
        xfinal=(xf);

    }

    public void makeVisible(){
        vi�edo.makeVisible();
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
}
