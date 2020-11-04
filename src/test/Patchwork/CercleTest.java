package test.Patchwork;

import main.patchwork.Cercle;
import main.patchwork.Ligne;
import main.patchwork.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class CercleTest {
    private static final double DELTA = 1e-2;

    private static Cercle c;
    @BeforeEach
    void setUp() {
        c = new Cercle(new Point(8,6),new Point(10,6));
    }

    @Test
    void getCentre() {
        assertEquals(c.getCentre(),new Point(8,6));
    }

    @Test
    void getAire() {
        assertEquals(c.getAire(),Math.PI*4);
    }

    @Test
    void getPerimetre() {
        assertEquals(c.getPerimetre(),Math.PI*4);
    }

    @Test
    void getPoints() {
        HashSet<Point> hashSet = new HashSet<Point>();
        hashSet.add(new Point(8,6));
        hashSet.add(new Point(10,6));
        assertEquals(hashSet, c.getPoints());
    }

    @Test
    void translation() {
        assertEquals(new Cercle(new Point(13,4), new Point(15,4)), c.translation(5, -2));
    }

    @Test
    void homothetie() {
        assertEquals(new Cercle(new Point(44,26), new Point(50,26)), c.homothetie(new Point(-10,-4),3));
    }

    @Test
    void rotation() {
        assertEquals(new Cercle(new Point(4,10), new Point(6,10)), c.rotation(new Point(4,6),90));
        //  assertEquals(new Cercle(new Point(4,2), new Point(5,2)).rotation(new Point(2,4),45).getPoints(), new Cercle(new Point(4,6), new Point(5,6)).getPoints());
    }

    @Test
    void symetrieCentre() {
        assertEquals(new Cercle(new Point(4,-4), new Point(2,-4)), c.symetrieCentre(new Point(6,1)));
    }

    @Test
    void symetrieAxiale() {
        Cercle temp = (Cercle) c.symetrieAxiale(new Ligne(new Point(2,6),new Point(6,12)));
        assertEquals(-0.31, temp.getCentre().getX(),DELTA);
        assertEquals(11.54,temp.getCentre().getY(),DELTA);
        assertEquals(-0.31+2, temp.getpCercle().getX(),DELTA );
        assertEquals(11.54,temp.getpCercle().getY(),DELTA);
    }
}