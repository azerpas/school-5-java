package main.patchwork;

public class Cercle extends Forme{

    private Point centre;
    private int rayon;

    public Cercle(Point centre, int rayon, Point position) {
        super(position);
        this.centre = centre;
        this.rayon = rayon;
    }

    public Point getCentre() {
        return centre;
    }

    public void setCentre(Point centre) {
        this.centre = centre;
    }

    public int getRayon() {
        return rayon;
    }

    public void setRayon(int rayon) {
        this.rayon = rayon;
    }

    @Override
    public double getAire() {
        return Math.pow(this.rayon,2) * Math.PI;
    }

    @Override
    public double getPerimetre() {
        return Math.PI*2*this.rayon;
    }
}
