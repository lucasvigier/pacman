package game;

import game.entities.PacGum;
import game.entities.SuperPacGum;
import game.entities.ghosts.Ghost;

//Interface de l'observer
public interface Observer {
    void updatePacGumEaten(PacGum pg);
    void updateSuperPacGumEaten(SuperPacGum spg);
    void updateGhostCollision(Ghost gh);
}
