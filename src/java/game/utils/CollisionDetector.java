package game.utils;

import game.Game;
import game.entities.*;

public class CollisionDetector {
    private Game game;

    public CollisionDetector(Game game) {
        this.game = game;
    }

    public Entity checkCollision(Entity obj, Class<? extends Entity> collisionCheck) {
        for (Entity e : game.getEntities()) {
            if (!e.isDestroyed() && collisionCheck.isInstance(e) && e.getHitbox().contains(obj.getxPos() + obj.getSize() / 2, obj.getyPos() + obj.getSize() / 2)) return e;
        }
        return null;
    }

    public Entity checkCollisionRect(Entity obj, Class<? extends Entity> collisionCheck) {
        for (Entity e : game.getEntities()) {
            if (!e.isDestroyed() && collisionCheck.isInstance(e) && e.getHitbox().intersects(obj.getHitbox())) return e;
        }
        return null;
    }
}
