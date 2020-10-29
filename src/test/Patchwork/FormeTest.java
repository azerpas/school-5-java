package test.Patchwork;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import main.patchwork.Ligne;
import main.patchwork.Point;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormeTest {
    
    private final Ligne l = new Ligne(new Point(3,0), new Point(5,0));

    @Test
    @DisplayName("Vérifier le point central de la ligne")
    public void centreDeLigne(){
        assertEquals(new Point(4,0), l.getCentre());
    }
}
