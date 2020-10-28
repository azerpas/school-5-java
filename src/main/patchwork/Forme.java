package main.patchwork;

import java.util.Objects;
import java.util.Set;

public abstract class Forme{

    protected Point centre;

    public Forme(){
        this.centre = new Point();
    }

    public Forme(final Point centre){
        this.centre = centre;
    }

    public abstract double getAire();
    public abstract double getPerimetre();
    public abstract Set<Point> getPoints();

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
}