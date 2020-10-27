package main.utils;

import main.patchwork.Forme;
import main.patchwork.Point;

public class Transformation {
    public static void translation(Forme f, final int x, final int y){
        Point p = new Point(f.getX()+x, f.getY()+y);
        Forme forme = new Forme();
    }
}