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

public class Rain{

    public static double PI=3.1416;

    private int diameter;
    private int xPosition;
    private int yPosition;
    private String color;
    private Circle g;
    private boolean isVisible;
    /**
     * Create a new circle at default position with default color.public Puncture(int xposition,int yposition,ArrayList<ArrayList<Integer>> huecos){
     */
    public Rain(int xposition,int yposition,int height){

        diameter = 5;
        this.xPosition = xposition;
        this.yPosition = (yposition-height)*-1;///-20
        color = "blue";
        g = new Circle(xposition,yPosition,height);
        
        isVisible = false;
    }
 
    
    public void fall(Valley valley,int heigth){
          makeVisible();
         double m=0;
         while(yPosition>heigth-10){
            
            if (m>0){
                
                g.moveRight();
                xPosition+=1;
            }else if(m<0){
                g.moveLeft();
                xPosition-=1;
                
            }else{
                g.moveDown();
                yPosition-=1;
            }
            
            double n[] =valley.neartrap(xPosition,yPosition);
            
            m=n[0];
            System.out.println(m);
            }   
    
    
    }
    
    
    
    
    
    /**public ArrayList<Integer> posicion(){
        ArrayList<Integer> posiciones=new ArrayList<Integer>();
        posiciones.add(xPosition);
        posiciones.add(yPosition);
        return posiciones;
    }*/
    
    
    
    
    
    
    /**
     * Make this circle visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        g.makeVisible();
        
    }

    /**
     * Make this circle invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible(){
        g.makeInvisible();
    }

}

