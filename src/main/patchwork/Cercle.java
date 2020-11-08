package main.patchwork;

import main.utils.Transformation;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a Circle form
 */
public class Cercle extends Forme implements Transformation {

    private Point pCercle;

    /**
     * @param centre  : centre du cercle
     * @param pCercle : point sur le cercle
     * Constructeur
     */
    public Cercle(final Point centre, final Point pCercle) {
        super(centre);
        this.pCercle = pCercle;
    }

    /**
     * @return Point sur le cercle
     */
    public Point getpCercle() {
        return pCercle;
    }

    /**
     * @param pCercle : nouveau point sur le cercle
     * Setter permettant de modifier le point sur le cercle
     */
    public void setpCercle(final Point pCercle) {
        this.pCercle = pCercle;
    }

    /**
     * @param centre : Centre du cercle
     *  Setter permettant de modifier le centre du cercle
     */
    public void setCentre(final Point centre) {
        this.centre = centre;
    }

    /**
     *
     * @return le rayon du Cercle (distance entre le centre et le point sur le cercle)
     */
    public double getRayon() {
        return Point.getDistance(this.centre,this.pCercle);
    }

    /**
     * @param rayon : Nouveau rayon
     * Setter permettant de modifier le rayon du cercle (deplacer le point sur le cercle)
     */
    public void setRayon(final double rayon) {
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
    public Forme translation(final double x, final double y) {
        return new Cercle(new Point(this.centre.getX()+x,this.centre.getY()+y),new Point(this.pCercle.getX()+x,this.pCercle.getY()+y));
    }

    @Override
    public Forme homothetie(final Point p, final double k) {
        double newX = k *(this.centre.getX() - p.getX()) + p.getX();
        double newY = k *(this.centre.getY() - p.getY()) + p.getY();
        Point res = new Point(newX,newY);

        double newXp = k *(this.pCercle.getX() - p.getX()) + p.getX();
        double newYp = k *(this.pCercle.getY() - p.getY()) + p.getY();
        Point resp = new Point(newXp,newYp);

        return new Cercle(res,resp);
    }

    @Override
    public Forme rotation(final Point p, double angle) {
        angle *= Math.PI  / 180;
        double x2 = ((this.centre.getX() - p.getX()) * Math.cos(angle)) + ((this.centre.getY() - p.getY()) * Math.sin(angle)) + p.getX();
        double y2 = ((this.centre.getX() - p.getX()) * Math.sin(angle)) + ((this.centre.getY() - p.getY()) * Math.cos(angle)) + p.getY();

        double xPointCercle2 = ((this.pCercle.getX() - p.getX()) * Math.cos(angle)) + ((this.pCercle.getY() - p.getY()) * Math.sin(angle)) + p.getX();
        double yPointCercle2 = ((this.pCercle.getX() - p.getX()) * Math.sin(angle)) + ((this.pCercle.getY() - p.getY()) * Math.cos(angle)) + p.getY();
        return new Cercle(new Point(x2,y2),new Point(xPointCercle2,yPointCercle2));
    }

    @Override
    public Forme symetrieCentre(final Point p) {
        Point newCentre = new Point(2*p.getX()-this.centre.getX(),2*p.getY()-this.centre.getY());
        Point newpCercle = new Point(2*p.getX()-this.pCercle.getX(),2*p.getY()-this.pCercle.getY());
        return new Cercle(newCentre,newpCercle);
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
    public Forme symetrieAxiale(final Ligne l) {
        // calcul de l'equation de la droite : y = coeficient * x + b
        double coeficient = (l.getPointB().getY() - l.getPointA().getY()) / (l.getPointB().getX() - l.getPointA().getX());
        double b = l.getPointA().getY() - (coeficient * l.getPointA().getX());
        /* Calcul de l'equation de la deuxieme droite (entre le centre et le centre symetrique) :
        *   coefficient = 1 / coefficientPremiereDroite
        *   donc yCentre = xCentre * coefficient + b
        * */

        double coefficientBis = -1 / coeficient;

        double bBis = this.centre.getY() - (this.centre.getX() * coefficientBis);
        double bpCercle =this.pCercle.getY() - (this.pCercle.getX() * coefficientBis);

        /*
        Soit les droites dont les équations sont y = x – 4 et y = –2x + 5, alors : x – 4 = –2x + 5. On représente ces droites dans un plan cartésien.
        Donc : 3x = 9 et x = 3
        Puis :  y = –1
        Les coordonnées du point d’intersection de ces droites sont (3, –1).

         */
        double xCentre =  (bBis - b) / (coeficient - coefficientBis);
        double yCentre =  coefficientBis * xCentre + bBis;

        double xPCercle =  (bpCercle - b) / (coeficient - coefficientBis);
        double yPCercle =  coefficientBis * xPCercle + bpCercle;

        Point newCentre = new Point(Math.floor((2*xCentre - this.centre.getX())*100)/100,Math.floor((2*yCentre - this.centre.getY())*100)/100);
        Point newPoint = new Point(Math.floor((2*xPCercle - this.pCercle.getX())*100)/100,Math.floor((2*yPCercle - this.pCercle.getY())*100)/100);
        return new Cercle(newCentre,newPoint);
    }
}
