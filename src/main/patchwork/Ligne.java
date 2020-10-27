package main.patchwork;

public class Ligne{
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

    public double getLongueur(){
        return Point.getDistance(this.pointA,this.pointB);
    }

}