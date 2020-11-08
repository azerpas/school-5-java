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

public class Read{
    /**
     * Read a JSON File from an absolute path
     * @param absolutePath the absolute path on current user session. Ex: C:/Users/Mickael/Documents/fresque.json
     * @return JSONObject
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
            
        }catch(JSONException e){
            throw new Error("Double check JSON file: "+e.getMessage());
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
        JSONObject jsonFile = new JSONObject(rawJson.toString());
        return jsonFile;
    }

    public static Fresque jsonToFresque(JSONObject object) throws Error{
        Fresque f = new Fresque();
        // TODO: Try/Catch
        JSONArray dessins = object.getJSONArray("dessins");
        Set<Image> ds = new HashSet<Image>();
        for(int i = 0; i < dessins.length(); i++){
            Dessin dessin = new Dessin();
            // TODO: Try/Catch
            JSONArray images = dessins.getJSONObject(i).getJSONArray("images");
            for(int j = 0; j < images.length(); j++){
                // TODO: Try/Catch
                Image image = new Image();
                JSONArray formes = images.getJSONObject(j).getJSONArray("formes");
                for(int o = 0; o < formes.length(); o++){
                    // TODO: Try/Catch
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
                            Ellipse e = new Ellipse(
                                new Point(
                                    forme.getJSONObject("p1").getDouble("x"), forme.getJSONObject("p1").getDouble("y")
                                ), ls.get(0), ls.get(1)
                            );
                            image.addForme(e);
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
                            // wrong type throw
                            break;
                    }
                }
            }
        }
        return f;
    }
}