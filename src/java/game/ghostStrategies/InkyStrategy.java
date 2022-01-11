package game.ghostStrategies;

import game.Game;
import game.GameplayPanel;
import game.entities.ghosts.Ghost;
import game.utils.Utils;

public class InkyStrategy implements IGhostStrategy{
    private Ghost otherGhost;
    public InkyStrategy(Ghost ghost) {
        this.otherGhost = ghost;
    }

    @Override
    public int[] getChaseTargetPosition() {
        int[] position = new int[2];
        int[] pacmanFacingPosition = Utils.getPointDistanceDirection(Game.getPacman().getxPos(), Game.getPacman().getyPos(), 32d, Utils.directionConverter(Game.getPacman().getDirection()));
        double distanceOtherGhost = Utils.getDistance(pacmanFacingPosition[0], pacmanFacingPosition[1], otherGhost.getxPos(), otherGhost.getyPos());
        double directionOtherGhost = Utils.getDirection(otherGhost.getxPos(), otherGhost.getyPos(), pacmanFacingPosition[0], pacmanFacingPosition[1]);
        int[] blinkyVectorPosition = Utils.getPointDistanceDirection(pacmanFacingPosition[0], pacmanFacingPosition[1], distanceOtherGhost, directionOtherGhost);
        position[0] = blinkyVectorPosition[0];
        position[1] = blinkyVectorPosition[1];
        return position;
    }

    @Override
    public int[] getScatterTargetPosition() {
        int[] position = new int[2];
        position[0] = GameplayPanel.width;
        position[1] = GameplayPanel.height;
        return position;
    }
}
