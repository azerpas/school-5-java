package main.patchwork;

import java.util.*;

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
        double x = Math.floor(sumX/this.points.size() * 100) / 100;
        double y = Math.floor(sumY/this.points.size() * 100) / 100;
        return new Point(x,y);
    }

    @Override
    public double getPerimetre() {
        /*Version (x1 * y2 - y1 * x2) + ...  +(xn * y1 - yn * x1)
                   ----------------------------------------------
                                          2
        */
            double res = 0;
            List<Point> allPoints = this.getSortedPoints();
            System.out.println("sdfghjk");
            System.out.println(allPoints);
            for(int i = 0 ; i < allPoints.size() ; i++){
                if(i+1 < allPoints.size()) res += Point.getDistance(allPoints.get(i),allPoints.get(i+1));
            }
            //res += first.getX() * suivant.getY() - first.getY() * suivant.getX();

            return res / 2 ;
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

        //System.out.println(this.centre);
        //        double newX = k *(this.centre.getX() - p.getX()) + p.getX();
        //        double newY = k *(this.centre.getY() - p.getY()) + p.getY();
        //        Point newCentre = new Point(newX,newY);
        //        int index = 0;
        //        Ligne l1 = null;
        //        Point[] pointsAxes = new Point[2];
        //        for(Point pointAxe : this.getPoints()){
        //            double newXpoint = k *(pointAxe.getX() - p.getX()) + p.getX();
        //            double newYpoint = k *(pointAxe.getY() - p.getY()) + p.getY();
        //            Point newPoint = new Point(newXpoint,newYpoint);
        //            pointsAxes[index%2] = newPoint;
        //            if(index == 1){
        //                l1 =new Ligne(pointsAxes);
        //                pointsAxes = new Point[2];
        //            }
        //            index++;
        //        }
        //        return new Ellipse(newCentre,l1,new Ligne(pointsAxes));
        // (0,0) (0,3) (3,3) (3,0) : Carré de 3x3cm
        // Somme des X = 6  && Somme des Y = 6
        // Centre du polygone (6/4,6/4) = (1.5,1.5)
        // (0,0) (0,3) (3,0) : Triangle carré en (0,0)
        // Somme des X = 3  && Somme des Y = 3
        // Centre du polygone (3/3,3/3) = (1,1)

        //Point centre = this.getCentre(); // (1,1)
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
        HashSet h = new HashSet<Point>();
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

    public List<Point> getSortedPoints(){
        Iterator<Point> iterator = this.points.iterator();
        ArrayList<Point> pointsTries = new ArrayList<Point>();
        while (iterator.hasNext()){
            Point courant = iterator.next();
            System.out.println("Courant point: "+courant.getX()+"-"+courant.getY());
            if(pointsTries.size() == 0) pointsTries.add(courant);
            else{
                System.out.println(pointsTries);
                boolean isAdd = false;
                for (int i = 0; i < pointsTries.size(); i++) {
                    Point point = pointsTries.get(i);
                    if(this.less(courant, point)){
                        int index = i == 0 ? 0 : i-1;
                        pointsTries.add(index,courant);
                        isAdd = true;
                        System.out.println("("+courant.getX()+","+courant.getY()+") < ("+point.getX()+","+point.getY()+")");
                        break;
                    }else{
                        System.out.println("("+courant.getX()+","+courant.getY()+") > ("+point.getX()+","+point.getY()+")");
                    }
                }
                if(!isAdd)pointsTries.add(courant);
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
