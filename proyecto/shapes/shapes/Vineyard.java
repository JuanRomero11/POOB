package shapes;


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
    public Vineyard(String name,int xi,int xf)
    {
        String [] colores= {"green","magenta","red","white"};
        int numRandon;
        vi�edo= new Rectangle(xf-xi,10);
        numRandon = (int) Math.round(Math.random() * 4 ); 
        vi�edo.changeColor("white");
        vi�edo.moveHorizontal(xi);
        //vi�edo.moveVertical(10);
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
}
