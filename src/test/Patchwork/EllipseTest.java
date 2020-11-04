package test.Patchwork;

import main.patchwork.Ellipse;
import main.patchwork.Ligne;
import main.patchwork.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class EllipseTest {

    private static final double DELTA = 1e-2;
    private static Ellipse e;
    private static Point pAA;
    private static Point pAB;
    private static Point pBA;
    private static Point pBB;

    @BeforeEach
    void setUp() {
        pAA = new Point(5,2);
        pAB = new Point(3,2);
        pBA = new Point(4,4);
        pBB = new Point(4,0);
        e = new Ellipse(new Point(4,2),new Ligne(pAA,pAB),new Ligne(pBA,pBB));
    }

    @Test
    void getCentre() {
        assertEquals(e.getCentre(),new Point(4,2));
    }

    @Test
    void getAire() {
        assertEquals(e.getAire(),Math.PI*2);
    }

    @Test
    void getPerimetre() {
        assertEquals(e.getPerimetre(),2*Math.PI*Math.sqrt(2.5));
    }

    @Test
    void getPoints() {
        HashSet<Point> h = new HashSet<>();
        h.add(pAA);
        h.add(pAB);
        h.add(pBA);
        h.add(pBB);
        assertEquals(e.getPoints(),h);
    }

    @Test
    void translation() {
        Ellipse newEllipse = new Ellipse(new Point(6,0),new Ligne(new Point(7,0),new Point(5,0)),new Ligne(new Point(6,2),new Point(6,-2)));
        assertEquals(e.translation(2,-2).getPoints(),newEllipse.getPoints());
    }

    @Test
    void homothetie() {
        Ellipse ellipseTemp = (Ellipse) e.homothetie(new Point(12,6),-3);
        HashSet h = new HashSet<Point>(Arrays.asList(new Point(39,18), new Point(36,12), new Point(33,18), new Point(36,24)));
        assertEquals(h,ellipseTemp.getPoints());
        assertEquals(ellipseTemp.getCentre(),new Point(36,18));
    }

    @Test
    void rotation() {
        Ellipse temp = (Ellipse) e.rotation(new Point(2,4),90);
        assertEquals(new HashSet<Point>(Arrays.asList(new Point(4,7),new Point(4,5),new Point(2,6),new Point(6,6))), temp.getPoints());
        assertEquals(new Point(4,6),temp.getCentre());
    }

    @Test
    void symetrieCentre() {
        Ellipse temp = (Ellipse)  e.symetrieCentre(new Point(6,1));
        assertEquals(new HashSet<Point>(Arrays.asList(new Point(7,0),new Point(9,0),new Point(8,-2),new Point(8,2))),temp.getPoints());
        assertEquals(new Point(8,0),temp.getCentre());
    }

    @Test
    void symetrieAxiale() {
        Ellipse temp = (Ellipse) e.symetrieAxiale(new Ligne(new Point(2,6),new Point(6,12)));
        HashSet h = new HashSet<Point>(Arrays.asList(new Point(-2.85,7.23), new Point(-2.08,5.38), new Point(-0.62,7.08), new Point(-4.31, 5.54)));
        assertEquals(-2.46, temp.getCentre().getX(),DELTA);
        assertEquals(6.31,temp.getCentre().getY(),DELTA);
        assertEquals(h,temp.getPoints());

    }
}