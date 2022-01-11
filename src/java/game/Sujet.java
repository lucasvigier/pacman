package game;

import game.entities.PacGum;
import game.entities.SuperPacGum;
import game.entities.ghosts.Ghost;

//Interface du sujet
public interface Sujet {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObserverPacGumEaten(PacGum pg);
    void notifyObserverSuperPacGumEaten(SuperPacGum spg);
    void notifyObserverGhostCollision(Ghost gh);
}
