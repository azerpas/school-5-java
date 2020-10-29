package test.Patchwork;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import main.patchwork.Ligne;
import main.patchwork.Point;
import main.patchwork.Polygone;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashSet;

public class FormeTest {
    
    private final Ligne l = new Ligne(new Point(3,0), new Point(5,0));
    private final Polygone p = new Polygone( 
        new HashSet<Point>( Arrays.asList( 
            new Point(0,0), new Point(0,3), new Point(3,0) 
        )));

    @Test
    @DisplayName("Vérifier le point central de la ligne")
    public void centreDeLigne(){
        assertEquals(new Point(4,0), l.getCentre());
    }

    @Test
    @DisplayName("Vérifier la translation d'un polygone")
    public void polygoneTranslation(){
        assertEquals(new HashSet<Point>(Arrays.asList(new Point(5, 5), new Point(8, 5), new Point(5, 8))), p.translation(5, 5).getPoints());
    }

    @Test
    public void polygoneAire(){
        assertEquals(4.5, p.getAire());
    }
}
