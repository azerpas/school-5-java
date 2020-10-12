package main.utils;

import main.patchwork.Forme;

public class Transformation {
    public static void translation(Forme f, final int x, final int y){
        f.setX(f.getX()+x);
        f.setY(f.getY()+y);
    }
}