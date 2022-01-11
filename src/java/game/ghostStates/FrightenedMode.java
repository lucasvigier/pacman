package game.ghostStates;

import game.entities.ghosts.Ghost;
import game.utils.Utils;
import jdk.jshell.execution.Util;

public class FrightenedMode extends GhostState{
    public FrightenedMode(Ghost ghost) {
        super(ghost);
    }

    @Override
    public void eaten() {
        ghost.switchEatenMode();
    }

    @Override
    public void timerFrightenedModeOver() {
        ghost.switchChaseModeOrScatterMode();
    }

    @Override
    public int[] getTargetPosition(){
        int[] position = new int[2];

        boolean randomAxis = Utils.randomBool();
        position[0] = ghost.getxPos() + (randomAxis ? Utils.randomInt(-1,1) * 32 : 0);
        position[1] = ghost.getyPos() + (!randomAxis ? Utils.randomInt(-1,1) * 32 : 0);
        return position;
    }
}
