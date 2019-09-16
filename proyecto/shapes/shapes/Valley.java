
package shapes;
import java.util.*;
import java.awt.geom.Area;
public class Valley
{
    // instance variables - replace the example below with your own
    private Rectangle valle;
    private ArrayList<Vineyard> listavi�edo=new ArrayList<Vineyard>();
    private ArrayList<Rain> lluvia=new ArrayList<Rain>();
    private ArrayList<String> namesVi�edos=new ArrayList<String>();
    private int width;
    private int height;
    private Trap creacion;
    private ArrayList<Integer> xinicial=new ArrayList<Integer>();
    private ArrayList<Integer> xfinal=new ArrayList<Integer>();
    private ArrayList<Trap> traps=new ArrayList<Trap>();
    private ArrayList<Puncture> punctures=new ArrayList<Puncture>();
    private Vineyard vi�edo;
    private int[] Higher;
    private int[] Lower;
    ArrayList<Integer> huecosX=new ArrayList<Integer>();
    ArrayList<Integer> huecosY=new ArrayList<Integer>();
    public Valley(int width,int height)
    {
        valle= new Rectangle(width,height);
        this.width=width;
        this.height=height;
    }
    /**
     * Create the vineyards in the valley
     */
    public void openVineyard(String name,int xi,int xf){
        boolean verificar=true;
        for(int i=0;i< xinicial.size();i++){
            if((xi<=xfinal.get(i) && xfinal.get(i)<=xf) || (xi<=xinicial.get(i) && xinicial.get(i)<=xf) || (xinicial.get(i)<=xi && xf<=xfinal.get(i)) || (xi<=xinicial.get(i) && xfinal.get(i)<=xf)){
                System.out.println("En las coordenadas ingresadas ya se encuentra un vi�edo");
                verificar=false;
                break;
            }
        }
        if(verificar){
            vi�edo= new Vineyard(name,xi,xf);
            vi�edo.moveVertical(height-10);
            listavi�edo.add(vi�edo);
            xinicial.add(xi);
            xfinal.add(xf);
            namesVi�edos.add(name);
        }
    }

    public void makeVisible(){
        valle.makeVisible();
        for(Vineyard vi�edo: listavi�edo){
            vi�edo.makeVisible();
        }
        for(Trap oneTrap: traps){
            oneTrap.makeVisible();
        }
        for(Puncture onePuncture: punctures){
            onePuncture.makeVisible();
        }
        for(Rain llover: lluvia){
            llover.makeVisible();
        }
    }
    /**
     * Close the vineyards with the name
     */
    public void closeVineyard(String name){
        for(int i=0;i<namesVi�edos.size();i++){
            if(name==namesVi�edos.get(i)){
                namesVi�edos.remove(i);
                xinicial.remove(i);
                xfinal.remove(i);
                listavi�edo.get(i).makeInvisible();
                listavi�edo.remove(i);
            }
        }
    }
    /**
     * Create the traps in the valley
     */
    public void addTrap(int[] higherEnd,int[] lowerEnd){
        Trap nuevoTrap=new Trap(higherEnd,lowerEnd);
        Higher = higherEnd;
        Lower = lowerEnd;
        //higherEnd[0]=Math.abs(higherEnd[0]-width);
        higherEnd[1]=Math.abs(higherEnd[1]-height);
        //lowerEnd[0]=Math.abs(lowerEnd[0]-width);
        lowerEnd[1]=Math.abs(lowerEnd[1]-height);
        if(traps.size()==0){
            traps.add(nuevoTrap);
        }else{
            boolean contador=true;
            int iterador=0;
            while(contador && iterador!=traps.size()){
                if(nuevoTrap.validar(traps.get(iterador))){
                    iterador++;
                }else{
                    contador=false;
                }
            }
            if(contador){
                traps.add(nuevoTrap);
            }
        }
    }
    /**
     * Remove the traps one by one
     */
    public void removeTrap(int position){
        traps.get(position).remove();
        traps.remove(position);
        traps.get(position).makeInvisible();
    }
    /**
     * Create the punctures in the traps
     */
    public void makePuncture(int x,int y){
        //x=Math.abs(x-width);
        y=Math.abs(y-height);       
        Puncture nuevoPuncture=new Puncture(x,y);
        Trap nuevoTrap=new Trap(Higher,Lower);
        if(traps.size()>0){
            if(nuevoTrap.verificateCoordinates(x,y)){
                huecosX.add(x);
                huecosY.add(y);
                nuevoPuncture.Coordinates(x,y);
                punctures.add(nuevoPuncture);
            }else{
                System.out.println("no hay lona");
                System.out.println(x);
                System.out.println(y);
            }
        }else{
            System.out.println("No hay traps");
        }

    }
    /**
     * Remove the punctures one by one
     */
    public void removePuncture(int position){
        if(punctures.size()>0){
            for(int p=0;p<punctures.size();p++){
                if(p==position){
                    punctures.remove(p);
                    punctures.get(p).makeInvisible();
                }
            }
        }else{
            System.out.println("No hay hueco en esa coordenada");
        }
    }
    public void startRain(int x){
       ArrayList<Integer> posicionLados=new ArrayList<Integer>();
       posicionLados.add(x);
       posicionLados.add(x+10);
       for(int k=0;k<posicionLados.size();k++){
        ArrayList<Integer> posicionRectangle=new ArrayList<Integer>();
        posicionRectangle.add(posicionLados.get(k));
        posicionRectangle.add(0);
        ArrayList<Integer> posicionInicial=new ArrayList<Integer>();
        ArrayList<Integer> posicionFinal=new ArrayList<Integer>();
        while(posicionRectangle.get(1)!=height-10 ){
            for(int i=0;i<traps.size();i++){
                if(traps.get(i).compararPosicion(posicionRectangle)){
                    Rain lluviaRectangle= new Rain(posicionRectangle.get(0),Math.abs(posicionRectangle.get(1)-height));
                    lluvia.add(lluviaRectangle);
                    posicionInicial=posicionRectangle;
                    posicionInicial.set(1,Math.abs(posicionInicial.get(1)-height));
                    posicionFinal=traps.get(i).puntofinal(posicionInicial);
                    posicionFinal.set(1,Math.abs(posicionFinal.get(1)-height));
                    Rain lluviaTrap= new Rain(posicionInicial,posicionFinal);
                    lluvia.add(lluviaTrap);
                    posicionRectangle.set(0,posicionFinal.get(0));
                    posicionRectangle.set(1,posicionFinal.get(1));
                    break;
                }
                else if(i+1==traps.size()){
                    posicionRectangle.set(1,posicionRectangle.get(1)+1);
                    break;
                }
            }
            
        }
    }
    }
}


