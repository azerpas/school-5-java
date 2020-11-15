package main.patchwork;

import java.util.Objects;

/**
 * A class representing a Point
 */
public class Point {

    /**
     * Position on x-axis
     */
    private double x;
    /**
     * Position on y-axis
     */
    private double y;

    /**
     * Point constructor
     */
    public Point() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Point constructor
     * @param x x-axis
     * @param y y-axis
     */
    public Point(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get x-axis
     * @return x : double
     */
    public double getX() {
        return x;
    }

    /**
     * Set x-axis
     * @param x : double
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Get y-axis
     * @return y : double
     */
    public double getY() {
        return y;
    }

    /**
     * Set y-axis
     * @param y : double
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Get distance between two points
     * @param a
     * @param b
     * @return Distance between a and b
     */
    public static double getDistance(Point a , Point b){
        return Math.floor(Math.sqrt((Math.pow(b.getX() - a.getX(),2)) + Math.pow(b.getY() - a.getY(),2)) * 100)/100;
    }

    @Override
    public String toString() {
        return "Point("  + x + "," + y + ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
