package main.patchwork;

public abstract class Forme{

    protected Point position;

    public Forme(Point position) {
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }

    public abstract double getAire();
    public abstract double getPerimetre();
}