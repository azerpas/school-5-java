package main.read;

import main.patchwork.Cercle;
import main.patchwork.Dessin;
import main.patchwork.Ellipse;
import main.patchwork.Fresque;
import main.patchwork.Image;
import main.patchwork.Ligne;
import main.patchwork.Point;
import main.patchwork.Polygone;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * The class needed to read files and especially JSON file representing a Fresque
 * ------------
 * JSON FORMAT:
 * ------------
 * 
 * {
 *  "dessins":[
 *      {
 *          "images":[
 *              {
 *                  "formes":[
 *                      {
 *                          "type": TYPE_ENUM,
 *                          ...
 *                      }
 *                  ]
 *              }
 *          ]
 *      }   
 *  ]
 * }
 * 
 * -----------
 * TYPE_ENUM: ligne | cercle | polygone | ellipse
 * 
 * ligne attributes: p1, p2: Point
 * cercle attributes: p1, p2: Point
 * polygone attributes: pts: [Point]
 * ellipse attributes: p1: Point, [ligne]
 * 
 * Point: {"x": double, "y": double}
 */
public class Read{
    /**
     * Read a JSON File from an absolute path
     * @param absolutePath the absolute path on current user session. Ex: C:/Users/Mickael/Documents/fresque.json
     * @return JSONObject
     * @throws Error
     */
    public static JSONObject readJsonFile(String absolutePath) throws Error{
        StringBuilder rawJson = new StringBuilder();
        try {
            FileInputStream file = new FileInputStream(absolutePath);
            int i; 
            while((i = file.read()) != -1){    
                rawJson.append((char)i);    
            }  
        }catch(FileNotFoundException e){
            throw new Error("File not found");
        }catch (Exception e) {
            throw new Error(e.getMessage());
        }
        try {
            JSONObject jsonFile = new JSONObject(rawJson.toString());
            return jsonFile;
        }catch(JSONException e){
            throw new Error("Double check JSON file: "+e.getMessage());
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

    /**
     * Convert a JSONObject to a Fresque entity
     * @param object JSONObject
     * @return Fresque Entity
     * @throws Error
     */
    public static Fresque jsonToFresque(JSONObject object) throws Error{
        Fresque f = new Fresque();
        try {
            JSONArray dessins = object.getJSONArray("dessins");
            if(dessins.length() < 1) throw new Error("Il n'y a pas assez de dessins");
            for(int i = 0; i < dessins.length(); i++){
                Dessin dessin = new Dessin();
                f.addDessin(dessin);
                try {
                    JSONArray images = dessins.getJSONObject(i).getJSONArray("images");
                    for(int j = 0; j < images.length(); j++){
                        Image image = new Image();
                        dessin.addImage(image);
                        try {
                            JSONArray formes = images.getJSONObject(j).getJSONArray("formes");
                            for(int o = 0; o < formes.length(); o++){
                                try {
                                    JSONObject forme = formes.getJSONObject(o);
                                    String type = forme.getString("type");
                                    switch(type.toLowerCase().strip()){ // On enlève tous les bruits (Majuscules, Espaces)
                                        case "ligne":
                                            Ligne l = new Ligne(
                                                new Point(
                                                    forme.getJSONObject("p1").getDouble("x"), forme.getJSONObject("p1").getDouble("y")
                                                ), 
                                                new Point(
                                                    forme.getJSONObject("p2").getDouble("x"), forme.getJSONObject("p2").getDouble("y")
                                                )
                                            );
                                            image.addForme(l);
                                            break;
                                        case "ellipse":
                                            JSONArray lignes = forme.getJSONArray("lignes");
                                            ArrayList<Ligne> ls = new ArrayList<Ligne>();
                                            for(int k = 0; k < lignes.length(); k++){
                                                JSONObject ligne = lignes.getJSONObject(k);
                                                ls.add(new Ligne(
                                                    new Point(ligne.getJSONObject("p1").getDouble("x"), ligne.getJSONObject("p1").getDouble("y")),
                                                    new Point(ligne.getJSONObject("p2").getDouble("x"), ligne.getJSONObject("p2").getDouble("y"))
                                                ));
                                            }
                                            try {
                                                Ellipse e = new Ellipse(
                                                   new Point(
                                                        forme.getJSONObject("p1").getDouble("x"), forme.getJSONObject("p1").getDouble("y")
                                                    ), ls.get(0), ls.get(1)
                                                );
                                                image.addForme(e);
                                            } catch (IndexOutOfBoundsException e) {
                                                throw new Error("Please state every line for Ligne n°"+o+", Image n°"+j+", Dessin n°:"+i);
                                            }
                                            break;
                                        case "polygone":
                                            JSONArray points = forme.getJSONArray("pts");
                                            HashSet<Point> pts = new HashSet<Point>();
                                            for(int k = 0; k < points.length(); k++){
                                                pts.add(new Point(
                                                    points.getJSONObject(k).getDouble("x"),
                                                    points.getJSONObject(k).getDouble("y")
                                                ));
                                            }
                                            if(pts.size() > 2){
                                                image.addForme(
                                                    new Polygone(pts)
                                                );
                                            }else{
                                                System.out.println("La forme n°"+o+", de l'image n°"+j+", du dessin n°"+i+"  de type Polygone n'est pas ajoutée car moins de 3 points.");
                                            }
                                            break;
                                        case "cercle":
                                            Cercle c = new Cercle(
                                                new Point(
                                                    forme.getJSONObject("p1").getDouble("x"), forme.getJSONObject("p1").getDouble("y")
                                                ), 
                                                new Point(
                                                    forme.getJSONObject("p2").getDouble("x"), forme.getJSONObject("p2").getDouble("y")
                                                )
                                            );
                                            image.addForme(c);
                                            break;
                                        default: 
                                            throw new Error("Unrecognized 'type' for Forme n°"+o+", Image n°"+j+", Dessin n°:"+i);
                                    }
                                } 
                                catch(JSONException e){
                                    System.out.println("Canno't read Forme n°"+o+", Image n°"+j+", Dessin n°:"+i+": "+e.getMessage());
                                } 
                                catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                                continue;
                                
                            }
                        } 
                        catch(JSONException e){
                            System.out.println("Canno't find 'formes' array from Image n°"+j+", Dessin n°:"+i+": "+e.getMessage());
                        } 
                        catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        continue; 
                    }
                }
                catch(JSONException e){
                    System.out.println("Canno't find 'images' array from Dessin n°"+i+": "+e.getMessage());
                } 
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                continue;
            }
        } 
        catch(JSONException e){
            throw new Error("Canno't find 'dessins' array from JSON file: "+e.getMessage());
        }
        catch (Exception e) {
            throw new Error(e.getMessage());
        }
        return f;
    }
}