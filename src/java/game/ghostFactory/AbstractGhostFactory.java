package game.ghostFactory;

import game.entities.ghosts.*;

//Abstract Factory pour créer les différents fantômes concrets à partir de constructeurs différents
public abstract class AbstractGhostFactory {
    public abstract Ghost makeGhost(int xPos, int yPos);
}

