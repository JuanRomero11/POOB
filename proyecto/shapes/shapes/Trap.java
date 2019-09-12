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
    private ArrayList<ArrayList<Integer>> matrizComparar;
    public Trap(int[]  higherEnd,int[] lowerEnd)
    {
        this.iniciales=higherEnd;
        this.finales=lowerEnd;
        matrizComparar=new ArrayList<ArrayList<Integer>>();
        int pendiente=(lowerEnd[1]-higherEnd[1])/(lowerEnd[0]-higherEnd[0]);
        int corte=(higherEnd[1]-(pendiente*higherEnd[0]));
        for(int j=0;j<10;j++){
            for(int i=higherEnd[0]-j;i<lowerEnd[0]-j;i++){
                ArrayList<Integer> localPost=new ArrayList<Integer>();
                localPost.add(i);
                localPost.add((i*pendiente)+corte);
                matrizComparar.add(localPost);
            }
        }
        isVisible = false;
    }

    private void draw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            int[] xpoints = { finales[0]-10, iniciales[0]-10,iniciales[0],finales[0] };
            int[] ypoints = { finales[1], iniciales[1], iniciales[1],finales[1]};
            canvas.draw(this,"blue", new Polygon(xpoints, ypoints, 4));
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

    public void moveHorizontal(int distance){
        erase();
        iniciales[0]=Math.abs(iniciales[0]-distance);
        finales[0]=Math.abs(iniciales[0]-distance);
        draw();
    }

    public void moveVertical(int distance){
        erase();
        iniciales[1]=Math.abs(iniciales[1]-distance);
        finales[1]=Math.abs(iniciales[1]-distance);
        draw();
    }

    public boolean validar(Trap trapComparar){
        boolean valorValidar=true;
        System.out.println(matrizComparar);
        for(int i=0;i<matrizComparar.size();i++){
           if(compararPosiciones(matrizComparar.get(i),trapComparar.matrizComparar)){
               valorValidar=false;
               break;
            }
        }
        return valorValidar;
    }
    public boolean compararPosiciones(ArrayList<Integer> posicion,ArrayList<ArrayList<Integer>> listaPosiciones){
        boolean valorValidar=false;
        for(int i=0;i<listaPosiciones.size();i++){
            if(posicion.get(0)==listaPosiciones.get(i).get(0) && posicion.get(1)==listaPosiciones.get(i).get(1)){
                valorValidar=true;
                break;
            }
        }
        return valorValidar;
    }
}
