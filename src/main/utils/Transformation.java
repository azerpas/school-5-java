package main.utils;

import main.patchwork.Forme;
import main.patchwork.Ligne;
import main.patchwork.Point;

public interface Transformation {
    public Forme translation(final int x, final int y);

    public Forme homothetie(double k);
    
    public Forme rotation(double angle);

    public Forme symetrieCentre(Point p);
    
    public Forme symetrieAxiale(Ligne l);
}