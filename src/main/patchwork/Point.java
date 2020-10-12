package main.patchwork;

public class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static double getDistance(Point a , Point b){
        return Math.sqrt((Math.pow(b.getX() - a.getX(),2)) + Math.pow(b.getY() - a.getY(),2));
    }
}
