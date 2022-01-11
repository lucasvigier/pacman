package game.ghostStates;

import game.entities.ghosts.Ghost;

public class ChaseMode extends GhostState{
    public ChaseMode(Ghost ghost) {
        super(ghost);
    }

    @Override
    public void superPacGumEaten() {
        ghost.switchFrightenedMode();
    }

    @Override
    public void timerModeOver() {
        ghost.switchScatterMode();
    }

    @Override
    public int[] getTargetPosition() {
        return ghost.getStrategy().getChaseTargetPosition();
    }
}
