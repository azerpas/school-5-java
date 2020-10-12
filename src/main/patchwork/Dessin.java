package main.patchwork;

import java.util.HashSet;
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

    public double getAire(){
        return 0.0;
    }

    public Dessin getCopie(){
        return new Dessin(this.images);
    }

}
