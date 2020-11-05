package main.patchwork;

import java.util.Objects;
import java.util.Set;

/**
 * An abstract class representing all the Geometric Forms of an Image
 */
public abstract class Forme{

    /**
     * Form center
     */
    protected Point centre;

    /**
     * Form constructor 
     */
    public Forme(){
        this.centre = new Point();
    }
    
    /**
     * Form constructor with a center
     * @param centre
     */
    public Forme(final Point centre){
        this.centre = centre;
    }

    /**
     * Get the form area
     * @return the area
     */
    public abstract double getAire();
    /**
     * Get the form perimeter
     * @return the perimeter
     */
    public abstract double getPerimetre();
    /**
     * Get the form points
     * @return a set of points
     */
    public abstract Set<Point> getPoints();

    /**
     * Get the form center
     * @return the center as a Point entity
     */
    public Point getCentre(){
        return this.centre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Forme forme = (Forme) o;
        return Objects.equals(centre, forme.centre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(centre);
    }

    @Override
    public String toString() {
        return "Forme{" +
                "centre=" + centre +
                '}';
    }
}