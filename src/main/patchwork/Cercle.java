package main.patchwork;

import main.utils.Transformation;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a Circle form
 */
public class Cercle extends Forme  implements Transformation {

    private Point pCercle;

    public Cercle(Point centre, Point pCercle) {
        super(centre);
        // Le point du cercle a le même y que le centre
        // Ce qui permet de faciliter les calculs par la suite
        if(pCercle.getY() != centre.getY()){
            pCercle.setX(Point.getDistance(pCercle,centre) + centre.getX());
            pCercle.setY(centre.getY());
        }
        this.pCercle = pCercle;
    }

    public Point getpCercle() {
        return pCercle;
    }

    public void setpCercle(Point pCercle) {
        this.pCercle = pCercle;
    }

    public void setCentre(Point centre) {
        this.centre = centre;
    }

    public double getRayon() {
        return Point.getDistance(this.centre,this.pCercle);
    }

    public void setRayon(double rayon) {
        this.pCercle.setX(this.centre.getX() + rayon);
    }


    @Override
    public double getAire() {
        return Math.pow(this.getRayon(),2) * Math.PI;
    }

    @Override
    public double getPerimetre() {
        return Math.PI*2*this.getRayon();
    }

    @Override
    public Set<Point> getPoints() {
        HashSet<Point> res = new HashSet<Point>();
        res.add(this.centre);
        res.add(this.pCercle);
        return res;
    }

    @Override
    public Forme translation(double x, double y) {
        return new Cercle(new Point(this.centre.getX()+x,this.centre.getY()+y),new Point(this.pCercle.getX()+x,this.pCercle.getY()+y));
    }

    @Override
    public Forme homothetie(Point p, double k) {
        Point res;
        if(p.getX() == 0.0 && p.getY() == 0.0){
            res = new Point( // scale par 0
                            k * p.getX() , // 0
                            k * p.getY() // 0
                     );
        }else{
            res = new Point( // On crée un nouveau point pour chaque ancien point pour la copie défensive
                    (k * (p.getX() - this.centre.getX())) + this.centre.getX(), // (2 * ( 0 - 1 )) + 1 = 1 && (2 * ( 0 - 1 )) + 1 = 1 && (2 * ( 3 - 1 )) + 1 = 5
                    (k * (p.getY() - this.centre.getY())) + this.centre.getY() // (2 * (0 - 1)) + 1 = 1 && (2 * ( 3 - 1 )) + 1 = 5 && (2 * ( 0 - 1 )) + 1 = 1
            );
        }
        return new Cercle(res,new Point(res.getX() + this.getRayon() * k,res.getY() ));
    }

    @Override
    public Forme rotation(Point p,double angle) {
        angle *= Math.PI  / 180;
        double x2 = ((this.centre.getX() - p.getX()) * Math.cos(angle)) + ((this.centre.getY() - p.getY()) * Math.sin(angle)) + p.getX();
        double y2 = ((this.centre.getX() - p.getX()) * Math.sin(angle)) + ((this.centre.getY() - p.getY()) * Math.cos(angle)) + p.getY();
        return new Cercle(new Point(x2,y2),new Point(x2+this.getRayon(),y2));
    }

    @Override
    public Forme symetrieCentre(Point p) {
        Point newCentre = new Point(2*p.getX()-this.centre.getX(),2*p.getY()-this.centre.getY());
        return new Cercle(newCentre,new Point(newCentre.getX()+this.getRayon(),newCentre.getY()));
    }

    @Override
    public String toString() {
        return "Cercle{" +
                "pCercle=" + pCercle +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cercle cercle = (Cercle) o;
        return Objects.equals(pCercle, cercle.pCercle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pCercle);
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
        double bBis =this.centre.getY() - (this.centre.getX() * coefficientBis);
        /*
        Soit les droites dont les équations sont y = x – 4 et y = –2x + 5, alors : x – 4 = –2x + 5. On représente ces droites dans un plan cartésien.
        Donc : 3x = 9 et x = 3
        Puis :  y = –1
        Les coordonnées du point d’intersection de ces droites sont (3, –1).

         */
        double xCentre = (bBis - b) / (coeficient - coefficientBis);
        double yCentre = coefficientBis * xCentre + bBis;

        Point newCentre = new Point(2*xCentre - this.centre.getX(),2*yCentre - this.centre.getY());
        return new Cercle(newCentre,new Point(newCentre.getX()+this.getRayon() , newCentre.getY()));
    }
}
