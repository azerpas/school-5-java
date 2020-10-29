package main.patchwork;

import java.util.HashSet;
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
        Point A = new Point(this.getPointA().getX() + x, this.getPointA().getY() + y);
        Point B = new Point(this.getPointB().getX() + x, this.getPointB().getY() + y);
        return new Ligne(A,B);
    }

    @Override
    public Forme homothetie(double k) {
        Point centre = this.getCentre();
        Point A = new Point(
            (k * ( pointA.getX() - centre.getX() )) + centre.getX(),
            (k * ( pointA.getY() - centre.getY() )) + centre.getY()
        );
        Point B = new Point(
            (k * ( pointB.getX() - centre.getX() )) + centre.getX(),
            (k * ( pointB.getY() - centre.getY() )) + centre.getY()
        );
        return (new Ligne(A, B).translation(centre.getX() + k, centre.getY() + k));
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
}