package main.patchwork;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/**
 * A class representing a Drawing (Dessin)
 */
public class Dessin {
    /**
     * Drawing images set
     */
    private Set<Image> images;

    /**
     * Drawing constructor
     */
    public Dessin(){
        this.images = new HashSet<Image>();
    }

    /**
     * Drawing constructor with an images parameter
     * @param images
     */
    public Dessin(final Set<Image> images){
        this.images = images;
    }

    /**
     * Getting drawing images as a Set of Image
     * @return
     */
    public Set<Image> getImages(){
        return this.images;
    }

    /**
     * Add an Image entity to set of current images
     * @param i
     * @return
     */
    public boolean addImage(final Image i){
        return this.images.add(i);
    }

    /**
     * Get drawing area
     * @return res: double
     */
    public double getAire(){
        int res = 0;
        Iterator<Image> i = this.images.iterator();
        while(i.hasNext()){
            double aireImage = ((Image) i.next()).getAire();
            res += aireImage;
        }
        return res;
    }

    /**
     * Get a drawing copy
     * @return Dessin entity
     */
    public Dessin getCopie(){
        return new Dessin(this.images);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dessin dessin = (Dessin) o;
        return Objects.equals(images, dessin.images);
    }

    @Override
    public int hashCode() {
        return Objects.hash(images);
    }

    @Override
    public String toString() {
        String is = "";
        for (Image image : images) {
            is += "\n      "+image.toString();
        }
        return "Dessin{" +
                "images=" + is +
                "\n   }";
    }
}
