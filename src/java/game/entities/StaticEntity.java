package game.entities;

import java.awt.*;

public abstract class StaticEntity extends Entity {

    protected Rectangle hitbox;

    public StaticEntity(int size, int xPos, int yPos) {
        super(size, xPos, yPos);
        this.hitbox = new Rectangle(xPos, yPos, size, size);
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
}
