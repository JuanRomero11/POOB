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

    /**
     * Constructor for objects of class Rain
     */
    public Rain(int x,int y)
    {
        Rectangle lluvia_parcial = new Rectangle(10,y);
        lluvia_parcial.moveHorizontal(x);
        lluvia_parcial.changeColor("blue");
        lluvia=lluvia_parcial;
    }
    public Rain(ArrayList<Integer> posicionInicial,ArrayList<Integer> posicionFinal)
    {
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
    }
    public void makeInvisible(){
        if(lluvia instanceof Trap){
            Trap nuevaLluvia =(Trap) lluvia;
            nuevaLluvia.makeInvisible();
        }else if(lluvia instanceof Rectangle){
            Rectangle nuevaLluvia =(Rectangle) lluvia;
            nuevaLluvia.makeInvisible();
        }
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
