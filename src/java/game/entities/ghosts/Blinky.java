package game.entities.ghosts;

import game.ghostStrategies.BlinkyStrategy;

//Classe concrète de Blinky (le fantôme rouge)
public class Blinky extends Ghost {
    public Blinky(int xPos, int yPos) {
        super(xPos, yPos, "blinky.png");
        setStrategy(new BlinkyStrategy());
    }
}
