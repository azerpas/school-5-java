package test.Patchwork;

import main.patchwork.Cercle;
import main.patchwork.Ligne;
import main.patchwork.Point;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CercleTest {

    private static Cercle c;
    @BeforeEach
    void setUp() {
        c = new Cercle(new Point(2,2),1);
        System.out.println("CERCLE TEST BEFORE : " +c);
    }

    @Test
    void getCentre() {
        System.out.println("CERCLE TEST getCentre : " +c);
        assertEquals(c.getCentre(),new Point(2,2));
    }

    @Test
    void getAire() {
        assertEquals(c.getAire(),Math.PI*1);
    }

    @Test
    void getPerimetre() {
        assertEquals(c.getPerimetre(),Math.PI*2);
    }

    @Test
    void getPoints() {
        Set hashSet = new HashSet<Point>();
        hashSet.add(c.getCentre());
        System.out.println(hashSet);
        System.out.println(c.getPoints());
        assertEquals(hashSet, c.getPoints());
    }

    @Test
    void translation() {
        assertEquals(new Cercle(new Point(), 1), c.translation(0, 0));
    }

    @Test
    void homothetie() {
        assertEquals(new Cercle(new Point(2,2), 3), c.homothetie(c.getCentre(),3));
    }

    @Test
    void rotation() {
        assertEquals(new Cercle(new Point(1,3), 1), c.rotation(new Point(1,2),45));
    }

    @Test
    void symetrieCentre() {
        assertEquals(new Cercle(new Point(0,2), 1), c.symetrieCentre(new Point(1,2)));
    }

    @Test
    void symetrieAxiale() {
        assertEquals(new Cercle(new Point(0,2), 1), c.symetrieAxiale(new Ligne(new Point(1,0),new Point(1,4))));
    }
}