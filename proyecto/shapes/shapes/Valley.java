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
        Vineyard vi�edo;
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

        for(Rain llover: lluvia){
            llover.makeVisible();
        }
    }
    public void makeInvisible(){
        valle.makeInvisible();
        for(Vineyard vi�edo: listavi�edo){
            vi�edo.makeInvisible();
        }
        for(Trap oneTrap: traps){
            oneTrap.makeInvisible();
        }

        for(Rain llover: lluvia){
            llover.makeInvisible();
        }
    }
    public void Zoom(String x){
        if(x=="+"){
            valle.changeSize(width+50,height+50);
            for(Vineyard vi�edo: listavi�edo){
                vi�edo.changeSize(50);
            }
            for(Trap oneTrap: traps){
                oneTrap.changeSize(10,50);
            }

            for(Rain llover: lluvia){
                llover.changeSize(5);
            } 

        }
        if(x=="-"){
            valle.changeSize(width-50,height-50);
            for(Vineyard vi�edo: listavi�edo){
                vi�edo.changeSize(-50);
            }
            for(Trap oneTrap: traps){
                oneTrap.changeSize(10,-50);
            }
            for(Rain llover: lluvia){
                llover.changeSize(-5);
            } 

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
        
        //higherEnd[0]=Math.abs(higherEnd[0]-width);
        higherEnd[1]=Math.abs(higherEnd[1]-height);
        //higherEnd[1]=Math.abs(higherEnd[1]);
        //lowerEnd[0]=Math.abs(lowerEnd[0]-width);
        for(Vineyard oneVineyard: listavi�edo){
            nuevoTrap.colorVi�edo(oneVineyard.posiciones()[0],oneVineyard.posiciones()[1],oneVineyard.getColor());
        }
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
    public String[] rainFalls(){
        String[] vi�edosRegandose=new String[lluvia.size()];
        int cont=0;
        for(int i=0;i<listavi�edo.size();i++){
            if(listavi�edo.get(i).posiciones()[0]<= lluvia.get(lluvia.size()-1).posicion().get(0) &&  lluvia.get(lluvia.size()-1).posicion().get(0)<=listavi�edo.get(i).posiciones()[1] && listavi�edo.get(i).posiciones()[1]== lluvia.get(lluvia.size()-1).posicion().get(1)+2){
                vi�edosRegandose[cont]=new String(listavi�edo.get(i).getName());
                cont++;
            }
        }
        System.out.println(vi�edosRegandose[0]);
        return vi�edosRegandose;
    }
    /**
     * Remove the traps one by one
     */
    public void removeTrap(int position){
        traps.get(position-1).makeInvisible();
        traps.get(position-1).remove();
        traps.remove(position-1);
    }

    /**
     * Create the punctures in the traps
     */
    public void makePuncture(int trap,int x){
        traps.get(trap-1).makePuncture(x,height);     
    }

    /**
     * Remove the punctures one by one
     */
    public void patchPuncture(int trapPosition,int position){
      traps.get(trapPosition-1).removePuncture(position-1);
    }

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

