# school-5-java

## Sujet
https://www.myefrei.fr/moodle/pluginfile.php/164567/mod_resource/content/2/DessineMoiUnMouton.pdf     


## Diagram

https://app.diagrams.net    
https://drive.google.com/file/d/1dVPSjFQF74Z0WybizJRSQcgkDVgsUDgV/view?usp=sharing

![diagram1](https://raw.githubusercontent.com/azerpas/school-5-java/main/diagram.png?token=AETDRFIZTUHY6LSS7IFZUC27RWSOC)

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

methode/classe    | Cercle | Polygone | Ellipse |
----------------- | ------ | -------- |---------|
translation       |   - [X]  |    [x]   |   [x]   |
homothetie        |   [ ]  |    [ ]   |   [ ]   |
symetrie axiale   |   [x]  |    [ ]   |   [ ]   |
symetrie centrale |   [x]  |    [ ]   |   [ ]   |
getPoints         |   [ ]  |    [x]   |   [x]   |
getCentre         |   [x]  |    [x]   |   [x]   |

