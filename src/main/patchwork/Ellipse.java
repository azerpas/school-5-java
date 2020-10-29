package main.patchwork;

import main.utils.Transformation;

import java.util.HashSet;
import java.util.Set;

public class Ellipse extends Forme implements Transformation {
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

    @Override
    public Forme translation(double x, double y) {
        return new Ellipse(new Point(x,y),this.petitAxe,this.grandAxe);
    }

    @Override
    public Forme homothetie(Point p,double k) {
        Point pointAA = new Point(
                (k * ( this.petitAxe.getPointA().getX() - this.centre.getX() )) + this.centre.getX(),
                (k * (  this.petitAxe.getPointA().getY() - this.centre.getY() )) + this.centre.getY()
        );
        Point pointAB = new Point(
                (k * ( this.petitAxe.getPointB().getX() - this.centre.getX() )) + this.centre.getX(),
                (k * (  this.petitAxe.getPointB().getY() - this.centre.getY() )) + this.centre.getY()
        );
        Point pointBA = new Point(
                (k * ( this.grandAxe.getPointA().getX() - this.centre.getX() )) + this.centre.getX(),
                (k * (  this.grandAxe.getPointA().getY() - this.centre.getY() )) + this.centre.getY()
        );
        Point pointBB = new Point(
                (k * ( this.grandAxe.getPointB().getX() - this.centre.getX() )) + this.centre.getX(),
                (k * (  this.grandAxe.getPointB().getY() - this.centre.getY() )) + this.centre.getY()
        );
        return new Ellipse(this.centre,new Ligne(pointAA,pointAB),new Ligne(pointBA,pointBB));
    }

    @Override
    public Forme rotation(Point p,double angle) {
        double x2 = ((this.centre.getX() - p.getX()) * Math.cos(angle)) - ((this.centre.getY() - p.getY()) * Math.sin(angle)) + p.getX();
        double y2 = ((this.centre.getX() - p.getX()) * Math.sin(angle)) - ((this.centre.getY() - p.getY()) * Math.cos(angle)) + p.getY();
        return new Ellipse(new Point(x2,y2),this.petitAxe,this.grandAxe);
    }

    @Override
    public Forme symetrieCentre(Point p) {
        this.centre.setX((this.centre.getX()+p.getX())/2);
        this.centre.setY((this.centre.getY()+p.getY())/2);
        return new Ellipse(this.centre,this.petitAxe,this.grandAxe);

    }

    @Override
    public Forme symetrieAxiale(Ligne l) {
        return null;
    }
}
