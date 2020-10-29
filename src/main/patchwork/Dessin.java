package main.patchwork;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class Dessin {
    private Set<Image> images;

    public Dessin(){
        this.images = new HashSet<Image>();
    }

    public Dessin(final Set<Image> images){
        this.images = images;
    }

    public Set<Image> getImages(){
        return this.images;
    }

    public boolean addImage(Image i){
        return this.images.add(i);
    }

    public double getAire(){
        int res = 0;
        Iterator<Image> i = this.images.iterator();
        while(i.hasNext()){
            double aireImage = ((Image) i.next()).getAire();
            System.out.println("Aire Image : " + aireImage);
            res += aireImage;
        }
        return res;
    }

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
        return "Dessin{" +
                "images=\n      " + images +
                "\n   }";
    }
}
