package game.ghostFactory;

import game.entities.ghosts.Ghost;
import game.entities.ghosts.Pinky;

//Factory concrète pour créer des fantômes Pinky
public class PinkyFactory extends AbstractGhostFactory {
    @Override
    public Ghost makeGhost(int xPos, int yPos) {
        return new Pinky(xPos, yPos);
    }
}
