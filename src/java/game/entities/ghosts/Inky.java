package game.entities.ghosts;

import game.Game;
import game.ghostStrategies.InkyStrategy;

//Classe concrète de Inky (le fantôme bleu)
public class Inky extends Ghost {
    public Inky(int xPos, int yPos) {
        super(xPos, yPos, "inky.png");
        setStrategy(new InkyStrategy(Game.getBlinky()));
    }
}
