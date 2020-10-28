package main.patchwork;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Image {
    private Set<Forme> formes;

    public Image(){
        formes = new HashSet<Forme>();
    }

    public Set<Forme> getFormes(){
        return this.formes;
    }
    public double getAire(){
        int res = 0;
        Iterator i = this.formes.iterator();

        System.out.println("   "+"------------------------------------------- ");
        while(i.hasNext()){
            Forme forme = ((Forme) i.next());
            double aireForme = forme.getAire();

            if(forme instanceof Polygone)System.out.println(((Polygone) forme).area());
            System.out.println("   "+"Aire forme : " + aireForme);
            System.out.println("   "+"------------------------------------------- ");
            res += aireForme;
        }
        return res;
    }

    public boolean addForme(Forme f){
        return this.formes.add(f);
    }
}
