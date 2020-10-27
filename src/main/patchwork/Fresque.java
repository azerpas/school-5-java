package main.patchwork;

import java.util.HashSet;
import java.util.Set;

public class Fresque {
    private Set<Dessin> dessins;

    public Fresque(){
        this.dessins = new HashSet<Dessin>();
    }
 
    public Set<Dessin> getDessins(){
        return this.dessins;
    }

    public boolean addDessin(Dessin d){
        return this.dessins.add(d);
    }
}
