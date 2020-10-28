package main.patchwork;

import main.utils.Transformation;

public class Cercle extends Forme  implements Transformation {

    private double rayon;

    public Cercle(Point centre, double rayon) {
        super(centre);
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
    public Forme translation(int x, int y) {
        this.centre.setX(x);
        this.centre.setY(y);
        return this;
    }

    @Override
    public Forme homothetie(double k) {
        this.rayon = this.rayon * k;
        return this;
    }

    @Override
    public Forme rotation(double angle) {
        return this;
    }

    @Override
    public Forme symetrieCentre(Point p) {
        this.centre.setX((this.centre.getX()+p.getX())/2);
        this.centre.setY((this.centre.getY()+p.getY())/2);
        return new Cercle(this.centre,this.rayon);
    }

    @Override
    public Forme symetrieAxiale(Ligne l) {
        // calcul de l'equation de la droite : y = coeficient * x + b
        double coeficient = (l.getPointB().getY() - l.getPointA().getY()) / (l.getPointB().getX() - l.getPointA().getX());
        double b = l.getPointA().getY() - l.getPointA().getX() * coeficient;
        /* Calcul de l'equation de la deuxieme droite (entre le centre et le centre symetrique) :
        *   coefficient = 1 / coefficientPremiereDroite
        *   donc yCentre = xCentre * coefficient + b
        * */

        double coefficientBis = 1 / coeficient;
        double bBis = this.centre.getX() * coefficientBis - this.centre.getY();

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
