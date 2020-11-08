package main.patchwork;

import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeMap;

public class Main {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    private static final TreeMap<String,String> colors = new TreeMap<String,String>(){{put("black",ANSI_BLACK);put("red",ANSI_RED);put("green",ANSI_GREEN);put("blue",ANSI_BLUE);put("purple",ANSI_PURPLE);}};

    public static void main(String[] args){
        System.out.println("Création de la fresque");
        Fresque f = new Fresque();
        System.out.println("Ajout d'un dessin à la fresque");
        Dessin d = new Dessin();
        f.addDessin(d);
        System.out.println("Ajout d'une image à ce dessin");
        Image i = new Image();
        d.addImage(i);
        System.out.println(printWithColor("\n###################### CERCLE #############################\n","red"));
        Cercle c = new Cercle(new Point(9,6),new Point(10,9));

        System.out.println("Creation d'un cercle de centre " +printWithColor(""+c.getCentre(),"blue") + " et de rayon "+printWithColor(""+c.getRayon(),"blue"));
        System.out.println("Aire du Cercle = "+printWithColor(""+c.getAire(),"blue"));
        System.out.println("Perimetre du Cercle = "+printWithColor(""+c.getPerimetre(),"blue"));

        System.out.println(printWithColor("\n           _________ translation _________ ","green"));
        System.out.println(printWithColor(" X = + 5 \n Y = - 2","purple"));
        Cercle temp = (Cercle) c.translation(5, -2);
        System.out.println("nouveau centre du cercle : "+printWithColor(""+temp.getCentre(),"blue"));

        System.out.println(printWithColor("\n           _________ homothetie _________ ","green"));
        System.out.println("grace au Point"+printWithColor(" (-10,-4)","purple")+" et de rapport "+printWithColor("3","purple"));
        temp = (Cercle) c.homothetie(new Point(-10,-4),3);
        System.out.println("nouveau centre du cercle : "+printWithColor(""+temp.getCentre(),"blue"));

        System.out.println(printWithColor("\n           _________ rotation _________ ","green"));
        System.out.println("autour du Point(4,6) et avec un angle de "+printWithColor("90°","purple"));
        temp = (Cercle) c.rotation(new Point(4,6),90);
        System.out.println("nouveau centre du cercle : "+printWithColor(""+temp.getCentre(),"blue"));

        System.out.println(printWithColor("\n           _________ symetrie centrale _________ ","green"));
        System.out.println("grace au Point "+printWithColor("(6,1)","purple"));
        temp = (Cercle) c.symetrieCentre(new Point(6,1));
        System.out.println("nouveau centre du cercle : "+printWithColor(""+temp.getCentre(),"blue"));

        System.out.println(printWithColor("\n           _________ symetrie axiale _________ ","green"));
        System.out.println("grace aux Points (2,6) et (6,12)");
        temp = (Cercle) c.symetrieAxiale(new Ligne(new Point(2,6),new Point(6,12)));
        System.out.println("nouveau centre du cercle : "+printWithColor(""+temp.getCentre(),"blue"));
        System.out.println(printWithColor("\n###################### ELLIPSE #############################\n","red"));

        System.out.println(printWithColor("\n###################### POLYGONE #############################\n","red"));


    }


    private static String printWithColor(String stringToPrint, String color){
        return colors.get(color) + stringToPrint + ANSI_RESET;
    }


}
