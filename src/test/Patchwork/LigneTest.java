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
        //assertEquals(new HashSet<Point>(Arrays.asList(new Point(40,28),new Point(25,73))),l.homothetie(new Point(-10,-7),5).getPoints());
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