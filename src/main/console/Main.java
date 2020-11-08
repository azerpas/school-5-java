package main.console;

import java.util.Scanner;

import main.patchwork.Dessin;
import main.patchwork.Fresque;
import main.patchwork.Image;

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
                    System.out.println("Création d'un dessin...");
                    while(choix != 0){
                        Menu.showMenu("dessin");
                        choix = Menu.chooseMenu(sc);
                        switch(choix){
                            case 1:
                                Image i = new Image();
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
