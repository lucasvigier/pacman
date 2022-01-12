package game.ghostStrategies;

import game.Game;
import game.GameplayPanel;

//Stratégie concrète de Blinky (le fantôme rouge)
public class BlinkyStrategy implements IGhostStrategy{
    //Blinky cible directement la position de Pacman
    @Override
    public int[] getChaseTargetPosition() {
        int[] position = new int[2];
        position[0] = Game.getPacman().getxPos();
        position[1] = Game.getPacman().getyPos();
        return position;
    }

    //En pause, Blinky cible la case en haut à droite
    @Override
    public int[] getScatterTargetPosition() {
        int[] position = new int[2];
        position[0] = GameplayPanel.width;
        position[1] = 0;
        return position;
    }
}
