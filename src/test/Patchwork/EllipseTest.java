package test.Patchwork;

import main.patchwork.Ellipse;
import main.patchwork.Ligne;
import main.patchwork.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        assertFalse(true);
    }

    @Test
    void rotation() {
        assertEquals(new Ellipse(new Point(4,6),new Ligne(new Point(4,7),new Point(4,5)), new Ligne(new Point(2,6),new Point(6,6))).getPoints(), e.rotation(new Point(2,4),90).getPoints());
    }

    @Test
    void symetrieCentre() {
        assertEquals(new Ellipse(new Point(8,0),new Ligne(new Point(7,0),new Point(9,0)),new Ligne(new Point(8,-2),new Point(8,2))).getPoints(), e.symetrieCentre(new Point(6,1)).getPoints());
    }

    @Test
    void symetrieAxiale() {
        Ellipse temp = (Ellipse) e.symetrieAxiale(new Ligne(new Point(2,6),new Point(6,12)));
        assertEquals(-2.46, temp.getCentre().getX(),DELTA);
        assertEquals(6.31,temp.getCentre().getY(),DELTA);

        assertEquals(-2.85, temp.getpetitAxe().getPointA().getX(),DELTA);
        //assertEquals(7.23, temp.getpetitAxe().getPointA().getY(),DELTA);

        assertEquals(-2.08,  temp.getpetitAxe().getPointB().getX(),DELTA);
        assertEquals(5.38, temp.getpetitAxe().getPointB().getY(),DELTA);

        assertEquals(-0.62,  temp.getgrandAxe().getPointA().getX(),DELTA);
        assertEquals(7.08, temp.getgrandAxe().getPointA().getY(),DELTA);

        assertEquals(-4.31,  temp.getgrandAxe().getPointB().getX(),DELTA);
        assertEquals(5.54, temp.getgrandAxe().getPointB().getY(),DELTA);
    }
}