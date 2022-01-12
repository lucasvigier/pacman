package game.ghostFactory;

import game.entities.ghosts.Clyde;
import game.entities.ghosts.Ghost;

//Factory concrète pour créer des fantômes Clyde
public class ClydeFactory extends AbstractGhostFactory {
    @Override
    public Ghost makeGhost(int xPos, int yPos) {
        return new Clyde(xPos, yPos);
    }
}
