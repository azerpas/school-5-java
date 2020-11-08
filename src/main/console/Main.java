package main.console;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import main.patchwork.Cercle;
import main.patchwork.Dessin;
import main.patchwork.Ellipse;
import main.patchwork.Fresque;
import main.patchwork.Image;
import main.patchwork.Ligne;
import main.patchwork.Point;

public class Main {

    public static void main(String[] args){
        System.out.println("\nBienvenue");
        System.out.println("Initialisation de la fresque");
        Fresque fresque = new Fresque();
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        while(exit == false){
            Menu.showMenu("");
            int choix = Menu.chooseMenu(sc);
            switch(choix){
                
                case 1: // Ajout dessin
                    Dessin d = new Dessin();
                    int choixDessin = -1;
                    fresque.addDessin(d);
                    while(choixDessin != 0){
                        Menu.showMenu("dessin");
                        choixDessin = Menu.chooseMenu(sc);
                        switch(choixDessin){

                            case 1: // Ajout image
                                Image i = new Image();
                                d.addImage(i);
                                int choixImage = -1;
                                while(choixImage != 0){
                                    Menu.showMenu("image");
                                    choixImage = Menu.chooseMenu(sc);
                                    switch(choixImage){

                                        case 1: // Ajout forme
                                            int choixForme = -1;
                                            while(choixForme != 0){
                                                Menu.showMenu("forme");
                                                choixForme = Menu.chooseMenu(sc);
                                                int[] xy = {}; // 
                                                Point p1, p2, p3;
                                                Ligne l1, l2;
                                                switch(choixForme){
                                                    case 1: // Ligne
                                                        System.out.println("Définissez le premier point:");
                                                        xy = Menu.initPoint(sc);
                                                        p1 = new Point(xy[0],xy[1]);
                                                        System.out.println("Définissez le deuxième point:");
                                                        xy = Menu.initPoint(sc);
                                                        p2 = new Point(xy[0],xy[1]);
                                                        l1 = new Ligne(p1, p2);
                                                        i.addForme(l1);
                                                        break;
                                                    case 2: // Cercle
                                                        System.out.println("Définissez le centre du cercle:");
                                                        xy = Menu.initPoint(sc);
                                                        p1 = new Point(xy[0],xy[1]);
                                                        System.out.println("Définissez un point pour qui la distance avec le centre représente le rayon:");
                                                        xy = Menu.initPoint(sc);
                                                        p2 = new Point(xy[0],xy[1]);
                                                        Cercle c = new Cercle(p1,p2);
                                                        i.addForme(c);
                                                        break;
                                                    case 3: // Ellipse
                                                        System.out.println("Définissez le centre de l'ellipse:");
                                                        xy = Menu.initPoint(sc);
                                                        p1 = new Point(xy[0],xy[1]);
                                                        System.out.println("Définissez un point pour qui la distance avec le centre représente le petit rayon:");
                                                        xy = Menu.initPoint(sc);
                                                        p2 = new Point(xy[0],xy[1]);
                                                        System.out.println("Définissez un point pour qui la distance avec le centre représente le grand rayon:");
                                                        xy = Menu.initPoint(sc);
                                                        p3 = new Point(xy[0],xy[1]);
                                                        l1 = new Ligne(p1, p2);
                                                        l2 = new Ligne(p1, p3);
                                                        Ellipse e = new Ellipse(p1, l1, l2);
                                                        break;
                                                    case 4: // Polygone
                                                        Set<Point> pts = new HashSet<Point>();
                                                        for(int j = 0; j < 3; j++){
                                                            System.out.println("Définissez un point du Polygone (Étape "+j+"/3):");
                                                            xy = Menu.initPoint(sc);
                                                            // TODO: Try/Catch
                                                            pts.add(new Point(xy[0], xy[1]));
                                                        }
                                                        boolean stop = false;
                                                        while(!stop){
                                                            System.out.println("1. Ajouter un point au polygone");
                                                            System.out.println("0. Fin du polygone");
                                                            int choixPolygone = Menu.chooseMenu(sc);
                                                            switch(choixPolygone){
                                                                case 1:
                                                                    System.out.println("Définissez un point du Polygone:");
                                                                    xy = Menu.initPoint(sc);
                                                                    // TODO: Try/Catch
                                                                    pts.add(new Point(xy[0], xy[1]));
                                                                    break;
                                                                case 0:
                                                                    stop = true;
                                                                    break;
                                                            }
                                                        }
                                                        break;
                                                    case 7: // Affichage de la fresque
                                                        System.out.println(fresque.toString());
                                                        break;
                                                }
                                            }
                                            break;
                                        case 7: // Affichage de la fresque
                                            System.out.println(fresque.toString());
                                            break;
                                    }
                                }
                                break;
                            case 7: // Affichage de la fresque
                                System.out.println(fresque.toString());
                                break;
                        }
                        
                    }
                    break;

                case 7: // Affichage de la fresque
                    System.out.println(fresque.toString());
                    break;

                case 0: // Exit
                    exit = true;
                    break;
            }
        }
        sc.close();
        
    }

    
}
