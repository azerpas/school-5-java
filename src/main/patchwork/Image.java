package main.patchwork;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(formes, image.formes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(formes);
    }

    @Override
    public String toString() {
        return "Image{" +
                "formes=\n         " + formes  +
                "\n      }";
    }
}
