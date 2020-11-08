package test.Read;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.patchwork.Fresque;
import main.read.Read;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;


class ReadTest {
    @Test
    @DisplayName("Retourne bien une fresque")
    void retourneFresque(){
        assertTrue(Read.readJsonFile("/Users/azerpas/school/5th/school-5-java/fresque.json") instanceof JSONObject);
    }
}
