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
        while (iterator.hasNext()){
            precedent = precedent == null ? first = (Point) iterator.next() : precedent;
            suivant = suivant == null ? (Point) iterator.next() : suivant;
            res += Math.abs(precedent.getX() * suivant.getY() - precedent.getY() * suivant.getX());
            precedent = suivant;
            suivant = (Point) iterator.next();
        }
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

        return super.toString()+"Polygone{" +
                "points=\n            " + points +
                "\n         }";
    }

    public Forme translation(double x, double y) {
        HashSet<Point> newPoints = new HashSet<Point>();
        for(Point p : this.points){ // (0,0) (0,3) (3,0) && (x,y): (5,5)
            newPoints.add( 
                new Point(
                    p.getX() + x, // 5 5 8
                    p.getY() + y //  5 8 5
                )
            );
        }
        return new Polygone(newPoints);
    }

    @Override
    public Forme homothetie(double k) {

        // (0,0) (0,3) (3,3) (3,0) : Carré de 3x3cm
        // Somme des X = 6  && Somme des Y = 6
        // Centre du polygone (6/4,6/4) = (1.5,1.5)
        // (0,0) (0,3) (3,0) : Triangle carré en (0,0)
        // Somme des X = 3  && Somme des Y = 3
        // Centre du polygone (3/3,3/3) = (1,1)

        Point centre = this.getCentre(); // (1,1)
        HashSet<Point> newPoints = new HashSet<Point>();
        for(Point p : this.points){ // (0,0) (0,3) (3,0)
            newPoints.add( 
                new Point( // On crée un nouveau point pour chaque ancien point pour la copie défensive
                    (k * ( p.getX() - centre.getX() )) + centre.getX(), // (2 * ( 0 - 1 )) + 1 = 1 && (2 * ( 0 - 1 )) + 1 = 1 && (2 * ( 3 - 1 )) + 1 = 5
                    (k * ( p.getY() - centre.getY() )) + centre.getY() // (2 * (0 - 1)) + 1 = 1 && (2 * ( 3 - 1 )) + 1 = 5 && (2 * ( 0 - 1 )) + 1 = 1
                )
            );
        }
        // Ça scale mais ça ne nous permet pas de déplacer la forme 
        // https://upload.wikimedia.org/wikipedia/commons/thumb/a/a9/Homothetic_transformation.svg/440px-Homothetic_transformation.svg.png
        // Il faudrait faire une translation en plus


        return new Polygone(newPoints);
    }

    @Override
    public Forme rotation(Point pRotation,double angle) {
        Point centre = this.getCentre();
        HashSet<Point> newPoints = new HashSet<Point>();
        Forme formeTranslate = this.translation(-centre.getX(), -centre.getY());
        for(Point p : formeTranslate.getPoints()){ // (0,0) (0,3) (3,0) && (x,y): (5,5)
            double x = (p.getX() - pRotation.getX()) * Math.cos(angle) - (p.getY() - pRotation.getY()) * Math.sin(angle) + pRotation.getX();
            double y = (p.getX() - pRotation.getX()) * Math.sin(angle) - (p.getY() - pRotation.getY()) * Math.cos(angle) + pRotation.getY();
           /* double x = p.getX() * Math.cos(angle) - p.getY() * Math.sin(angle);
            double y = p.getX() * Math.sin(angle) + p.getY() * Math.cos(angle);*/
            newPoints.add(new Point(x,y));
        }
        return new Polygone(newPoints);
    }

    @Override
    public Forme symetrieCentre(Point p) {
        Point centre = this.getCentre();
        HashSet<Point> newPoints = new HashSet<Point>();
        Forme formeTranslate = this.translation(-centre.getX(), -centre.getY());
        for(Point pCourant : formeTranslate.getPoints()){
            double x = (pCourant.getX()+p.getX())/2;
            double y = (pCourant.getY()+p.getY())/2;
            newPoints.add(new Point(x,y));
        }
        return new Polygone(newPoints);
    }

    @Override
    public Forme symetrieAxiale(Ligne l) {
        // TODO Auto-generated method stub
        return null;
    }

}
