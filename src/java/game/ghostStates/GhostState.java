package game.ghostStates;

import game.entities.ghosts.Ghost;
import game.utils.Utils;
import game.utils.WallCollisionDetector;

//Classe abstrate pour décrire les différents états de fantômes
public abstract class GhostState {
    protected Ghost ghost;

    public GhostState(Ghost ghost) {
        this.ghost = ghost;
    }

    //Différentes transitions possibles d'un état vers un autre
    public void superPacGumEaten() {}
    public void timerModeOver() {}
    public void timerFrightenedModeOver() {}
    public void eaten() {}
    public void outsideHouse() {}
    public void insideHouse() {}

    public int[] getTargetPosition(){
        return new int[2];
    } //retourne le point que va cibler le fantôme

    //Méthode pour calculer la prochaine direction que le fantôme va prendre
    public void computeNextDir() {
        int new_xSpd = 0;
        int new_ySpd = 0;

        if (!ghost.onTheGrid()) return; //Le fantôme doit être sur une "case" de la zone de jeu
        if (!ghost.onGameplayWindow()) return;  //Le fantôme doit être dans la zone de jeu

        double minDist = Double.MAX_VALUE; //distance minimale courante entre le fantôme et la cible selon sa prochaine direction

        //Si le fantôme va actuellement vers la gauche et qu'il n'y a pas de mur à gauche...
        if (ghost.getxSpd() <= 0 && !WallCollisionDetector.checkWallCollision(ghost, -ghost.getSpd(), 0)) {
            //On regarde la distance entre la position ciblée et la position potentielle du fantôme si ce dernier irait vers la gauche
            double distance = Utils.getDistance(ghost.getxPos() - ghost.getSpd(), ghost.getyPos(), getTargetPosition()[0], getTargetPosition()[1]);

            //Si cette distance est inférieure à la distance minimale courante, on dit que le fantôme va vers la gauche et on met à jour la distance minimale
            if (distance < minDist) {
                new_xSpd = -ghost.getSpd();
                new_ySpd = 0;
                minDist = distance;
            }
        }

        //Même chose en testant vers la droite
        if (ghost.getxSpd() >= 0 && !WallCollisionDetector.checkWallCollision(ghost, ghost.getSpd(), 0)) {
            double distance = Utils.getDistance(ghost.getxPos() + ghost.getSpd(), ghost.getyPos(),  getTargetPosition()[0], getTargetPosition()[1]);
            if (distance < minDist) {
                new_xSpd = ghost.getSpd();
                new_ySpd = 0;
                minDist = distance;
            }
        }

        //Même chose en testant vers le haut
        if (ghost.getySpd() <= 0 && !WallCollisionDetector.checkWallCollision(ghost, 0, -ghost.getSpd())) {
            double distance = Utils.getDistance(ghost.getxPos(), ghost.getyPos() - ghost.getSpd(), getTargetPosition()[0], getTargetPosition()[1]);
            if (distance < minDist) {
                new_xSpd = 0;
                new_ySpd = -ghost.getSpd();
                minDist = distance;
            }
        }

        //Même chose en testant vers le bas
        if (ghost.getySpd() >= 0 && !WallCollisionDetector.checkWallCollision(ghost, 0, ghost.getSpd())) {
            double distance = Utils.getDistance(ghost.getxPos(), ghost.getyPos() + ghost.getSpd(), getTargetPosition()[0], getTargetPosition()[1]);
            if (distance < minDist) {
                new_xSpd = 0;
                new_ySpd = ghost.getSpd();
                minDist = distance;
            }
        }

        if (new_xSpd == 0 && new_ySpd == 0) return;

        //Une fois tous les cas testés, on change la direction du fantôme (au cas où, comme cette direction est définie par une vitesse horizontale et une vitesse verticale, on fait quand même une vérification afin qu'il ne puisse pas aller en diagonale)
        if (Math.abs(new_xSpd) != Math.abs(new_ySpd)) {
            ghost.setxSpd(new_xSpd);
            ghost.setySpd(new_ySpd);
        } else {
            if (new_xSpd != 0) {
                ghost.setxSpd(0);
                ghost.setxSpd(new_ySpd);
            }else{
                ghost.setxSpd(new_xSpd);
                ghost.setySpd(0);
            }
        }
    }
}
