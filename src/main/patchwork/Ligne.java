package main.patchwork;

public class Ligne extends Forme{
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
        return new Point(
            (this.getPointA().getX() + this.getPointB().getX())/2, 
            (this.getPointA().getY() + this.getPointB().getY())/2
        );
    }    
    
    @Override
    public double getAire() {
        return 0;
    }

    @Override
    public double getPerimetre() {
        return Point.getDistance(this.pointA,this.pointB);
    }

}