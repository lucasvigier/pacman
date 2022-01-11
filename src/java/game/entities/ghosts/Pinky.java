package game.entities.ghosts;

import game.ghostStrategies.PinkyStrategy;

public class Pinky extends Ghost {
    public Pinky(int xPos, int yPos) {
        super(xPos, yPos, "pinky.png");
        setStrategy(new PinkyStrategy());
    }
}
