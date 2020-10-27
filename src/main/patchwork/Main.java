package main.patchwork;

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
        System.out.println("Création des côtés d'un carré");
        Point p0 = new Point(0, 0);
        Point p1 = new Point(0, 3);
        Point p2 = new Point(3, 3);
        Point p3 = new Point(3, 0);
        Ligne l0 = new Ligne(p0,p1);
        Ligne l1 = new Ligne(p1,p2);
        Ligne l2 = new Ligne(p2,p3);
        Ligne l3 = new Ligne(p3,p0);
        Polygone p = new Polygone();
        p.addLigne(l0);
        p.addLigne(l1);
        p.addLigne(l2);
        p.addLigne(l3);
        i.addForme(p);

        System.out.println("Affichage de l'image");
        System.out.println(i.toString());
        System.out.println("Affichage du dessin");
        System.out.println(d.toString());
        System.out.println("Affichage de la fresque");
        System.out.println(f.toString());

    }
}
