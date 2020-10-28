package main.patchwork;

import java.util.HashSet;
import java.util.Iterator;
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
        Iterator i = this.images.iterator();
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

}
