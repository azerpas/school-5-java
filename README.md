# school-5-java

## Sujet
https://www.myefrei.fr/moodle/pluginfile.php/164567/mod_resource/content/2/DessineMoiUnMouton.pdf     

## TODO
🔵 Anthony 

🟢 Martin

- [X] Classes / Méthodes
- [X] Tests unitaires
- [X]  Fonction main:

   - [X] Boucle à choix 🟢
   
   - [X] Déroulement de l'algorithme automatique 🔵
   
   - [X] Lecture dans un fichier 🔵
   
- [X] Classe lecture d'un fichier (JSON?) 🔵
- [X] Javadoc (Anthony) 🔵
- [X] Mots-clés: final, static, const 🔵🟢
- [X] Try/Catch
- [ ] Compléter le rapport 🔵🟢

### Si le temps nous le permet
- [ ] Branche avec Point qui implémente Transformation 🟢

## Diagram

https://app.diagrams.net    
https://drive.google.com/file/d/1dVPSjFQF74Z0WybizJRSQcgkDVgsUDgV/view?usp=sharing
![DiagrameUML](./diagram.png?raw=true "Title")

**Entités:**     
- Forme
- Image
- Dessin
- Fresque (iterable)

## Infos
Rien de complexe au niveau de l'affichage de la fresque et de ses dessins : du textuel via les toString!      

Aucune contrainte géométrique n'est à vérifier pour le placement des formes sur la fresque.     
Ainsi, le recouvrement des dessins et des formes n'est pas à gérer.      

Les formes peuvent être générées aléatoirement ou lues dans un fichier (classe Scanner vue en cours).     

Pour itérer, il faut des collections itérables!      

## Classe

methode/classe    | Cercle | Polygone | Ellipse |
----------------- | ------ | -------- |---------|
translation       |   ✅   |    ✅    |   ✅    |
rotation          |   ✅   |    ✅    |   ✅    |
homothetie        |   ✅   |    ✅    |   ✅    |
symetrie axiale   |   ✅   |    ✅    |   ✅    |
symetrie centrale |   ✅   |    ✅    |   ✅    |
getPoints         |   ✅   |    ✅    |   ✅    |
getCentre         |   ✅   |    ✅    |   ✅    |
getAire           |   ✅   |    ✅    |   ✅    |
getPerimetre      |   ✅   |    ✅    |   ✅    |

## Test

methode/classe    | Cercle | Polygone | Ellipse |
----------------- | ------ | -------- |---------|
translation       |   ✅   |    ✅    |   ✅    |
rotation          |   ✅   |    ✅    |   ✅    |
homothetie        |   ✅   |    ✅    |   ✅    |
symetrie axiale   |   ✅   |    ✅    |   ✅    |
symetrie centrale |   ✅   |    ✅    |   ✅    |
getPoints         |   ✅   |    ✅    |   ✅    |
getCentre         |   ✅   |    ✅    |   ✅    |
getAire           |   ✅   |    ✅    |   ✅    |
getPerimetre      |   ✅   |    ✅    |   ✅    |

## JARs

- https://mvnrepository.com/artifact/org.json/json/20200518
- https://mvnrepository.com/artifact/org.junit.platform/junit-platform-console-standalone/1.7.0
