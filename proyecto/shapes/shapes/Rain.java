package shapes;
import java.util.*;
/**
 * Write a description of class Rain here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Rain
{
    // instance variables - replace the example below with your own
    private Object lluvia;
    private int distance=10;
    private boolean isVisible=false;
    private int x;
    private int y;
    private ArrayList<Integer> posicionInicial;
    private ArrayList<Integer> posicionFinal;
    /**
     * Constructor for objects of class Rain
     */
    public Rain(int x,int y)
    {
        this.x=x;
        this.y=y;
        Rectangle lluvia_parcial = new Rectangle(distance,y);
        lluvia_parcial.moveHorizontal(x);
        lluvia_parcial.changeColor("blue");
        lluvia=lluvia_parcial;
    }
    public Rain(ArrayList<Integer> posicionInicial,ArrayList<Integer> posicionFinal)
    {
        this.posicionInicial=posicionInicial;
        this.posicionFinal=posicionFinal;
        int[] posicionIni=new int[]{posicionInicial.get(0),posicionInicial.get(1)};;
        int[] posicionFin= new int[]{posicionFinal.get(0),posicionFinal.get(1)};
        Trap lluvia_parcial = new Trap(posicionIni,posicionFin);
        lluvia_parcial.changeColor("blue");
        lluvia=lluvia_parcial;
    }
    public void makeVisible(){
        if(lluvia instanceof Trap){
            Trap nuevaLluvia =(Trap) lluvia;
            nuevaLluvia.makeVisible();
        }else if(lluvia instanceof Rectangle){
            Rectangle nuevaLluvia =(Rectangle) lluvia;
            nuevaLluvia.makeVisible();
        }
        isVisible=true;
    }
    public void changeSize(int x){
        if(lluvia instanceof Trap){
            Trap nuevaLluvia =(Trap) lluvia;
            nuevaLluvia.changeSize(10,x);
            
        }else if(lluvia instanceof Rectangle){
            Rectangle nuevaLluvia =(Rectangle) lluvia;
            nuevaLluvia.changeSize(nuevaLluvia.getmedidas()[0],nuevaLluvia.getmedidas()[1]);
            nuevaLluvia.moveVertical(x);
        }
        isVisible=true;
    }
    public void makeInvisible(){
        if(lluvia instanceof Trap){
            Trap nuevaLluvia =(Trap) lluvia;
            nuevaLluvia.makeInvisible();
        }else if(lluvia instanceof Rectangle){
            Rectangle nuevaLluvia =(Rectangle) lluvia;
            nuevaLluvia.makeInvisible();
        }
        isVisible=false;
    }

    public void moveVertical(int distance){
        if(lluvia instanceof Trap){
            Trap nuevaLluvia =(Trap) lluvia;
            nuevaLluvia.moveVertical(distance);
        }else if(lluvia instanceof Rectangle){
            Rectangle nuevaLluvia =(Rectangle) lluvia;
            nuevaLluvia.moveVertical(distance);
        }
    }
    public void moveHorizontal(int distance){
        if(lluvia instanceof Trap){
            Trap nuevaLluvia =(Trap) lluvia;
            nuevaLluvia.moveHorizontal(distance);
        }else if(lluvia instanceof Rectangle){
            Rectangle nuevaLluvia =(Rectangle) lluvia;
            nuevaLluvia.moveHorizontal(distance);
        }
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    
}
