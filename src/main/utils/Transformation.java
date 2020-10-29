package main.utils;

import main.patchwork.Forme;
import main.patchwork.Ligne;
import main.patchwork.Point;

public interface Transformation {
    public Forme translation(final double x, final double y);

    public Forme homothetie(Point p, double k);
    
    public Forme rotation(Point p , double angle);

    public Forme symetrieCentre(Point p);
    
    public Forme symetrieAxiale(Ligne l);
}