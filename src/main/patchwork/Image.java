package main.patchwork;

import java.util.HashSet;
import java.util.Set;

public class Image {
    private Set<Forme> formes;

    public Image(){
        formes = new HashSet<Forme>();
    }

    public Set<Forme> getFormes(){
        return this.formes;
    }
}
