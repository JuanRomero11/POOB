
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
    private int pendiente;
    private int corte;
    private ArrayList<ArrayList<Integer>> matrizComparar;
    private ArrayList<Puncture> punctures;
    public Trap(int[]  higherEnd,int[] lowerEnd)
    {
        this.iniciales=higherEnd;
        this.finales=lowerEnd;
        matrizComparar=new ArrayList<ArrayList<Integer>>();
        pendiente=(lowerEnd[1]-higherEnd[1])/(lowerEnd[0]-higherEnd[0]);
        corte=(higherEnd[1]-(pendiente*higherEnd[0]));
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
            canvas.draw(this,"black", new Polygon(xpoints, ypoints, 4));
            canvas.wait(10);
        }
    }

    public void changeColor(String newColor){
        color = newColor;
        draw();
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

    public boolean compararPosicion(ArrayList<Integer> posicion){
        boolean valorValidar=false;
        if(matrizComparar.contains(posicion)){
            valorValidar=true;
        }
        return valorValidar;
    }

    public ArrayList<Integer> puntofinal(ArrayList<Integer> puntoInicial){
        ArrayList<Integer> puntofinal=new ArrayList<Integer> ();
        if(matrizComparar.contains(puntoInicial)){
            System.out.println("entre");
        }
        for(int i=0;i<matrizComparar.size();i++){
            if(matrizComparar.get(i).get(0)==puntoInicial.get(0) && matrizComparar.get(i).get(1)==puntoInicial.get(1)){
                for(int j=puntoInicial.get(0);j<matrizComparar.get(i).get(0)+1;j++){
                    ArrayList<Integer> localPost=new ArrayList<Integer>();
                    localPost.add(j);
                    localPost.add((j*pendiente)+corte);
                    if(matrizComparar.contains(localPost)==false){
                        puntofinal=localPost;
                        break;
                    }else if(j==matrizComparar.get(i).get(0) && (matrizComparar.contains(localPost))){
                        puntofinal=matrizComparar.get(matrizComparar.size()-1);
                        break;
                    }
                }
            }
        }
        System.out.println(puntofinal);
        return puntofinal;
    }

    public void remove(){
        erase();
        isVisible = false;
    }

    /**
     * return a boolean and verifies if the puncture coordinate is inside the traps coordinate
     * @return
     */
    public boolean verificateCoordinates(int x,int y){
        Boolean verificar=false;
        for(int i=0;i<matrizComparar.size();i++){
            System.out.println(matrizComparar.get(i));
            if(x==matrizComparar.get(i).get(0) && y==matrizComparar.get(i).get(1)){
                matrizComparar.remove(i);
                verificar=true;
                break;
            }
        }
        return verificar;
    }
}
