package game.entities;

import java.awt.*;

//Classe abstraite pour décrire un entité qui ne bouge pas
public abstract class StaticEntity extends Entity {

    protected Rectangle hitbox;

    public StaticEntity(int size, int xPos, int yPos) {
        super(size, xPos, yPos);
        this.hitbox = new Rectangle(xPos, yPos, size, size); //La hitbox n'est définie qu'une fois lors de la création de l'enttité
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
}
