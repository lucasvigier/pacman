package game.utils;

import game.Game;
import game.entities.Entity;
import game.entities.GhostHouse;
import game.entities.Wall;

import java.awt.*;

//Classe pour détecter les collision entre une entité et un mur (par rapport à la classe CollisionDetector, les murs sont statiques)
public class WallCollisionDetector {

    //Fonction pour s'avoir s'il y a un mur à la position d'une entité + un certain delta (ce delta permet de détecter le mur avant de rentrer dedans)
    public static boolean checkWallCollision(Entity obj, int dx, int dy) {
        Rectangle r = new Rectangle(obj.getxPos() + dx, obj.getyPos() + dy, obj.getSize(), obj.getSize());
        for (Wall w : Game.getWalls()) {
            if (w.getHitbox().intersects(r)) return true;
        }
        return false;
    }

    //Même chose que la méthode précédente, mais on peut ignorer ici les collisions avec les murs de la maison des fantômes
    public static boolean checkWallCollision(Entity obj, int dx, int dy, boolean ignoreGhostHouses) {
        Rectangle r = new Rectangle(obj.getxPos() + dx, obj.getyPos() + dy, obj.getSize(), obj.getSize());
        for (Wall w : Game.getWalls()) {
            if (!(ignoreGhostHouses && w instanceof GhostHouse) && w.getHitbox().intersects(r)) return true;
        }
        return false;
    }
}
