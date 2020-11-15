package test.Read;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.patchwork.Cercle;
import main.patchwork.Dessin;
import main.patchwork.Ellipse;
import main.patchwork.Fresque;
import main.patchwork.Image;
import main.patchwork.Ligne;
import main.patchwork.Point;
import main.patchwork.Polygone;
import main.read.Read;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;


class ReadTest {
    @Test
    @DisplayName("Retourne bien une fresque")
    void testJson() throws JSONException {
        String j = "{\"dessins\":[{\"images\":[{\"formes\":[{\"type\":\"ligne\",\"p1\":{\"x\":2,\"y\":3},\"p2\":{\"x\":0,\"y\":0}},{\"type\":\"cercle\",\"p1\":{\"x\":3,\"y\":0},\"p2\":{\"x\":9,\"y\":9}}]},{\"formes\":[{\"type\":\"polygone\",\"pts\":[{\"x\":2,\"y\":0},{\"x\":0,\"y\":0},{\"x\":0,\"y\":2}]},{\"type\":\"ellipse\",\"p1\":{\"x\":3,\"y\":3},\"lignes\":[{\"type\":\"ligne\",\"p1\":{\"x\":3,\"y\":3},\"p2\":{\"x\":9,\"y\":4}},{\"type\":\"ligne\",\"p1\":{\"x\":3,\"y\":3},\"p2\":{\"x\":15,\"y\":9}}]}]}]}]}";
        JSONObject obj = new JSONObject(j);
        Fresque f = new Fresque();
        Dessin d = new Dessin();
        f.addDessin(d);
        Image i1 = new Image();
        i1.addForme(
            new Ligne(new Point(2.0,3.0), new Point(0, 0))
        );
        i1.addForme(
            new Cercle(new Point(3.0, 0), new Point(9.0, 9.0))
        );
        Image i2 = new Image();
        i2.addForme(
            new Polygone(new HashSet<Point>(Arrays.asList(new Point(2,0), new Point(0,0), new Point(0,2))))
        );
        i2.addForme(
            new Ellipse(new Point(3,3), new Ligne(new Point(3,3), new Point(9,4)), new Ligne(new Point(3,3), new Point(15,9))) 
        );
        d.addImage(i1);
        d.addImage(i2);
        assertEquals(f, Read.jsonToFresque(obj));
    }
}
