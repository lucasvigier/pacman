package game.ghostStrategies;

import game.Game;
import game.GameplayPanel;
import game.utils.Utils;

public class BlinkyStrategy implements IGhostStrategy{
    @Override
    public int[] getChaseTargetPosition() {
        int[] position = new int[2];
        position[0] = Game.getPacman().getxPos();
        position[1] = Game.getPacman().getyPos();
        return position;
    }

    @Override
    public int[] getScatterTargetPosition() {
        int[] position = new int[2];
        position[0] = GameplayPanel.width;
        position[1] = 0;
        return position;
    }
}
