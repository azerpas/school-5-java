package main.console;

import java.util.Scanner;

import main.patchwork.Dessin;
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
                    choix = -1;
                    while(choix != 0){
                        Menu.showMenu("dessin");
                        choix = Menu.chooseMenu(sc);
                        switch(choix){

                            case 1: // Ajout image
                                Image i = new Image();
                                choix = -1;
                                while(choix != 0){
                                    Menu.showMenu("image");
                                    choix = Menu.chooseMenu(sc);
                                    switch(choix){

                                        case 1: // Ajout forme
                                            choix = -1;
                                            while(choix != 0){
                                                Menu.showMenu("forme");
                                                choix = Menu.chooseMenu(sc);
                                                switch(choix){
                                                    case 1: // Ligne
                                                        System.out.println("Définissez le premier point:");
                                                        int[] xy = Menu.initPoint(sc);
                                                        Point p1 = new Point(xy[0],xy[1]);
                                                        System.out.println("Définissez le deuxième point:");
                                                        xy = Menu.initPoint(sc);
                                                        Point p2 = new Point(xy[0],xy[1]);
                                                        Ligne l = new Ligne(p1, p2);
                                                        i.addForme(l);
                                                        break;
                                                    case 2: // Cercle
                                                        break;
                                                    case 3: // Ellipse
                                                        break;
                                                    case 4: // Polygone
                                                        break;
                                                }
                                            }
                                            break;
                                    }
                                }

                                d.addImage(i);
                                break;
                            case 7: // Affichage de la fresque
                                fresque.toString();
                                break;
                        }
                        
                    }
                    fresque.addDessin(d);
                    break;

                case 7: // Affichage de la fresque
                    System.out.println("Voici la fresque: ");
                    fresque.toString();
                    break;

                case 0: // Exit
                    exit = true;
                    break;
            }
        }
        sc.close();
        
    }

    
}
