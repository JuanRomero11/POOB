package shapes;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;
/**
 * A circle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0.  (15 July 2000) 
 */

public class Puncture extends Circle{
    public static double PI=3.1416;
    private int diameter;
    private int xPosition;
    private int yPosition;
    private String color;
    private boolean isVisible;
    private ArrayList<ArrayList<Integer>> huecos;
    /**
     * Create a new circle at default position with default color.public Puncture(int xposition,int yposition,ArrayList<ArrayList<Integer>> huecos){
     */
    public Puncture(int xposition,int yposition,ArrayList<ArrayList<Integer>> huecos){
        this.huecos=huecos;
        diameter = 15;
        this.xPosition = xposition;
        this.yPosition = yposition-20;
        color = "yellow";
        isVisible = false;
    }
    public ArrayList<Integer> punctureSuperficial(){
        return huecos.get(0);
    }
    public int EnPosicionx(){
        return xPosition;
    }
    public ArrayList<ArrayList<Integer>> huecos(){
        return this.huecos;
    }
    /**
     * Make this circle visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }

    /**
     * Make this circle invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }

    /**
     * Move the circle a few pixels to the right.
     */
    public void moveRight(){
        moveHorizontal(20);
    }

    /**
     * Move the circle a few pixels to the left.
     */
    public void moveLeft(){
        moveHorizontal(-20);
    }

    /**
     * Move the circle a few pixels up.
     */
    public void moveUp(){
        moveVertical(-20);
    }

    /**
     * Move the circle a few pixels down.
     */
    public void moveDown(){
        moveVertical(20);
    }

    /**
     * Move the circle horizontally.
     * @param distance the desired distance in pixels
     */
    public void moveHorizontal(int distance){
        erase();
        xPosition += distance;
        draw();
    }

    /**
     * Move the circle vertically.
     * @param distance the desired distance in pixels
     */
    public void moveVertical(int distance){
        erase();
        yPosition += distance;
        draw();
    }

    /**
     * Change the size.
     * @param newDiameter the new size (in pixels). Size must be >=0.
     */
    public void changeSize(int newDiameter){
        erase();
        diameter = newDiameter;
        draw();
    }

    /**
     * Change the color. 
     * @param color the new color. Valid colors are "red", "yellow", "blue", "green",
     * "magenta" and "black".
     */
    public void changeColor(String newColor){
        color = newColor;
        draw();
    }
    /**
     * Create the puncture inside the Valley class
     */
    public void Coordinates(int x,int y){
        
        if(isVisible){
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, 
                new Ellipse2D.Double(x,y, 
                    diameter, diameter));
            canvas.wait(10);
        }
    }

    /*
     * Draw the circle with current specifications on screen.
     */
    public void draw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, 
                new Ellipse2D.Double(xPosition, yPosition, 
                    diameter, diameter));
            canvas.wait(10);
        }
    }

    /*
     * Erase the circle on screen.
     */
    private void erase(){
       
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
        
    }
}

