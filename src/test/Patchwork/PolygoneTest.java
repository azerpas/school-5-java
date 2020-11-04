package test.Patchwork;

import main.patchwork.Ligne;
import main.patchwork.Point;
import main.patchwork.Polygone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PolygoneTest {

    private static Polygone triangle;
    private static Polygone carre;
    private static Polygone rectangle;

    @BeforeEach
    void setUp() {
        Point p00 = new Point(0, 0);
        Point p30 = new Point(3, 0);
        Point p33 = new Point(3, 3);
        Point p03 = new Point(0, 3);
        Point p60 = new Point(6, 0);
        Point p63 = new Point(6, 3);
        triangle = new Polygone();
        triangle.addPoint(p00);
        triangle.addPoint(p30);
        triangle.addPoint(p33);

        HashSet<Point> h = new HashSet<>();
        h.add(p00);
        h.add(p30);
        h.add(p33);
        h.add(p03);
        carre = new Polygone(h);

        rectangle = new Polygone();

        rectangle.addPoint(p60);
        rectangle.addPoint(p00);
        rectangle.addPoint(p03);
        rectangle.addPoint(p63);
    }

    @Test
    void getAire() {
        assertEquals(rectangle.getAire(),18);
        assertEquals(carre.getAire(),9);
        assertEquals(triangle.getAire(),4.5);
    }

    @Test
    void getPoints() {
        Point p00 = new Point(0, 0);
        Point p30 = new Point(3, 0);
        Point p33 = new Point(3, 3);
        Point p03 = new Point(0, 3);
        Point p60 = new Point(6, 0);
        Point p63 = new Point(6, 3);

        Set<Point> hashSetTriangle = new HashSet<Point>();
        hashSetTriangle.add(p00);
        hashSetTriangle.add(p30);
        hashSetTriangle.add(p33);
        assertEquals(hashSetTriangle, triangle.getPoints());

        Set<Point> hashSetCarre = new HashSet<Point>();
        hashSetCarre.add(p00);
        hashSetCarre.add(p30);
        hashSetCarre.add(p33);
        hashSetCarre.add(p03);
        assertEquals(hashSetCarre, carre.getPoints());

        Set<Point> hashSetRectangle = new HashSet<Point>();
        hashSetRectangle.add(p00);
        hashSetRectangle.add(p60);
        hashSetRectangle.add(p63);
        hashSetRectangle.add(p03);

        assertEquals(hashSetRectangle, rectangle.getPoints());


    }

    @Test
    void getCentre() {
        assertEquals(rectangle.getCentre(),new Point(3,1.5));
        assertEquals(carre.getCentre(),new Point(1.5,1.5));
        assertEquals(triangle.getCentre(),new Point(2,1));
    }

    @Test
    void getPerimetre() {
        assertEquals(rectangle.getPerimetre(),18);
        assertEquals(carre.getPerimetre(),12);
        assertEquals(triangle.getPerimetre(),9);
    }

    @Test
    void translation() {

        assertEquals(new HashSet<Point>(Arrays.asList(new Point(5,5), new Point(11,8), new Point(11,5), new Point(5,8))), rectangle.translation(5, 5).getPoints());
        assertEquals(new HashSet<Point>(Arrays.asList(new Point(5,5), new Point(8,5), new Point(5,8), new Point(8,8))), carre.translation(5, 5).getPoints());
        assertEquals(new HashSet<Point>(Arrays.asList(new Point(5,5), new Point(8,5), new Point(8,8))), triangle.translation(5, 5).getPoints());
    }

    @Test
    void homothetie() {
        assertFalse(true);
    }

    @Test
    void rotation() {
        assertEquals(new HashSet<Point>(Arrays.asList(new Point(0,0), new Point(0,-3), new Point(-6,-3), new Point(-6, 0))), rectangle.rotation(new Point(0,0), 180.00).getPoints());
        assertEquals(new HashSet<Point>(Arrays.asList(new Point(0,0), new Point(0,-3), new Point(-3,-3), new Point(-3, 0))), carre.rotation(new Point(0,0), 180.00).getPoints());
        assertEquals(new HashSet<Point>(Arrays.asList(new Point(0,0), new Point(-3,0), new Point(-3,-3))), triangle.rotation(new Point(0,0), 180.00).getPoints());
    }

    @Test
    void symetrieCentre() {
        assertFalse(true);
    }

    @Test
    void symetrieAxiale() {
        assertEquals(new HashSet<Point>(Arrays.asList(new Point(-4.8,3.6), new Point(0,0), new Point(1.8,2.4), new Point(-3, 6))), rectangle.symetrieAxiale(new Ligne(new Point(0,0),new Point(1,3))).getPoints());
        assertEquals(new HashSet<Point>(Arrays.asList(new Point(0,0), new Point(-2.4,1.8), new Point(-0.6,4.2), new Point(1.8,2.4))), carre.symetrieAxiale(new Ligne(new Point(0,0),new Point(1,3))).getPoints());
        assertEquals(new HashSet<Point>(Arrays.asList(new Point(0,0), new Point(-2.4,1.8), new Point(-0.6,4.2))), triangle.symetrieAxiale(new Ligne(new Point(0,0),new Point(1,3))).getPoints());
    }

    @Test
    @DisplayName("Vérifie que les points sont triés en horloge")
    void sortedPoints(){

        //Polygone trigl = new Polygone(new HashSet<Point>(Arrays.asList(new Point(8,8), new Point(8,5), new Point(5,5))));
        System.out.println(carre);
        assertEquals(
            new ArrayList<Point>(Arrays.asList(new Point(0,0), new Point(3,0), new Point(3,3), new Point(0,3))),
            carre.getSortedPoints()
        );
    }
}