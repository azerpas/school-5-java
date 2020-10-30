package main.patchwork;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import main.utils.Transformation;

/**
 * Represents a Polygon form
 */
public class Polygone extends Forme implements Transformation{
    /**
     * Set of Points shaping the Polygon
     */
    private Set<Point> points;

    public Polygone() {
        super();
        this.points = new HashSet<Point>();
    }

    public Polygone(HashSet<Point> points) {
        super();
        this.points = points;
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
        Iterator<Point> iterator = this.points.iterator();
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

    @Override
    public Set<Point> getPoints() {
        return this.points;
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

    @Override
    public double getPerimetre() {
        double res = 0;
        //for (Point p:this.points) {
            //res += p.getLongueur();
        //}
        return res;
    }

    @Override
    public String toString() {

        return super.toString()+"Polygone{" +
                "points=\n            " + points +
                "\n         }";
    }

    @Override
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
    public Forme homothetie(Point point, double k) {

        // (0,0) (0,3) (3,3) (3,0) : Carré de 3x3cm
        // Somme des X = 6  && Somme des Y = 6
        // Centre du polygone (6/4,6/4) = (1.5,1.5)
        // (0,0) (0,3) (3,0) : Triangle carré en (0,0)
        // Somme des X = 3  && Somme des Y = 3
        // Centre du polygone (3/3,3/3) = (1,1)

        //Point centre = this.getCentre(); // (1,1)
        HashSet<Point> newPoints = new HashSet<Point>();
        for(Point p : this.points){ // (0,0) (0,3) (3,0)
            if(p.getX() == 0.0 && p.getY() == 0.0){
                newPoints.add( 
                    new Point( // scale par 0
                        k * p.getX() , // 0
                        k * p.getY() // 0
                    )
                );
            }else{
                newPoints.add( 
                    new Point( // On crée un nouveau point pour chaque ancien point pour la copie défensive
                        (k * ( p.getX() - point.getX() )) + point.getX(), // (2 * ( 0 - 1 )) + 1 = 1 && (2 * ( 0 - 1 )) + 1 = 1 && (2 * ( 3 - 1 )) + 1 = 5
                        (k * ( p.getY() - point.getY() )) + point.getY() // (2 * (0 - 1)) + 1 = 1 && (2 * ( 3 - 1 )) + 1 = 5 && (2 * ( 0 - 1 )) + 1 = 1
                    )
                );
            }
        }
        // Ça scale mais ça ne nous permet pas de déplacer la forme 
        // https://upload.wikimedia.org/wikipedia/commons/thumb/a/a9/Homothetic_transformation.svg/440px-Homothetic_transformation.svg.png
        // Il faudrait faire une translation en plus
        return (new Polygone(newPoints)/*.translation(centre.getX() + k, centre.getY() + k)*/);
    }

    @Override
    public Forme rotation(Point pRotation,double angle) {
        HashSet<Point> newPoints = new HashSet<Point>();

        Forme formeTranslate = this.translation(-pRotation.getX(), -pRotation.getY());
        for(Point p : formeTranslate.getPoints()){ // (0,0) (0,3) (3,0) && (x,y): (5,5)
            double x = p.getX() * Math.cos(Math.toRadians(angle)) - p.getY() * Math.sin(Math.toRadians(angle));
            double y = p.getX() * Math.sin(Math.toRadians(angle)) + p.getY() * Math.cos(Math.toRadians(angle));
            newPoints.add(new Point(Math.round(x) + pRotation.getX(), Math.round(y) + pRotation.getY()));
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

    public List<Point> getSortedPoints(){
        Iterator<Point> iterator = this.points.iterator();
        ArrayList<Point> pointsTries = new ArrayList<Point>();
        while (iterator.hasNext()){
            Point courant = iterator.next();
            System.out.println("Courant point: "+courant.getX()+"-"+courant.getY());
            if(pointsTries.size() == 0) pointsTries.add(courant);
            else{
                System.out.println(pointsTries);
                for (int i = 0; i < pointsTries.size(); i++) {
                    Point point = pointsTries.get(i);
                    if(this.less(courant, point)){ 
                        System.out.println(courant.getX()+"-"+courant.getY()+">"+point.getX()+"-"+point.getY());
                        pointsTries.add(courant);
                        break;
                    }else{
                        System.out.println(courant.getX()+"-"+courant.getY()+"<"+point.getX()+"-"+point.getY());
                    }
                }
            }
        }
        return pointsTries;
    }

    public boolean less(Point a, Point b)
    {
        Point centre = this.getCentre();
        if (a.getX() - centre.getX() >= 0 && b.getX() - centre.getX() < 0)
            return true;
        if (a.getX() - centre.getX() < 0 && b.getX() - centre.getX() >= 0)
            return false;
        if (a.getX() - centre.getX() == 0 && b.getX() - centre.getX() == 0) {
            if (a.getY() - centre.getY() >= 0 || b.getY() - centre.getY() >= 0)
                return a.getY() > b.getY();
            return b.getY() > a.getY();
        }

        double det = (a.getX() - centre.getX()) * (b.getY() - centre.getY()) - (b.getX() - centre.getX()) * (a.getY() - centre.getY());
        if (det < 0)
            return true;
        if (det > 0)
            return false;

        double d1 = (a.getX() - centre.getX()) * (a.getX() - centre.getX()) + (a.getY() - centre.getY()) * (a.getY() - centre.getY());
        double d2 = (b.getX() - centre.getX()) * (b.getX() - centre.getX()) + (b.getY() - centre.getY()) * (b.getY() - centre.getY());
        return d1 > d2;
    }

}
