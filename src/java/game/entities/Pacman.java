package game.entities;

import game.Game;
import game.Observer;
import game.Sujet;
import game.entities.ghosts.Ghost;
import game.utils.CollisionDetector;
import game.utils.KeyHandler;
import game.utils.WallCollisionDetector;

import java.util.ArrayList;
import java.util.List;

//Classe pour décrire Pacman
public class Pacman extends MovingEntity implements Sujet {
    private CollisionDetector collisionDetector;
    private List<Observer> observerCollection;

    public Pacman(int xPos, int yPos) {
        super(32, xPos, yPos, 2, "pacman.png", 4, 0.3f);
        observerCollection = new ArrayList<>();
    }

    //Gestion des déplacements
    public void input(KeyHandler k) {
        int new_xSpd = 0;
        int new_ySpd = 0;

        if (!onTheGrid()) return; //Pacman doit être sur une "case" de la zone de jeu
        if (!onGameplayWindow()) return; //Pacman doit être dans la zone de jeu

        //Selon les touches appuyées, la direction de pacman change en conséquence
        if (k.k_left.isPressed && xSpd >= 0 && !WallCollisionDetector.checkWallCollision(this, -spd, 0)) {
            new_xSpd = -spd;
        }
        if (k.k_right.isPressed && xSpd <= 0 && !WallCollisionDetector.checkWallCollision(this, spd, 0)) {
            new_xSpd = spd;
        }
        if (k.k_up.isPressed && ySpd >= 0 && !WallCollisionDetector.checkWallCollision(this, 0, -spd)) {
            new_ySpd = -spd;
        }
        if (k.k_down.isPressed && ySpd <= 0 && !WallCollisionDetector.checkWallCollision(this, 0, spd)) {
            new_ySpd = spd;
        }

        if (new_xSpd == 0 && new_ySpd == 0) return;

        if (!Game.getFirstInput()) Game.setFirstInput(true);

        if (Math.abs(new_xSpd) != Math.abs(new_ySpd)) {
            xSpd = new_xSpd;
            ySpd = new_ySpd;
        } else {
            if (xSpd != 0) {
                xSpd = 0;
                ySpd = new_ySpd;
            }else{
                xSpd = new_xSpd;
                ySpd = 0;
            }
        }
    }

    @Override
    public void update() {
        //On teste à chaque fois si Pacman est en contact avec une PacGum, une SuperPacGum, ou un fantôme, et les observers sont notifiés en conséquence
        PacGum pg = (PacGum) collisionDetector.checkCollision(this, PacGum.class);
        if (pg != null) {
            notifyObserverPacGumEaten(pg);
        }

        SuperPacGum spg = (SuperPacGum) collisionDetector.checkCollision(this, SuperPacGum.class);
        if (spg != null) {
            notifyObserverSuperPacGumEaten(spg);
        }

        Ghost gh = (Ghost) collisionDetector.checkCollision(this, Ghost.class);
        if (gh != null) {
            notifyObserverGhostCollision(gh);
        }

        //S'il n'y a pas de mur à la prochaine position potentielle de Pacman, on met à jour sa position
        if (!WallCollisionDetector.checkWallCollision(this, xSpd, ySpd)) {
            updatePosition();
        }
    }

    public void setCollisionDetector(CollisionDetector collisionDetector) {
        this.collisionDetector = collisionDetector;
    }

    @Override
    public void registerObserver(Observer observer) {
        observerCollection.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observerCollection.remove(observer);
    }

    @Override
    public void notifyObserverPacGumEaten(PacGum pg) {
        observerCollection.forEach(obs -> obs.updatePacGumEaten(pg));
    }

    @Override
    public void notifyObserverSuperPacGumEaten(SuperPacGum spg) {
        observerCollection.forEach(obs -> obs.updateSuperPacGumEaten(spg));
    }

    @Override
    public void notifyObserverGhostCollision(Ghost gh) {
        observerCollection.forEach(obs -> obs.updateGhostCollision(gh));
    }
}
