package main.patchwork;

public class Polygone extends Forme{
    private Ligne[] cotes;
    private Point centre;


    public Polygone(Ligne[] cotes, Point centre) {
        // tous les cotes sont reliés ?
        super(centre);
        this.cotes = cotes;
        this.centre = centre;
    }

    public Point getCentre() {
        return centre;
    }

    public void setCentre(Point centre) {
        this.centre = centre;
    }

    public Ligne[] getCotes() {
        return cotes;
    }

    public void setCotes(Ligne[] cotes) {
        this.cotes = cotes;
    }

    @Override
    public double getAire() {

        return 0;
    }

    @Override
    public double getPerimetre() {
        double res = 0;
        for (Ligne l:this.cotes) {
            res += l.getLongueur();
        }
        return res;
    }
}
