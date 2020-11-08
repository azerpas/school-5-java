package main.patchwork;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/**
 * Represents an Image containing all the formes (Forme)
 */
public class Image {
    /**
     * Image forms as Forme entity
     */
    private Set<Forme> formes;

    /**
     * Image constructor
     */
    public Image(){
        formes = new HashSet<Forme>();
    }

    /**
     * Get forms
     * @return Set of Forme entity
     */
    public Set<Forme> getFormes(){
        return this.formes;
    }


    /**
     * Get Image area
     * @return res: double
     */
    public double getAire(){
        int res = 0;
        Iterator<Forme> i = this.formes.iterator();

        while(i.hasNext()){
            Forme forme = ((Forme) i.next());
            double aireForme = forme.getAire();

            res += aireForme;
        }
        return res;
    }

    /**
     * Add a form to current Set of Forme
     * @param f
     * @return
     */
    public boolean addForme(final Forme f){
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
        String fs = "";
        for (Forme forme : formes) {
            fs += forme.toString()+"\n";
        }
        return "Image{" +
                "formes=\n         " + fs  +
                "\n      }";
    }
}
