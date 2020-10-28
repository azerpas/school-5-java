package main.patchwork;

import java.util.HashSet;

public class Main {
    public static void main(String[] args){
        System.out.println("Création de la fresque");
        Fresque f = new Fresque();
        System.out.println("Ajout d'un dessin à la fresque");
        Dessin d = new Dessin();
        f.addDessin(d);
        System.out.println("Ajout d'une image à ce dessin");
        Image i = new Image();
        d.addImage(i);
        System.out.println("Création des côtés d'un TRIANGLE 00 => 30 => 33  Normalement : aire = 4.5");


        Point p00 = new Point(0, 0);
        Point p30 = new Point(3, 0);
        Point p33 = new Point(3, 3);
        Point p03 = new Point(0, 3);
        Point p60 = new Point(6, 0);
        Point p63 = new Point(6, 3);

        /**
         * TRIANGLE 00 => 30 => 33  Normalement : aire = 4.5
         */
        Polygone triangle = new Polygone();
        triangle.addPoint(p00);
        triangle.addPoint(p30);
        triangle.addPoint(p33);
        i.addForme(triangle);

        System.out.println("Création des côtés d'un CARRE 00 => 30 => 33 => 03 Normalement : aire = 9");
        /**
         * CARRE 00 => 30 => 33 => 03 Normalement : aire = 9
         */
        HashSet<Point> h = new HashSet<>();
        h.add(p00);
        h.add(p30);
        h.add(p33);
        h.add(p03);
        Polygone carre = new Polygone(h);
        i.addForme(carre);

        System.out.println("Création des côtés d'un Rectangle 00 => 60 => 63 => 03  Normalement : aire = 18");
        /**
         * Rectangle 00 => 60 => 63 => 03  Normalement : aire = 18
         */

        Polygone rectangle = new Polygone();
        rectangle.addPoint(p00);
        rectangle.addPoint(p60);
        rectangle.addPoint(p63);
        rectangle.addPoint(p03);
        i.addForme(rectangle);

        System.out.println(d.getAire());
        System.out.println("Affichage de l'image");
        System.out.println(i.toString());
        System.out.println("Affichage du dessin");
        System.out.println(d.toString());
        System.out.println("Affichage de la fresque");
        System.out.println(f.toString());


    }
}
