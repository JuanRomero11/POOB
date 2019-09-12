package shapes;
import java.util.*;
import java.awt.geom.Area;
public class Valley
{
    // instance variables - replace the example below with your own
    private Rectangle valle;
    private ArrayList<Vineyard> listavi�edo=new ArrayList<Vineyard>();
    private ArrayList<String> names=new ArrayList<String>();
    private int width;
    private int height;
    private Trap creacion;
    private ArrayList<Integer> xinicial=new ArrayList<Integer>();
    private ArrayList<Integer> xfinal=new ArrayList<Integer>();
    private ArrayList<Trap> traps=new ArrayList<Trap>();
    private Vineyard vi�edo;
    public Valley(int width,int height)
    {
        valle= new Rectangle(width,height);
        this.width=width;
        this.height=height;
    }

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
            names.add(name);
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
    }

    public void closeVineyard(String name){
        for(int i=0;i<names.size();i++){
            if(name==names.get(i)){
                names.remove(i);
                xinicial.remove(i);
                xfinal.remove(i);
                listavi�edo.get(i).makeInvisible();
                listavi�edo.remove(i);
            }
        }
    }

    public void addTrap(int[] higherEnd,int[] lowerEnd){
        Trap nuevoTrap=new Trap(higherEnd,lowerEnd);
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

}
