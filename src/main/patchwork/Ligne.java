package main.patchwork;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import main.utils.Transformation;

/**
 * A class representing a Line form
 */
public class Ligne extends Forme implements Transformation {
    private Point pointA;
    private Point pointB;

    /**
     * Line constructor
     * @param pointA 
     * @param pointB
     */
    public Ligne(final Point pointA, final Point pointB) {
        this.pointA = pointA;
        this.pointB = pointB;
    }

    /**
     * Line constructor
     * @param points
     */
    public Ligne(final Point[] points){
        if(points.length > 1){
            this.pointA = points[0];
            this.pointB = points[1];
        }
    }

    /**
     * Get PointA
     * @return Point entity
     */
    public Point getPointA() {
        return pointA;
    }

    /**
     * Set PointA
     * @param pointA
     */
    public void setPointA(Point pointA) {
        this.pointA = pointA;
    }

    /**
     * Get PointB
     * @return
     */
    public Point getPointB() {
        return pointB;
    }

    /**
     * Set PointB
     * @param pointB
     */
    public void setPointB(Point pointB) {
        this.pointB = pointB;
    }

    @Override
    public Point getCentre() {
        return new Point((this.getPointA().getX() + this.getPointB().getX()) / 2,
                (this.getPointA().getY() + this.getPointB().getY()) / 2);
    }

    @Override
    public double getAire() {
        return 0;
    }

    @Override
    public double getPerimetre() {
        return Point.getDistance(this.pointA, this.pointB);
    }

    @Override
    public Set<Point> getPoints() {
        HashSet<Point> points = new HashSet<Point>();
        points.add(pointA);
        points.add(pointB);
        return points;
    }

    @Override
    public String toString() {
        return "Ligne{" +
                "pointA=" + pointA +
                ", pointB=" + pointB +
                '}';
    }
    @Override
    public Forme translation(double x, double y) {
        Point A = new Point(this.getPointA().getX()+x,this.getPointA().getY()+y);
        Point B =new Point(this.getPointB().getX()+x,this.getPointB().getY()+y);
        return new Ligne(A,B);
    }

    @Override
    public Forme homothetie(Point p, double k) {
        //Point centre = this.getCentre();
        Point A = new Point(
            (k * ( pointA.getX() - p.getX() )) + p.getX(),
            (k * ( pointA.getY() - p.getY() )) + p.getY()
        );
        Point B = new Point(
            (k * ( pointB.getX() - p.getX() )) + p.getX(),
            (k * ( pointB.getY() - p.getY() )) + p.getY()
        );
        return (new Ligne(A, B).translation(p.getX() + k, p.getY() + k));
    }

    @Override
    public Forme rotation(Point p, double angle) {
        //Point centre = this.getCentre();


        angle *= Math.PI / 180;
        Point A = new Point(
                ((pointA.getX() - p.getX())  * Math.cos(angle)) - ((pointA.getY() - p.getY()) * Math.sin(angle)) + p.getX(),
                ((pointA.getX() - p.getX())  * Math.sin(angle)) - ((pointA.getY() - p.getY()) * Math.cos(angle)) + p.getY()
        );
        Point B = new Point(
                ((pointB.getX() - p.getX())  * Math.cos(angle)) - ((pointB.getY() - p.getY()) * Math.sin(angle)) + p.getX(),
                ((pointB.getX() - p.getX())  * Math.sin(angle)) - ((pointB.getY() - p.getY()) * Math.cos(angle)) + p.getY()
        );
        return new Ligne(A,B);
    }

    @Override
    public Forme symetrieCentre(Point p) {
        Point resA = new Point(2*p.getX()-this.pointA.getX(),2*p.getY()-this.pointA.getY());
        Point resB = new Point(2*p.getX()-this.pointB.getX(),2*p.getY()-this.pointB.getY());
        return new Ligne(resA,resB);
    }

    @Override
    public Forme symetrieAxiale(Ligne l) {
        // calcul de l'equation de la droite : y = coeficient * x + b
        double coeficient = (l.getPointB().getY() - l.getPointA().getY()) / (l.getPointB().getX() - l.getPointA().getX());
        double bL = l.getPointA().getY() - (coeficient * l.getPointA().getX());


        double coefficientBis = -1 / coeficient;


        double bA = this.pointA.getY() - (this.pointA.getX() * coefficientBis);
        double xA = (bA - bL) / (coeficient - coefficientBis);
        double yA = coefficientBis * xA + bA;
        xA = (double)Math.round((2*xA - this.pointA.getX()) * 100) / 100;
        yA = (double)Math.round((2*yA - this.pointA.getY()) * 100) / 100;
        Point newA = new Point(xA,yA);


        double bB = this.pointB.getY() - (this.pointB.getX() * coefficientBis);
        double xB = (bB - bL) / (coeficient - coefficientBis);
        double yB = coefficientBis * xB + bB;
        xB = (double)Math.round((2*xB - this.pointB.getX()) * 100) / 100;
        yB = (double)Math.round((2*yB - this.pointB.getY()) * 100) / 100;
        Point newB = new Point(xB,yB);

        return new Ligne(newA,newB);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Ligne ligne = (Ligne) o;
        return Objects.equals(pointA, ligne.pointA) &&
                Objects.equals(pointB, ligne.pointB);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pointA, pointB);
    }
}