package shapes;
import java.util.*;
import java.awt.geom.Area;
public class Valley
{
    private Rectangle valle;
    private ArrayList<Vineyard> listavinedo;
    private ArrayList<Rain> lluvia=new ArrayList<Rain>();
    private ArrayList<String> namesVinedos=new ArrayList<String>();
    private int width;
    private int height;
    private Trap creacion;
    private ArrayList<Integer> xinicial=new ArrayList<Integer>();
    private ArrayList<Integer> xfinal=new ArrayList<Integer>();
    private ArrayList<Trap> traps=new ArrayList<Trap>();
    private boolean booleano;
    public Valley(int width,int height)
    {
        valle= new Rectangle(width,height);
        this.width=width;
        this.height=height;
        listavinedo=new ArrayList<Vineyard>();
    }

    /**
     * Create the vineyards in the valley
     */
    public void openVineyard(String name,int xi,int xf){
        Vineyard vinedo;
        boolean verificar=true;
        booleano=true;
        for(int i=0;i<xinicial.size();i++){
            if((xi<0 || xf>=width) || (xi<=xfinal.get(i) && xfinal.get(i)<=xf) || (xi<=xinicial.get(i) && xinicial.get(i)<=xf) || (xinicial.get(i)<=xi && xf<=xfinal.get(i)) || (xi<=xinicial.get(i) && xfinal.get(i)<=xf)){
                System.out.println("En las coordenadas ingresadas ya se encuentra un vinedo");
                verificar=false;
                booleano=false;
                break;
            }
        }

        if(verificar){
            vinedo= new Vineyard(name,xi,xf);
            vinedo.moveVertical(height-10);
            listavinedo.add(vinedo);
            xinicial.add(xi);
            xfinal.add(xf);
            namesVinedos.add(name);
        }
    }

    /**
     * Make visible all the objects in the valley
     */
    public void makeVisible(){

        valle.makeVisible();
        for(Vineyard vinedo: listavinedo){
            vinedo.makeVisible();
        }
        for(Trap oneTrap: traps){
            oneTrap.makeVisible();
        }

        for(Rain llover: lluvia){
            llover.makeVisible();
        }
    }

    /**
     * Make invisible all the objects in the valley
     */
    public void makeInvisible(){

        valle.makeInvisible();
        for(Vineyard vinedo: listavinedo){
            vinedo.makeInvisible();
        }
        for(Trap oneTrap: traps){
            oneTrap.makeInvisible();
        }

        for(Rain llover: lluvia){
            llover.makeInvisible();
        }
    }

    /**
     * Make zoom to the valley, could be "+" or "-"
     */
    public void Zoom(String x){
        booleano =false;
        if(x=="+"){
            valle.changeSize(width+50,height+50);
            for(Vineyard vinedo: listavinedo){
                vinedo.changeSize(50);
            }
            for(Trap oneTrap: traps){
                oneTrap.changeSize(10,50);
            }

            for(Rain llover: lluvia){
                llover.changeSize(5);
            } 
            booleano =true;
        }
        if(x=="-"){
            valle.changeSize(width-50,height-50);
            for(Vineyard vinedo: listavinedo){
                vinedo.changeSize(-50);
            }
            for(Trap oneTrap: traps){
                oneTrap.changeSize(10,-50);
            }
            for(Rain llover: lluvia){
                llover.changeSize(-5);
            } 
            booleano =true;
        }
    }

    /**
     * Close the vineyards with the name
     */
    public void closeVineyard(String name){
        booleano = false;

        for(int i=0;i<namesVinedos.size();i++){
            booleano=true;
            if(name==namesVinedos.get(i)){
                namesVinedos.remove(i);
                xinicial.remove(i);
                xfinal.remove(i);
                listavinedo.get(i).makeInvisible();
                listavinedo.remove(i);
            }
        }

    }

    /**
     * Create the traps in the valley
     */
    public void addTrap(int[] higherEnd,int[] lowerEnd){
        booleano=false;
        if(0<=higherEnd[0] && higherEnd[0]<= width && 0<=higherEnd[1] && higherEnd[1]<= height &&  0<=lowerEnd[0] && lowerEnd[0]<= width && 0<=lowerEnd[1] && lowerEnd[1]<= height){

            Trap nuevoTrap=new Trap(higherEnd,lowerEnd);
            higherEnd[1]=Math.abs(higherEnd[1]-height);
            for(Vineyard oneVineyard: listavinedo){
                nuevoTrap.colorVinedo(oneVineyard.posiciones()[0],oneVineyard.posiciones()[1],oneVineyard.getColor());
            }
            lowerEnd[1]=Math.abs(lowerEnd[1]-height);
            if(traps.size()==0){
                booleano =true;
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
                    booleano =true;
                }
            }
        }

    }

    public String[] rainFalls(){
        String[] vinedosRegandose=new String[lluvia.size()];
        int cont=0;
        for(int i=0;i<listavinedo.size();i++){
            System.out.println("una "+(height-10)+"dos"+lluvia.get(lluvia.size()-1).posicion().get(1));
            if(listavinedo.get(i).posiciones()[0]<= lluvia.get(lluvia.size()-1).posicion().get(0) &&  lluvia.get(lluvia.size()-1).posicion().get(0)<=listavinedo.get(i).posiciones()[1] && height-31== lluvia.get(lluvia.size()-1).posicion().get(1)){
                vinedosRegandose[cont]=new String(listavinedo.get(i).getName());
                cont++;
            }
        }
        System.out.println(vinedosRegandose[0]);
        return vinedosRegandose;
    }

    /**
     * Remove the traps one by one
     */
    public void removeTrap(int position){
        booleano=true;
        try{
            traps.get(position-1).makeInvisible();
            traps.get(position-1).remove();
            traps.remove(position-1);
        }catch(Exception e){
            booleano=false;
        }
    }

    /**
     * Create the punctures in the traps
     */
    public void makePuncture(int trap,int x){
        booleano=true;
        try{
            traps.get(trap-1).makePuncture(x,height);  
        }catch(Exception e){
            booleano=false;
        }
    }

    /**
     * Remove the punctures one by one
     */
    public void patchPuncture(int trapPosition,int position){
        booleano=true;
        try{
            traps.get(trapPosition-1).removePuncture(position-1);
        }catch(Exception e){
            booleano=false;
        }
    }

    /**
     * Make the rain in a x position in to the valley
     */
    public void startRain(int x){
        booleano=false;
        if(x>=0 && x<=width){
            booleano=true;
            int i=0;
            ArrayList<Integer> posicion = new ArrayList<Integer>();
            posicion.add(x);
            posicion.add(0);
            System.out.println(posicion+""+posicion.get(0)+"//"+i);
            while(posicion.get(1)<height-10){
                if(traps.size()>0 && traps.get(i).compararPosicion(posicion)){
                    ArrayList<Rain> lista=new ArrayList<Rain>();
                    lista=traps.get(i).rainTrap(posicion,height);
                    for(Rain n:lista){
                        lluvia.add(n);
                    }
                    ArrayList<Integer> posicionLista= lista.get(lista.size()-1).posicion();
                    posicion.set(0,posicionLista.get(0));
                    posicion.set(1,posicionLista.get(1));
                }else{
                    Rain gota =new Rain(posicion.get(0),(posicion.get(1)));                
                    lluvia.add(gota);                
                    posicion.set(0,posicion.get(0));
                    posicion.set(1,posicion.get(1)+1);
                    if(i+1<traps.size()){
                        i++;
                    }
                }

            }
        }

    }

    public boolean ok(){
        return this.booleano; 
    }
}

