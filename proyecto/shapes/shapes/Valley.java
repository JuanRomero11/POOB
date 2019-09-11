package shapes;
import java.util.*;
import java.awt.geom.Area;
public class Valley
{
    // instance variables - replace the example below with your own
    private String [] colores= {"green","magenta","red","white"};
    private int numRandon;
    private Rectangle valle;
    private Rectangle viñedo;
    private ArrayList<Rectangle> listaviñedo=new ArrayList<Rectangle>();
    private ArrayList<String> names=new ArrayList<String>();
    private int y;
    private Trap creacion;
    private ArrayList<Integer> xinicial=new ArrayList<Integer>();
    private ArrayList<Integer> xfinal=new ArrayList<Integer>();
    private ArrayList<Trap> traps=new ArrayList<Trap>();
    private ArrayList<ArrayList<Integer>> post = new ArrayList<ArrayList<Integer>>();
    private int[] lowerEnd=new int[100];
    private int[] higherEnd=new int[100];
    public Valley(int width,int height)
    {
        valle= new Rectangle(width,height);
        y=height;
        valle.makeVisible();
    }
    public void openVineyard(String name,int xi,int xf){
        if(xinicial.size()!=0){
            for(int i=0;i< xinicial.size();i++){
                if((xi<=xfinal.get(i) && xfinal.get(i)<=xf) || (xi<=xinicial.get(i) && xinicial.get(i)<=xf) || (xinicial.get(i)<=xi && xf<=xfinal.get(i)) || (xi<=xinicial.get(i) && xfinal.get(i)<=xf)){
                   System.out.println("En las coordenadas ingresadas ya se encuentra un viñedo");
                   break;
                }else{
                    viñedo= new Rectangle(xf-xi,10);
                    numRandon = (int) Math.round(Math.random() * 4 ); 
                    viñedo.changeColor(colores[numRandon]);
                    viñedo.moveHorizontal(xi);
                    viñedo.moveVertical(y-10);
                    viñedo.makeVisible();
                    listaviñedo.add(viñedo);
                    xinicial.add(xi);
                    xfinal.add(xf);
                    names.add(name);
                    break;
                }
            }
        }else{
            viñedo= new Rectangle(xf-xi,10);
            numRandon = (int) Math.round(Math.random() * 4 ); 
            viñedo.changeColor(colores[numRandon]);
            viñedo.moveHorizontal(xi);
            viñedo.moveVertical(y-10);
            viñedo.makeVisible();
            listaviñedo.add(viñedo);
            xinicial.add(xi);
            xfinal.add(xf);
            names.add(name);
        }
    }
    public void closeVineyard(String name){
        for(int i=0;i<names.size();i++){
            if(name==names.get(i)){
                names.remove(i);
                xinicial.remove(i);
                xfinal.remove(i);
                listaviñedo.get(i).makeInvisible();
                listaviñedo.remove(i);
            }
        }
    }
    public void addTrap(int[] lowerEnd,int[] higherEnd){
        ArrayList<ArrayList<Integer>> matrizPost=new ArrayList<ArrayList<Integer>>();
        int pendiente=(higherEnd[1]-lowerEnd[1])/(higherEnd[0]-lowerEnd[0]);
        int corte=(higherEnd[1]-(pendiente*higherEnd[0]));
        for(int i=lowerEnd[0];i<higherEnd[0];i++){
            ArrayList<Integer> localPost=new ArrayList<Integer>();
            localPost.add(i);
            localPost.add((i*pendiente)+corte);
            matrizPost.add(localPost);
        }
        if(validar(matrizPost)){
            guardar(matrizPost);
            crear(lowerEnd,higherEnd);
        }else{
            System.out.println("NO SE PUEDE AGREGAR");
        }
    }
    public boolean validar(ArrayList<ArrayList<Integer>> matrizComparar){
        boolean valorValidar=true;
        System.out.println("seraquesi");
        for(int i=0;i<post.size();i++){
            for(int j=0;j<matrizComparar.size();j++){
                System.out.println("seraquesi");
                System.out.println("x1"+ " " + matrizComparar.get(j).get(0)+" "+"x2"+" "+post.get(i).get(0)+" "+"y1"+" "+matrizComparar.get(j).get(1)+" "+"y2"+" "+post.get(i).get(1));
                if(matrizComparar.get(j).get(0)==post.get(i).get(0) && matrizComparar.get(j).get(1)==post.get(i).get(1)){
                    valorValidar= false;
                }
            }
        }
        return valorValidar;
    }
    public void guardar(ArrayList<ArrayList<Integer>> matrizGuardar){
        for(int i=0;i<matrizGuardar.size();i++){
            post.add(matrizGuardar.get(i));
        }
    }
    public void crear(int[] lowerEnd,int[] higherEnd){
        this.lowerEnd=lowerEnd;
        this.higherEnd=higherEnd;
        creacion= new Trap(lowerEnd,higherEnd);
        traps.add(creacion);
        creacion.makeVisible();
    }
}
