package main.console;

import java.util.Scanner;

public class Menu {
    public static int chooseMenu(Scanner sc){
        int choix = sc.nextInt();
        sc.skip("((?<!\\R)\\s)*");
        if(choix < 0){
            System.out.print("Erreur.\nEntrez un numéro du menu: ");
            return chooseMenu(sc);
        }
        return choix;
    }

    public static void showMenu(String stage){
        System.out.println("\nSélectionnez une action:");
        System.out.println("--------------------------");
        switch(stage){
            default:
                System.out.println("1. Ajouter un dessin");
                break;
            case "dessin":
                System.out.println("1. Ajouter une image");
                break;
            case "image":
                System.out.println("1. Ajouter une forme");
                break;
            case "forme":
                System.out.println("1. Créer une ligne");
                System.out.println("2. Créer un cercle");
                System.out.println("3. Créer une ellipse");
                System.out.println("4. Créer un polygone");
                break;
        }
        System.out.println("7. Afficher la fresque");
        System.out.println("0. Quitter "+stage);
    }

    public static int[] initPoint(Scanner sc){
        System.out.print("x: ");
        int x = sc.nextInt();
        sc.skip("((?<!\\R)\\s)*");
        System.out.print("y: ");
        int y = sc.nextInt();
        sc.skip("((?<!\\R)\\s)*");
        return new int[] {x,y};
    }
}
