package test.Patchwork;

import main.patchwork.Ellipse;
import main.patchwork.Ligne;
import main.patchwork.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class EllipseTest {

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
        assertEquals(e.getPerimetre(),2*Math.PI*Math.sqrt(8));
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
        assertEquals(e.translation(2,-2),new Ellipse(new Point(6,0),new Ligne(new Point(9,0),new Point(5,0)),new Ligne(new Point(2,0),new Point(2,-4))));
    }

    @Test
    void homothetie() {
    }

    @Test
    void rotation() {
    }

    @Test
    void symetrieCentre() {
    }

    @Test
    void symetrieAxiale() {
    }
}