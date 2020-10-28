package main.patchwork;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Polygone extends Forme{
    private Set<Point> points;
    private Point centre;


    public Polygone() {
        super();
        this.points = new HashSet<>();
    }

    public Polygone(HashSet<Point> points, Point centre) {
        // tous les points sont reliés ?
        super(centre);
        this.points = points;
        this.centre = centre;
    }

    public Point getCentre() {
        return centre;
    }

    public void setCentre(Point centre) {
        this.centre = centre;
    }

    public Set<Point> getpoints() {
        return this.points;
    }

    public void setpoints(HashSet<Point> points) {
        this.points = points;
    }


    public void addPoint(Point p){
        this.points.add(p);
    }

    @Override
    public double getAire() {
        /*Version (x1 * y2 - y1 * x2) + ...  +(xn * y1 - yn * x1)
                   ----------------------------------------------
                                          2
        */
        double res = 0;
        Point precedent = null;
        Point suivant = null;
        Point first = null;
        Iterator iterator = this.points.iterator();
        System.out.println("      "+this.points);
        while (iterator.hasNext()){
            precedent = precedent == null ? first = (Point) iterator.next() : precedent;
            System.out.println("      "+precedent);
            suivant = suivant == null ? (Point) iterator.next() : suivant;
            System.out.println("      "+res + " + " + precedent.getX() + "*"+ suivant.getY()+ "-"+ precedent.getY()+ "*"+ suivant.getX());
            res += Math.abs(precedent.getX() * suivant.getY() - precedent.getY() * suivant.getX());
            precedent = suivant;
            suivant = (Point) iterator.next();
        }
        System.out.println("      "+suivant);
        System.out.println("      "+res + " + " + first.getX() + "*"+ suivant.getY()+ "-"+ first.getY()+ "*"+ suivant.getX());
        res += first.getX() * suivant.getY() - first.getY() * suivant.getX();

        return res / 2 ;
    }


    public double area()
    {
        System.out.print("      AREA : ");
        Object[] arrayPoints =  this.points.toArray();
        double total = 0;
        for(int i=0;i<arrayPoints.length;i++)
        {
            int j = (i+1)%arrayPoints.length;
            Point pointCourant = (Point) arrayPoints[i];
            Point pointSuivant = (Point) arrayPoints[j];
            total += (pointCourant.getX()*pointSuivant.getY()) - (pointSuivant.getX()*pointCourant.getY());
        }
        return total / 2;
    }

    @Override
    public double getPerimetre() {
        double res = 0;
        for (Point p:this.points) {
            //res += p.getLongueur();
        }
        return res;
    }

    @Override
    public String toString() {
        return "Polygone{" +
                "points=" + points +
                ", centre=" + centre +
                '}';
    }
}
