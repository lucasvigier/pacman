package game.ghostFactory;

import game.entities.ghosts.Blinky;
import game.entities.ghosts.Ghost;

public class BlinkyFactory extends AbstractGhostFactory {
    @Override
    public Ghost makeGhost(int xPos, int yPos) {
        return new Blinky(xPos, yPos);
    }
}
