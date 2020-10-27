package main.patchwork;

import java.util.ArrayList;

import main.utils.Transformation;

public class Polygone extends Forme implements Transformation{
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

    public ArrayList<Point> getPoints(){
        ArrayList<Point> p = new ArrayList<>();
        for (Ligne l : this.cotes){
            p.add(l.getPointA());
            p.add(l.getPointB());
        }
        return p;
    }

    @Override
    public double getAire() {
        /*Version (x1 * y2 - y1 * x2) + ...  +(xn * y1 - yn * x1)
                   ----------------------------------------------
                                          2
        */
        double res = 0;
        ArrayList<Point> allPoints = this.getPoints();
        int index = 0;
        for(Point p : allPoints){
            if(index == allPoints.size() -1)index = -1;
            double valA = p.getX() * allPoints.get(index + 1).getY();
            double valB = p.getY() * allPoints.get(index + 1).getX();
            res += valA - valB;
            index++;
        }

        return res /2 ;
    }

    @Override
    public double getPerimetre() {
        double res = 0;
        for (Ligne l:this.cotes) {
            res += l.getLongueur();
        }
        return res;
    }

    @Override
    public Forme translation(int x, int y) {
        ArrayList<Point> allPoints = this.getPoints();
        return null;
    }

    @Override
    public Forme homothetie(double k) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Forme rotation(double angle) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Forme symetrieCentre(Point p) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Forme symetrieAxiale(Point p) {
        // TODO Auto-generated method stub
        return null;
    }

}
