package game.utils;

import game.Game;
import game.entities.*;

//Classe pour détecter les collision entre deux entités
public class CollisionDetector {
    private Game game;

    public CollisionDetector(Game game) {
        this.game = game;
    }

    //Détection de collision entre des entités de type collisionCheck et une entité obj ; on renvoie l'entité du type testé en cas de collision
    //Les entités de type collisionCheck ont une hitbox rectangulaire, et on considère ici que la hitbox de l'entité obj est un point (pour la collision entre Pacman et les fantôme, ça permet d'avoir une marge et faire en sorte que le jeu ne soit pas trop punitif)
    public Entity checkCollision(Entity obj, Class<? extends Entity> collisionCheck) {
        for (Entity e : game.getEntities()) {
            if (!e.isDestroyed() && collisionCheck.isInstance(e) && e.getHitbox().contains(obj.getxPos() + obj.getSize() / 2, obj.getyPos() + obj.getSize() / 2)) return e;
        }
        return null;
    }

    //Même chose que la méthode précédente, mais toutes les hitboxes sont considérées comme rectangulaires
    public Entity checkCollisionRect(Entity obj, Class<? extends Entity> collisionCheck) {
        for (Entity e : game.getEntities()) {
            if (!e.isDestroyed() && collisionCheck.isInstance(e) && e.getHitbox().intersects(obj.getHitbox())) return e;
        }
        return null;
    }
}
