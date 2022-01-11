package game.ghostFactory;

import game.entities.ghosts.*;

public abstract class AbstractGhostFactory {
    public abstract Ghost makeGhost(int xPos, int yPos);
}

