package shapes;


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
    public Vineyard(String name,int xi,int xf)
    {
        String [] colores= {"green","magenta","red","white"};
        int numRandon;
        viñedo= new Rectangle(xf-xi,10);
        numRandon = (int) Math.round(Math.random() * 4 ); 
        viñedo.changeColor("white");
        viñedo.moveHorizontal(xi);
        //viñedo.moveVertical(10);
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
}
