package main.patchwork;

import main.utils.Transformation;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a Circle form
 */
public class Cercle extends Forme  implements Transformation {

    private double rayon;

    public Cercle(Point centre, double rayon) {
        super(centre);
        System.out.println("CENTRE : "+centre);
        this.rayon = rayon;
    }

    public Point getCentre() {
        return centre;
    }

    public void setCentre(Point centre) {
        this.centre = centre;
    }

    public double getRayon() {
        return rayon;
    }

    public void setRayon(double rayon) {
        this.rayon = rayon;
    }


    @Override
    public double getAire() {
        return Math.pow(this.rayon,2) * Math.PI;
    }

    @Override
    public double getPerimetre() {
        return Math.PI*2*this.rayon;
    }

    @Override
    public Set<Point> getPoints() {
        HashSet<Point> res = new HashSet<Point>();
        res.add(this.centre);
        return res;
    }

    @Override
    public Forme translation(double x, double y) {
        return new Cercle(new Point(x,y),this.rayon);
    }

    @Override
    public Forme homothetie(Point p, double k) {
        return new Cercle(p,this.rayon * k);
    }

    @Override
    public Forme rotation(Point p,double angle) {
        double x2 = ((this.centre.getX() - p.getX()) * Math.cos(angle)) - ((this.centre.getY() - p.getY()) * Math.sin(angle)) + p.getX();
        double y2 = ((this.centre.getX() - p.getX()) * Math.sin(angle)) - ((this.centre.getY() - p.getY()) * Math.cos(angle)) + p.getY();
        return new Cercle(new Point(x2,y2),this.rayon);
    }

    @Override
    public Forme symetrieCentre(Point p) {
        Point newCentre = new Point((this.centre.getX()+p.getX())/2,(this.centre.getY()+p.getY())/2);
        return new Cercle(newCentre,this.rayon);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cercle cercle = (Cercle) o;
        return Double.compare(cercle.rayon, rayon) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), rayon);
    }

    @Override
    public String toString() {
        return "Cercle{" +
                "rayon=" + rayon +
                ", centre=" + centre +
                '}';
    }

    @Override
    public Forme symetrieAxiale(Ligne l) {
        // calcul de l'equation de la droite : y = coeficient * x + b
        double coeficient = (l.getPointB().getY() - l.getPointA().getY()) / (l.getPointB().getX() - l.getPointA().getX());
        System.out.print("COEF : " + coeficient + " == " + l.getPointB().getY() + "-"  +l.getPointA().getY()+ "/" +l.getPointB().getX() +"-" +l.getPointA().getX() + " === ");
        System.out.println((l.getPointB().getY() -l.getPointA().getY())+ "/" +(l.getPointB().getX() - l.getPointA().getX()));
        double b = l.getPointA().getY() - l.getPointA().getX() * coeficient;
        System.out.println(b);
        /* Calcul de l'equation de la deuxieme droite (entre le centre et le centre symetrique) :
        *   coefficient = 1 / coefficientPremiereDroite
        *   donc yCentre = xCentre * coefficient + b
        * */

        double coefficientBis = 1 / coeficient;
        double bBis = this.centre.getX() * coefficientBis - this.centre.getY();
        System.out.println(bBis);
        /*
        Soit les droites dont les équations sont y = x – 4 et y = –2x + 5, alors : x – 4 = –2x + 5. On représente ces droites dans un plan cartésien.
        Donc : 3x = 9 et x = 3
        Puis :  y = –1
        Les coordonnées du point d’intersection de ces droites sont (3, –1).

         */

        double x = (bBis - b) / (coeficient - coefficientBis);
        double y = coefficientBis * x + bBis;




        return new Cercle(new Point(x,y),this.rayon);
    }
}
