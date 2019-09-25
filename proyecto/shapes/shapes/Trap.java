
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
    private int[] iniciales;
    private int[] finales;
    private boolean isVisible;
    private int pendiente;
    private int distance=10;
    private int corte;
    private ArrayList<ArrayList<Integer>> matrizComparar;
    private ArrayList<Puncture> punctures;
    private ArrayList<ArrayList<Integer>> superficialPunctures;
    private ArrayList<ArrayList<Integer>> superficialTrap;
    public Trap(int[]  higherEnd,int[] lowerEnd)
    {
        this.iniciales=higherEnd;
        this.finales=lowerEnd;
        matrizComparar=new ArrayList<ArrayList<Integer>>();
        punctures=new ArrayList<Puncture>();
        superficialTrap=new ArrayList<ArrayList<Integer>>();
        pendiente=(lowerEnd[1]-higherEnd[1])/(lowerEnd[0]-higherEnd[0]);
        corte=(higherEnd[1]-(pendiente*higherEnd[0]));
        ArrayList<Integer> lower=new ArrayList<Integer>();
        lower.add(lowerEnd[0]);
        lower.add(lowerEnd[1]);
        superficialTrap.add(lower);
        for(int j=0;j<10;j++){
            for(int i=higherEnd[0]-j;i<lowerEnd[0]-j;i++){
                ArrayList<Integer> localPost=new ArrayList<Integer>();
                localPost.add(i);
                localPost.add((i*pendiente)+corte);
                matrizComparar.add(localPost);
                System.out.println(localPost);
                if(j==0){
                    superficialTrap.add(localPost);
                }
            }
        }
        isVisible = false;
    }

    private void draw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            int[] xpoints = { finales[0]-distance, iniciales[0]-distance,iniciales[0],finales[0] };
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
        for(int i=0;i<punctures.size();i++){
            punctures.get(i).makeVisible();
        }
    }

    public void makeInvisible(){
        erase();
        isVisible = false;
        for(int i=0;i<punctures.size();i++){
            punctures.get(i).makeInvisible();
        }
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
        iniciales[1]=Math.abs(iniciales[1]+distance);
        finales[1]=Math.abs(finales[1]+distance);
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

    public void makePuncture(int x,int height){
        System.out.println(x);
        ArrayList<ArrayList<Integer>> hueco=new ArrayList<ArrayList<Integer>>();
        for(int i=0;i<matrizComparar.size();i++){
            if(x==matrizComparar.get(i).get(0)){
                hueco.add(matrizComparar.get(i));
                matrizComparar.remove(i);
                System.out.println(matrizComparar.get(i));
            }
        }
        Puncture punto=new Puncture(hueco.get(1).get(0),(hueco.get(1).get(1)-height)*-1,hueco);
        punctures.add(punto);
   
    }
    
    public boolean compararPosicion(ArrayList<Integer> posicion){
        boolean valorValidar=false;
        if(matrizComparar.contains(posicion)){
            valorValidar=true;
        }
        return valorValidar;
    }

    public ArrayList<Rain> rainTrap(ArrayList<Integer> posicionInicial,int height){
        ArrayList<Rain> rains=new ArrayList<Rain>();
        for(int i=0;i<punctures.size();i++){
            superficialPunctures.add(punctures.get(i).punctureSuperficial());
        }
        ArrayList<ArrayList<Integer>> ordenada=new ArrayList<ArrayList<Integer>>();
        int z=0;
        while(superficialPunctures.size()!=0){
            ArrayList<Integer> parcial=superficialPunctures.get(0);
            int x=parcial.get(0);
            for(int k=1;k<superficialPunctures.size();k++){
                if(x>superficialPunctures.get(k).get(0)){
                    parcial=superficialPunctures.get(k);
                    x=parcial.get(0);
                    z=k;
                }
            }
            ordenada.add(parcial);
            superficialPunctures.remove(z);
        }
        superficialPunctures=ordenada;
        for(int j=posicionInicial.get(0);j<superficialPunctures.get(0).get(superficialPunctures.size()-1);j++){
            for(int k=0;k<superficialTrap.size();k++){
                if(j==superficialTrap.get(k).get(0)){
                    Rain rain =new Rain(j,superficialTrap.get(k).get(1));
                    rain.makeVisible();
                    rains.add(rain);
                }
            }
        }

        return rains;
    }

    public void changeSize(int newHeight,int x) {
        erase();
        distance = newHeight;
        moveVertical(x); 
        draw();
        for(Puncture i: punctures){
            i.changeSize(5);
        }
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
