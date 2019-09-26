
package shapes;
import java.util.*;
import java.awt.geom.Area;
public class Valley
{
    // instance variables - replace the example below with your own
    private Rectangle valle;
    private ArrayList<Vineyard> listaviñedo=new ArrayList<Vineyard>();
    private ArrayList<Rain> lluvia=new ArrayList<Rain>();
    private ArrayList<String> namesViñedos=new ArrayList<String>();
    private int width;
    private int height;
    private Trap creacion;
    private ArrayList<Integer> xinicial=new ArrayList<Integer>();
    private ArrayList<Integer> xfinal=new ArrayList<Integer>();
    private ArrayList<Trap> traps=new ArrayList<Trap>();
    private Vineyard viñedo;
    private int[] Higher;
    private int[] Lower;
    private int distance=0;
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
                System.out.println("En las coordenadas ingresadas ya se encuentra un viñedo");
                verificar=false;
                break;
            }
        }
        if(verificar){
            viñedo= new Vineyard(name,xi,xf);
            viñedo.moveVertical(height-10);
            listaviñedo.add(viñedo);
            xinicial.add(xi);
            xfinal.add(xf);
            namesViñedos.add(name);
        }
    }

    public void makeVisible(){
        valle.makeVisible();
        for(Vineyard viñedo: listaviñedo){
            viñedo.makeVisible();
        }
        for(Trap oneTrap: traps){
            oneTrap.makeVisible();
        }
       
        for(Rain llover: lluvia){
            llover.makeVisible();
        }
    }

    public void Zoom(String x){
        if(x=="+"){
            valle.changeSize(width+50,height+50);
            for(Vineyard viñedo: listaviñedo){
                viñedo.changeSize(50);
            }
            for(Trap oneTrap: traps){
                oneTrap.changeSize(10,50);
            }
            
            for(Rain llover: lluvia){
                llover.changeSize(10);
            } 
            
        }
        if(x=="-"){
            valle.changeSize(width-50,height-50);
            for(Vineyard viñedo: listaviñedo){
                viñedo.changeSize(-50);
            }
            for(Trap oneTrap: traps){
                oneTrap.changeSize(10,-50);
            }
            for(Rain llover: lluvia){
                llover.changeSize(10);
            } 

        }
    }

    /**
     * Close the vineyards with the name
     */
    public void closeVineyard(String name){
        for(int i=0;i<namesViñedos.size();i++){
            if(name==namesViñedos.get(i)){
                namesViñedos.remove(i);
                xinicial.remove(i);
                xfinal.remove(i);
                listaviñedo.get(i).makeInvisible();
                listaviñedo.remove(i);
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
        //higherEnd[1]=Math.abs(higherEnd[1]);
        //lowerEnd[0]=Math.abs(lowerEnd[0]-width);
        lowerEnd[1]=Math.abs(lowerEnd[1]-height);
        //lowerEnd[1]=Math.abs(lowerEnd[1]);
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
    public void makePuncture(int trap,int x){
        traps.get(trap-1).makePuncture(x,height);
        //ArrayList<Integer> huecosX=new ArrayList<Integer>();
        //ArrayList<Integer> huecosY=new ArrayList<Integer>();
        //x=Math.abs(x-width);
        //y=Math.abs(y-height);       
        //Puncture nuevoPuncture=new Puncture(x,y);
        //Trap nuevoTrap=new Trap(Higher,Lower);
        //if(traps.size()>0){
         //   if(nuevoTrap.verificateCoordinates(x,y)){
           //     huecosX.add(x);
           //     huecosY.add(y);
                //nuevoPuncture.Coordinates(x,y);
                //punctures.add(nuevoPuncture);
            //}else{
              //  System.out.println("no hay lona");
              //  System.out.println(x);
              //  System.out.println(y);
            //}
        //}else{
          //  System.out.println("No hay traps");
        //}
        
    }

    /**
     * Remove the punctures one by one
     */
    //public void removePuncture(int position){
      //  if(punctures.size()>0){
      //      for(int p=0;p<punctures.size();p++){
      //          if(p==position){
      //              punctures.remove(p);
      //              punctures.get(p).makeInvisible();
      //          }
      //      }
      //  }else{
      //      System.out.println("No hay hueco en esa coordenada");
      //  }
    //}

    public void startRain(int x){
        int i=0;
        ArrayList<Integer> posicion = new ArrayList<Integer>();
        posicion.add(x);
        posicion.add(0);
        System.out.println(posicion+""+posicion.get(0)+"//"+i);
        while(posicion.get(1)<height-10){
            //System.out.println("kasa");
            if(traps.size()>0 && traps.get(i).compararPosicion(posicion)){
              //  System.out.println("kasota");
                ArrayList<Rain> lista=new ArrayList<Rain>();
                lista=traps.get(i).rainTrap(posicion,height);
                for(Rain n:lista){
                    lluvia.add(n);
                }
                ArrayList<Integer> posicionLista= lista.get(lista.size()-1).posicion();
                posicion.set(0,posicionLista.get(0));
                posicion.set(1,posicionLista.get(1));
                
            }else{
              //  System.out.println("kasita");
                Rain gota =new Rain(posicion.get(0),(posicion.get(1)));
                gota.makeVisible();
                lluvia.add(gota);
                //System.out.println(lluvia.size());
                posicion.set(0,posicion.get(0));
                posicion.set(1,posicion.get(1)+1);
                if(i+1<traps.size()){
                    i++;
                }
            }
            
        }
    }
}

