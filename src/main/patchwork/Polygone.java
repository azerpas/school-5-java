package main.patchwork;

import java.util.ArrayList;
import java.util.Collections;
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
        Iterator<Point> iterator = this.getSortedPoints(this.points).iterator();
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
        double x = Math.floor(sumX/this.points.size() * 100) / 100;
        double y = Math.floor(sumY/this.points.size() * 100) / 100;
        return new Point(x,y);
    }

    @Override
    public double getPerimetre() {
        double res = 0;
        int i = 0;
        List<Point> allPoints = this.getSortedPoints(this.getPoints());
        for(i = 0 ; i < allPoints.size() ; i++){
            if(i+1 < allPoints.size()) res += Point.getDistance(allPoints.get(i),allPoints.get(i+1));
        }
        res += Point.getDistance(allPoints.get(0),allPoints.get(i-1));
        return Math.floor(res*100)/100 ;
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
        HashSet<Point> newPoints = new HashSet<Point>();

        for(Point pointCourant : this.getPoints()){
            double newXpoint = k *(pointCourant.getX() - point.getX()) + point.getX();
            double newYpoint = k *(pointCourant.getY() - point.getY()) + point.getY();
            Point newPoint = new Point(newXpoint,newYpoint);
            newPoints.add(newPoint);
        }

        return new Polygone(newPoints);
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
        //Point newCentre = new Point(2*p.getX()-this.centre.getX(),2*p.getY()-this.centre.getY());
        //Point centre = this.getCentre();
        HashSet<Point> newPoints = new HashSet<Point>();
        //Forme formeTranslate = this.translation(-centre.getX(), -centre.getY());
        for(Point pCourant : this.getPoints()){
            double x = 2*p.getX()-pCourant.getX();
            double y = 2*p.getY()-pCourant.getY();
            newPoints.add(new Point(x,y));
        }
        return new Polygone(newPoints);
    }

    @Override
    public Forme symetrieAxiale(Ligne l) {
        // calcul de l'equation de la droite : y = coeficient * x + b
        double coeficient = (l.getPointB().getY() - l.getPointA().getY()) / (l.getPointB().getX() - l.getPointA().getX());
        double b = l.getPointA().getY() - (coeficient * l.getPointA().getX());
        /* Calcul de l'equation de la deuxieme droite (entre le centre et le centre symetrique) :
         *   coefficient = 1 / coefficientPremiereDroite
         *   donc yCentre = xCentre * coefficient + b
         * */

        double coefficientBis = -1 / coeficient;
        /*
        Soit les droites dont les équations sont y = x – 4 et y = –2x + 5, alors : x – 4 = –2x + 5. On représente ces droites dans un plan cartésien.
        Donc : 3x = 9 et x = 3
        Puis :  y = –1
        Les coordonnées du point d’intersection de ces droites sont (3, –1).

         */
        HashSet<Point> h = new HashSet<Point>();
        for(Point p : this.getPoints()){
            double bPoint = p.getY() - (p.getX() * coefficientBis);
            double x = (bPoint - b) / (coeficient - coefficientBis);
            double y = coefficientBis * x + bPoint;
            x = (double)Math.round((2*x - p.getX()) * 100) / 100;
            y = (double)Math.round((2*y - p.getY()) * 100) / 100;
            Point pointRes = new Point(x,y);
            h.add(pointRes);
        }

        return new Polygone(h);
    }

    public List<Point> getSortedPoints(Set<Point> pts) {
        List<Point> points = new ArrayList<Point>(pts);
        Point center = this.getCentre();
        Collections.sort(points, (a, b) -> {
            double a1 = (Math.toDegrees(Math.atan2(a.getX() - center.getX(), a.getY() - center.getY())) + 360) % 360;
            double a2 = (Math.toDegrees(Math.atan2(b.getX() - center.getX(), b.getY() - center.getY())) + 360) % 360;
            return (int) (a1 - a2);
        });
        return points;
    }
}
