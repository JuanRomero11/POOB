package shapes;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;
public class Trap
{ 
    private int height;
    private int width;
    private int xPosition;
    private int yPosition;
    private String color;
    private int[] iniciales=new int[100];
    private int[] finales=new  int[100];
    private boolean isVisible;
    public Trap(int[]  iniciales,int[]  finales)
    {
        this.iniciales=iniciales;
        this.finales=finales;
    isVisible = false;
    }

    private void draw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            int[] xpoints = { finales[0]-10, iniciales[0]-10,iniciales[0],finales[0] };
            int[] ypoints = { finales[1], iniciales[1], iniciales[1],finales[1]};
            canvas.draw(this, "blue", new Polygon(xpoints, ypoints, 4));
            canvas.wait(10);
        }
    }
    public void makeVisible(){
        isVisible = true;
    draw();
    }
    public void makeInvisible(){
        erase();
        isVisible = false;
    }
    private void erase(){
	if(isVisible) {
		Canvas canvas = Canvas.getCanvas();
		canvas.erase(this);
	}
    }
}
