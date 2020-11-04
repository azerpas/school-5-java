package main.patchwork;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a Fresque containing all the drawings (Dessin)
 */
public class Fresque {
    /**
     * Fresque drawings as Dessin entity
     */
    private Set<Dessin> dessins;

    public Fresque(){
        this.dessins = new HashSet<Dessin>();
    }
    
    /**
     * Get the drawings
     * @return get the drawings (Dessin) inside the fresque
     */
    public Set<Dessin> getDessins(){
        return this.dessins;
    }

    /**
     * Add a drawing
     * @param d a drawing (Dessin) entity
     * @return boolean - push success
     */
    public boolean addDessin(final Dessin d){
        return this.dessins.add(d);
    }

    @Override
    public String toString() {
        return "Fresque{" +
                "dessins=\n   " + dessins +
                "\n}";
    }

}
