package main.console;

import java.util.Scanner;

public class Menu {
    public static int chooseMenu(){
        Scanner sc = new Scanner(System.in);
        int choix = sc.nextInt();
        if(choix < 0){
            System.out.print("Erreur.\nEntrez un numéro du menu: ");
            sc.close();
            return chooseMenu();
        }
        sc.close();
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
        }
        System.out.println("7. Afficher la fresque");
        System.out.println("0. Quitter "+stage);
    }
}
