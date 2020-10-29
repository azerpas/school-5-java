package main.utils;

import main.patchwork.Forme;
import main.patchwork.Ligne;
import main.patchwork.Point;

public interface Transformation {
    /**
     * Translate form
     * @param x the value to add on x axis
     * @param y the value to add on y axis 
     * @return defensive copy of a previous form
     */
    public Forme translation(final double x, final double y);

    /**
     * Homothetic transformation
     * @param p the point where the transformation occurs
     * @param k the scaling value
     * @return defensive copy of a previous form
     */
    public Forme homothetie(Point p, double k);
    
    /**
     * Rotate form
     * @param p the form rotate around this point
     * @param angle the angle of rotation
     * @return defensive copy of a previous form
     */
    public Forme rotation(Point p , double angle);

    /**
     * Point reflection 
     * @param p the point where the form reflects
     * @return defensive copy of a previous form
     */
    public Forme symetrieCentre(Point p);
    
    /**
     * Axial symmetry
     * @param l the line where the form reflects
     * @return defensive copy of a previous form
     */
    public Forme symetrieAxiale(Ligne l);
}