package game.entities.ghosts;

import game.ghostStrategies.BlinkyStrategy;

public class Blinky extends Ghost {
    public Blinky(int xPos, int yPos) {
        super(xPos, yPos, "blinky.png");
        setStrategy(new BlinkyStrategy());
    }
}
