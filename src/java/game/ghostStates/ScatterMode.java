package game.ghostStates;

import game.entities.ghosts.Ghost;

//Classe pour décrire l'état concret d'un fantôme en train de faire une pause
public class ScatterMode extends GhostState{
    public ScatterMode(Ghost ghost) {
        super(ghost);
    }

    //Transition lorsqu'une SuperPacGum est mangée
    @Override
    public void superPacGumEaten() {
        ghost.switchFrightenedMode();
    }

    //Transition lorsque le timer de l'état courant est terminé (il alterne entre ChaseMode et ScatterMode)
    @Override
    public void timerModeOver() {
        ghost.switchChaseMode();
    }

    //Dans cet état, la position ciblée dépend de la stratégie du fantôme
    @Override
    public int[] getTargetPosition() {
        return ghost.getStrategy().getScatterTargetPosition();
    }
}
