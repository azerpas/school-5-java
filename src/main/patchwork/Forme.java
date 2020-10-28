package main.patchwork;

import java.util.Objects;

public abstract class Forme{

    protected Point position;

    public Forme(){
        this.position = new Point();
    }

    public Forme(Point position) {
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }

    public abstract double getAire();
    public abstract double getPerimetre();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Forme forme = (Forme) o;
        return Objects.equals(position, forme.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}