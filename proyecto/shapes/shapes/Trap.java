
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
        superficialPunctures=new ArrayList<ArrayList<Integer>>();
        pendiente=(lowerEnd[1]-higherEnd[1])/(lowerEnd[0]-higherEnd[0]);
        corte=(higherEnd[1]-(pendiente*higherEnd[0]));
        color="black";
        ArrayList<Integer> lower=new ArrayList<Integer>();
        if(pendiente<0){
            lower.add(lowerEnd[0]);
            lower.add(lowerEnd[1]);
            superficialPunctures.add(lower);
        }else{
            lower.add(higherEnd[0]);
            lower.add(higherEnd[1]);
            superficialPunctures.add(lower);
        }
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

    public void colorViñedo(int xinicial,int xfinal,String newColor){
        System.out.println(newColor);
        if((xinicial<=iniciales[0] && iniciales[0]<=xfinal) && (xinicial<=finales[0] && finales[0]<=xfinal)){
            this.changeColor(newColor);
        }
    }
    public void removePuncture(int posicionHueco){
        ArrayList<ArrayList<Integer>> huecos=new ArrayList<ArrayList<Integer>>();
        for(int i=0;i<punctures.size();i++){
            if(posicionHueco==i){
                huecos= new ArrayList<ArrayList<Integer>>(punctures.get(i).huecos());
                punctures.get(i).makeInvisible();
                punctures.remove(i);
            }
        }
        for(int j=0;j<superficialPunctures.size();j++){
            for(int k=0;k<huecos.size();k++){
                if(superficialPunctures.get(j).get(0) ==huecos.get(k).get(0) && superficialPunctures.get(j).get(1) ==huecos.get(k).get(1)){
                    superficialPunctures.remove(j);
            }
            
        }
       for(int z=0;z<huecos.size();z++){
           matrizComparar.add(huecos.get(z));
        }
    }
}
    private void drawTrap(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            int[] xpoints = { finales[0]-distance, iniciales[0]-distance,iniciales[0],finales[0] };
            int[] ypoints = { finales[1], iniciales[1], iniciales[1],finales[1]};
            canvas.draw(this,color, new Polygon(xpoints, ypoints, 4));
            canvas.wait(10);
        }
    }

    public void changeColor(String newColor){
        color = newColor;
        drawTrap();
    }

    public void makeVisible(){
        isVisible = true;
        drawTrap();
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
        drawTrap();
    }

    public void moveVertical(int distance){
        erase();
        iniciales[1]=Math.abs(iniciales[1]+distance);
        finales[1]=Math.abs(finales[1]+distance);
        drawTrap();
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
        int r=0;
        if(pendiente<0){
            r=20;
        }
        Puncture punto=new Puncture(hueco.get(1).get(0),(hueco.get(1).get(1)-height-r)*-1,hueco);
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
                    parcial.set(0,superficialPunctures.get(k).get(0));
                    parcial.set(1,superficialPunctures.get(k).get(1));
                    x=parcial.get(0);
                    z=k;
                }
            }
            ordenada.add(parcial);
            if(pendiente<0){
                superficialPunctures.remove(z-1);
            }else{
                superficialPunctures.remove(z);
            }
        }

        if(pendiente>0){
            for(int j=ordenada.get(ordenada.size()-1).get(0);j<posicionInicial.get(0);j++){
                for(int k=superficialTrap.size()-1;k>0;k--){
                    if(j==superficialTrap.get(k).get(0)){
                        Rain rain =new Rain(j,((height-superficialTrap.get(k).get(1))));
                        rains.add(rain);
                    }
                }
            }
            Rain base=rains.get(rains.size()-1);
            rains.set(rains.size()-1,rains.get(0));
            rains.set(0,base);
        }
        else{

            for(int j=posicionInicial.get(0);j<ordenada.get(0).get(0);j++){
                for(int k=0;k<superficialTrap.size();k++){
                    if(j==superficialTrap.get(k).get(0)){
                        Rain rain =new Rain(j,((height-superficialTrap.get(k).get(1))));            
                        rains.add(rain);
                    }
                }
            }
        }

        return rains;
    }

    public void changeSize(int newHeight,int x) {
        erase();
        distance = newHeight;
        moveVertical(x); 
        drawTrap();
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

    public String getColor(){
        return this.color;
    }

    public void setColor(String color){
        this.color=color;
    }
}
