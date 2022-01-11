package game.utils;

import game.Game;
import game.entities.Entity;
import game.entities.GhostHouse;
import game.entities.Wall;

import java.awt.*;

public class WallCollisionDetector {

    public static boolean checkWallCollision(Entity obj, int dx, int dy) {
        Rectangle r = new Rectangle(obj.getxPos() + dx, obj.getyPos() + dy, obj.getSize(), obj.getSize());
        for (Wall w : Game.getWalls()) {
            if (w.getHitbox().intersects(r)) return true;
        }
        return false;
    }

    public static boolean checkWallCollision(Entity obj, int dx, int dy, boolean ignoreGhostHouses) {
        Rectangle r = new Rectangle(obj.getxPos() + dx, obj.getyPos() + dy, obj.getSize(), obj.getSize());
        for (Wall w : Game.getWalls()) {
            if (!(ignoreGhostHouses && w instanceof GhostHouse) && w.getHitbox().intersects(r)) return true;
        }
        return false;
    }
}
