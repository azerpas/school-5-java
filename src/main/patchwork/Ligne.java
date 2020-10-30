package main.patchwork;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import main.utils.Transformation;

public class Ligne extends Forme implements Transformation {
    private Point pointA;
    private Point pointB;

    public Ligne(Point pointA, Point pointB) {
        this.pointA = pointA;
        this.pointB = pointB;
    }

    public Point getPointA() {
        return pointA;
    }

    public void setPointA(Point pointA) {
        this.pointA = pointA;
    }

    public Point getPointB() {
        return pointB;
    }

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
        /*
         pAA = new Point(8,6);
        pAB = new Point(12,6);
        pBA = new Point(10,0);
        pBB = new Point(10,12);
        e = new Ellipse(new Point(10,6),new Ligne(pAA,pAB),new Ligne(pBA,pBB));

        centre de l'ellipse (10 , 6)
        petitAxe : (8,6) (12,6)
        petitAxe : (10,0) (10,12)

        resultat normalement :
        centre de l'ellipse (x,y)
        petitAxe : (8 +/- x , 6 +/- y
         */
        Point A = new Point(this.getPointA().getX()+x,this.getPointA().getY()+y);
        Point B =new Point(this.getPointB().getX()+y,this.getPointB().getY()+y);
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
        Point A = new Point(
            p.getX() + (pointA.getX() * Math.cos(angle) - pointA.getY() * Math.sin(angle)),
            p.getY() + (pointA.getX() * Math.sin(angle) + pointA.getY() * Math.cos(angle))
        );
        Point B = new Point(
            p.getX() + (pointB.getX() * Math.cos(angle) - pointB.getY() * Math.sin(angle)),
            p.getY() + (pointB.getX() * Math.sin(angle) + pointB.getY() * Math.cos(angle))
        );
        return new Ligne(A,B);
    }

    @Override
    public Forme symetrieCentre(Point p) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Forme symetrieAxiale(Ligne p) {
        // TODO Auto-generated method stub
        return null;
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