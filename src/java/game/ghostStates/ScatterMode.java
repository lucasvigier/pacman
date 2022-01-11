package game.ghostStates;

import game.entities.ghosts.Ghost;

public class ScatterMode extends GhostState{
    public ScatterMode(Ghost ghost) {
        super(ghost);
    }

    @Override
    public void superPacGumEaten() {
        ghost.switchFrightenedMode();
    }

    @Override
    public void timerModeOver() {
        ghost.switchChaseMode();
    }

    @Override
    public int[] getTargetPosition() {
        return ghost.getStrategy().getScatterTargetPosition();
    }
}
