package main.patchwork;

import java.util.HashSet;
import java.util.Set;

public class Ellipse extends Forme {
    private Ligne petitAxe;
    private Ligne grandAxe;

    public Ellipse(Point centre, Ligne petitAxe, Ligne grandAxe) {
        super(centre);
        this.petitAxe = petitAxe;
        this.grandAxe = grandAxe;
    }

    public Point getCentre() {
        return this.centre;
    }

    public void setCentre(Point centre) {
        this.centre = centre;
    }

    public Ligne getpetitAxe() {
        return petitAxe;
    }

    public void setpetitAxe(Ligne petitAxe) {
        this.petitAxe = petitAxe;
    }

    public Ligne getgrandAxe() {
        return grandAxe;
    }

    public void setgrandAxe(Ligne grandAxe) {
        this.grandAxe = grandAxe;
    }

    @Override
    public double getAire() {
        return Math.PI * petitAxe.getPerimetre() * grandAxe.getPerimetre();
    }

    @Override
    public double getPerimetre() {
        double a = Math.pow(this.grandAxe.getPerimetre(), 2);
        double b = Math.pow(this.petitAxe.getPerimetre(), 2);
        return 2 * Math.PI * Math.sqrt((a + b) / 2);
    }

    @Override
    public Set<Point> getPoints() {
        HashSet<Point> points = new HashSet<Point>();
        points.add(this.getCentre());
        return points;
    }
}
