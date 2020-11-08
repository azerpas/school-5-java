package test.Patchwork;

import main.patchwork.Cercle;
import main.patchwork.Ligne;
import main.patchwork.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

//import static org.junit.jupiter.api.Assertions.*;

class LigneTest {

    private static Ligne l;
    @BeforeEach
    void setUp() {
        l = new Ligne(new Point(0,0),new Point(-3,9));
    }

    @Test
    void getCentre() {
        assertEquals(l.getCentre(),new Point(-1.5,4.5));
    }

    @Test
    void getAire() {
        assertEquals(l.getAire(),0);
    }

    @Test
    void getPerimetre() {
        assertEquals(l.getPerimetre(),9.48);
    }

    @Test
    void getPoints() {
        assertEquals(new HashSet<Point>(Arrays.asList(new Point(0,0),new Point(-3,9))),l.getPoints());
    }

    @Test
    void translation() {
        assertEquals(new HashSet<Point>(Arrays.asList(new Point(-5,5),new Point(-8,14))),l.translation(-5,5).getPoints());
    }

    @Test
    void homothetie() {
        assertEquals(new HashSet<Point>(Arrays.asList(new Point(-36,-24),new Point(-21,-69))),l.homothetie(new Point(-6,-4),-5).getPoints());
    }

    @Test
    void rotation() {
        assertEquals(new HashSet<Point>(Arrays.asList(new Point(-3,7),new Point(-12,3.99))),l.rotation(new Point(-5,2),90).getPoints());
    }

    @Test
    void symetrieCentre() {
        assertEquals(new HashSet<Point>(Arrays.asList(new Point(-12,-8),new Point(-9,-17))),l.symetrieCentre(new Point(-6,-4)).getPoints());
    }

    @Test
    void symetrieAxiale() {
        assertEquals(new HashSet<Point>(Arrays.asList(new Point(18,-18),new Point(27,-21))),l.symetrieAxiale(new Ligne(new Point(4,-14),new Point(14,-4))).getPoints());
    }
}