package main.patchwork;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import main.utils.Transformation;

public class Polygone extends Forme implements Transformation{
    private Set<Point> points;

    public Polygone() {
        super();
        this.points = new HashSet<>();
    }

    public Polygone(HashSet<Point> points) {
        super();
        this.points = points;
    }

    @Override
    public Point getCentre() {
        double sumX = 0;
        double sumY = 0;
        for(Point p : this.points){
            sumX += p.getX();
            sumY += p.getY();
        }
        return new Point(sumX / this.points.size(), sumY / this.points.size());
    }

    public Set<Point> getPoints() {
        return this.points;
    }

    public void setPoints(HashSet<Point> points) {
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
                '}';
    }
    public Forme translation(int x, int y) {
        // (0,0) (0,3) (3,3) (3,0) : Carré de 3x3cm
        // Somme des X = 6  && Somme des Y = 6
        // Centre du polygone (6/4,6/4) = (1.5,1.5)
        // (0,0) (0,3) (3,0)
        // Somme des X = 3  && Somme des Y = 3
        // Centre du polygone (3/3,3/3) = (1,1)
        return null;
    }

    @Override
    public Forme homothetie(double k) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Forme rotation(double angle) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Forme symetrieCentre(Point p) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Forme symetrieAxiale(Ligne l) {
        // TODO Auto-generated method stub
        return null;
    }

}
