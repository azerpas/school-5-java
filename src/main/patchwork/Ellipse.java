package main.patchwork;

public class Ellipse extends Forme {
   private Point centre;
   private Ligne petitAxe;
   private Ligne grandAxe;

    public Ellipse(Point centre, Ligne petitAxe, Ligne grandAxe) {
        super(centre);
        this.centre = centre;
        this.petitAxe = petitAxe;
        this.grandAxe = grandAxe;
    }

    public Point getCentre() {
        return centre;
    }

    public void setCentre(Point centre) {
        this.centre = centre;
    }

    public Ligne getpetitAxe() {
        return petitAxe;
    }

    public void setpetitAxe(Ligne petitAxe) {
        this.petitAxe = petitAxe;
    }

    public Ligne getgrandAxe() {
        return grandAxe;
    }

    public void setgrandAxe(Ligne grandAxe) {
        this.grandAxe = grandAxe;
    }

    @Override
    public double getAire() {
        return Math.PI * petitAxe.getLongueur() * grandAxe.getLongueur();
    }

    @Override
    public double getPerimetre() {
        double a = Math.pow(this.grandAxe.getLongueur(),2);
        double b = Math.pow(this.petitAxe.getLongueur(),2);
        return 2 * Math.PI * Math.sqrt((a+b)/2);
    }
}
