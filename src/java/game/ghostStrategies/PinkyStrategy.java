package game.ghostStrategies;

import game.Game;
import game.utils.Utils;

//Stratégie concrète de Pinky (le fantôme rose)
public class PinkyStrategy implements IGhostStrategy {
    //Pinky cible deux cases devant de Pacman
    @Override
    public int[] getChaseTargetPosition() {
        int[] position = new int[2];
        int[] pacmanFacingPosition = Utils.getPointDistanceDirection(Game.getPacman().getxPos(), Game.getPacman().getyPos(), 64, Utils.directionConverter(Game.getPacman().getDirection()));
        position[0] = pacmanFacingPosition[0];
        position[1] = pacmanFacingPosition[1];
        return position;
    }

    //En pause, Pinky cible la case en haut à gauche
    @Override
    public int[] getScatterTargetPosition() {
        int[] position = new int[2];
        position[0] = 0;
        position[1] = 0;
        return position;
    }
}
