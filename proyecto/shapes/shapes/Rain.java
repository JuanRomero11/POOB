package shapes;


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
    public Rain(int y)
    {
        Rectangle lluvia_parcial = new Rectangle(10,y);
        lluvia_parcial.changeColor("blue");
        lluvia=lluvia_parcial;
    }
    public Rain(int [] posicionInicial, int [] posicionFinal)
    {
        Trap lluvia_parcial = new Trap(posicionInicial,posicionFinal);
        lluvia_parcial.changeColor("blue");
        lluvia=lluvia_parcial;
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    
}
