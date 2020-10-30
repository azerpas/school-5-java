package main.patchwork;

import main.utils.Transformation;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents an Ellipse form
 */
public class Ellipse extends Forme implements Transformation {
    private Ligne petitAxe;
    private Ligne grandAxe;

    /**
     * @param centre
     * @param petitAxe
     * @param grandAxe
     */
    public Ellipse(Point centre, Ligne petitAxe, Ligne grandAxe) {
        super(centre);
        this.petitAxe = petitAxe;
        this.grandAxe = grandAxe;
    }

    /**
     * @param centre
     */
    public void setCentre(Point centre) {
        this.centre = centre;
    }

    /**
     * @return
     */
    public Ligne getpetitAxe() {
        return petitAxe;
    }

    /**
     * @param petitAxe
     */
    public void setpetitAxe(Ligne petitAxe) {
        this.petitAxe = petitAxe;
    }

    /**
     * @return
     */
    public Ligne getgrandAxe() {
        return grandAxe;
    }

    /**
     * @param grandAxe
     */
    public void setgrandAxe(Ligne grandAxe) {
        this.grandAxe = grandAxe;
    }

    @Override
    public double getAire() {
        return Math.PI * (petitAxe.getPerimetre()/2) * (grandAxe.getPerimetre()/2);
    }

    @Override
    public double getPerimetre() {
        double a = Math.pow(this.grandAxe.getPerimetre()/2, 2);
        double b = Math.pow(this.petitAxe.getPerimetre()/2, 2);
        return 2 * Math.PI * Math.sqrt((a + b) / 2);
    }

    @Override
    public Set<Point> getPoints() {
        HashSet<Point> points = new HashSet<Point>();
        points.add(this.grandAxe.getPointA());
        points.add(this.grandAxe.getPointB());
        points.add(this.petitAxe.getPointA());
        points.add(this.petitAxe.getPointB());
        return points;
    }

    @Override
    public Forme translation(double x, double y) {
        Point newCentre = new Point(this.centre.getX()+x,this.centre.getY()+y);

        return new Ellipse(newCentre,(Ligne)this.petitAxe.translation(x,y),(Ligne)this.grandAxe.translation(x,y));
    }

    @Override
    public Forme homothetie(Point p,double k) {
        Point pointAA = new Point(
                (k * ( this.petitAxe.getPointA().getX() - this.centre.getX() )) + this.centre.getX(),
                (k * (  this.petitAxe.getPointA().getY() - this.centre.getY() )) + this.centre.getY()
        );
        Point pointAB = new Point(
                (k * ( this.petitAxe.getPointB().getX() - this.centre.getX() )) + this.centre.getX(),
                (k * (  this.petitAxe.getPointB().getY() - this.centre.getY() )) + this.centre.getY()
        );
        Point pointBA = new Point(
                (k * ( this.grandAxe.getPointA().getX() - this.centre.getX() )) + this.centre.getX(),
                (k * (  this.grandAxe.getPointA().getY() - this.centre.getY() )) + this.centre.getY()
        );
        Point pointBB = new Point(
                (k * ( this.grandAxe.getPointB().getX() - this.centre.getX() )) + this.centre.getX(),
                (k * (  this.grandAxe.getPointB().getY() - this.centre.getY() )) + this.centre.getY()
        );
        return new Ellipse(this.centre,new Ligne(pointAA,pointAB),new Ligne(pointBA,pointBB));
    }

    @Override
    public Forme rotation(Point p,double angle) {


        angle *= Math.PI / 180;
        double x2 = ((this.centre.getX() - p.getX()) * Math.cos(angle)) - ((this.centre.getY() - p.getY()) * Math.sin(angle)) + p.getX();
        double y2 = ((this.centre.getX() - p.getX()) * Math.sin(angle)) - ((this.centre.getY() - p.getY()) * Math.cos(angle)) + p.getY();
        return new Ellipse(new Point(x2,y2),(Ligne)this.petitAxe.rotation(p,angle),(Ligne)this.grandAxe.rotation(p,angle));
    }

    @Override
    public Forme symetrieCentre(Point p) {
        Point newCentre = new Point(2*p.getX()-this.centre.getX(),2*p.getY()-this.centre.getY());
        System.out.println(this.centre + " : " + newCentre + " : " + p);
        return new Ellipse(newCentre,(Ligne)this.petitAxe.symetrieCentre(p),(Ligne)this.grandAxe.symetrieCentre(p));

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


        double bAA =this.petitAxe.getPointA().getY() - (this.petitAxe.getPointA().getX() * coefficientBis);
        double xAA = (bAA - b) / (coeficient - coefficientBis);
        double yAA = coefficientBis * xCentre + bAA;
        Point pointAA = new Point(2*xAA - this.petitAxe.getPointA().getX(),2*yAA - this.petitAxe.getPointA().getY());

        double bAB =this.petitAxe.getPointB().getY() - (this.petitAxe.getPointB().getX() * coefficientBis);
        double xAB = (bAB - b) / (coeficient - coefficientBis);
        double yAB = coefficientBis * xAB + bAB;
        Point pointAB = new Point(2*xAB - this.petitAxe.getPointB().getX(),2*yAB - this.petitAxe.getPointB().getY());

        double bBA =this.grandAxe.getPointA().getY() - (this.grandAxe.getPointA().getX() * coefficientBis);
        double xBA = (bBA - b) / (coeficient - coefficientBis);
        double yBA = coefficientBis * xBA + bBA;
        Point pointBA = new Point(2*xBA - this.grandAxe.getPointA().getX(),2*yBA - this.grandAxe.getPointA().getY());

        double bBB =this.grandAxe.getPointB().getY() - (this.grandAxe.getPointB().getX() * coefficientBis);
        double xBB = (bBB - b) / (coeficient - coefficientBis);
        double yBB = coefficientBis * xBB + bBB;
        Point pointBB = new Point(2*xBB - this.grandAxe.getPointB().getX(),2*yBB - this.grandAxe.getPointB().getY());

        return new Ellipse(newCentre,new Ligne(pointAA,pointAB),new Ligne(pointBA,pointBB));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Ellipse ellipse = (Ellipse) o;
        System.out.println(Objects.equals(petitAxe, ellipse.petitAxe) &&
                Objects.equals(grandAxe, ellipse.grandAxe));
        return Objects.equals(petitAxe, ellipse.petitAxe) &&
                Objects.equals(grandAxe, ellipse.grandAxe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), petitAxe, grandAxe);
    }


}
