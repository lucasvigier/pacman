package game.entities.ghosts;

import game.Game;
import game.entities.MovingEntity;
import game.ghostStates.*;
import game.ghostStrategies.IGhostStrategy;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

//Classe abtraite pour décrire les fantômes
public abstract class Ghost extends MovingEntity {
    protected GhostState state;

    protected final GhostState chaseMode;
    protected final GhostState scatterMode;
    protected final GhostState frightenedMode;
    protected final GhostState eatenMode;
    protected final GhostState houseMode;

    protected int modeTimer = 0;
    protected int frightenedTimer = 0;
    protected boolean isChasing = false;

    protected static BufferedImage frightenedSprite1;
    protected static BufferedImage frightenedSprite2;
    protected static BufferedImage eatenSprite;

    protected IGhostStrategy strategy;

    public Ghost(int xPos, int yPos, String spriteName) {
        super(32, xPos, yPos, 2, spriteName, 2, 0.1f);

        //Création des différents états des fantômes
        chaseMode = new ChaseMode(this);
        scatterMode = new ScatterMode(this);
        frightenedMode = new FrightenedMode(this);
        eatenMode = new EatenMode(this);
        houseMode = new HouseMode(this);

        state = houseMode; //état initial

        try {
            frightenedSprite1 = ImageIO.read(getClass().getClassLoader().getResource("img/ghost_frightened.png"));
            frightenedSprite2 = ImageIO.read(getClass().getClassLoader().getResource("img/ghost_frightened_2.png"));
            eatenSprite = ImageIO.read(getClass().getClassLoader().getResource("img/ghost_eaten.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Méthodes pour les transitions entre les différents états
    public void switchChaseMode() {
        state = chaseMode;
    }
    public void switchScatterMode() {
        state = scatterMode;
    }

    public void switchFrightenedMode() {
        frightenedTimer = 0;
        state = frightenedMode;
    }

    public void switchEatenMode() {
        state = eatenMode;
    }

    public void switchHouseMode() {
        state = houseMode;
    }

    public void switchChaseModeOrScatterMode() {
        if (isChasing) {
            switchChaseMode();
        }else{
            switchScatterMode();
        }
    }

    public IGhostStrategy getStrategy() {
        return this.strategy;
    }

    public void setStrategy(IGhostStrategy strategy) {
        this.strategy = strategy;
    }

    public GhostState getState() {
        return state;
    }

    @Override
    public void update() {
        if (!Game.getFirstInput()) return; //Les fantômes ne bougent pas tant que le joueur n'a pas bougé

        //Si le fantôme est dans l'état effrayé, un timer de 7s se lance, et l'état sera notifié ensuite afin d'appliquer la transition adéquate
        if (state == frightenedMode) {
            frightenedTimer++;

            if (frightenedTimer >= (60 * 7)) {
                state.timerFrightenedModeOver();
            }
        }

        //Les fantômes alternent entre l'état chaseMode et scatterMode avec un timer
        //Si le fantôme est dans l'état chaseMode ou scatterMode, un timer se lance, et au bout de 5s ou 20s selon l'état, l'état est notifié ensuite afin d'appliquer la transition adéquate
        if (state == chaseMode || state == scatterMode) {
            modeTimer++;

            if ((isChasing && modeTimer >= (60 * 20)) || (!isChasing && modeTimer >= (60 * 5))) {
                state.timerModeOver();
                isChasing = !isChasing;
            }
        }

        //Si le fantôme est sur la case juste au dessus de sa maison, l'état est notifié afin d'appliquer la transition adéquate
        if (xPos == 208 && yPos == 168) {
            state.outsideHouse();
        }

        //Si le fantôme est sur la case au milieu sa maison, l'état est notifié afin d'appliquer la transition adéquate
        if (xPos == 208 && yPos == 200) {
            state.insideHouse();
        }

        //Selon l'état, le fantôme calcule sa prochaine direction, et sa position est ensuite mise à jour
        state.computeNextDir();
        updatePosition();
    }

    @Override
    public void render(Graphics2D g) {
        //Différents sprites sont utilisés selon l'état du fantôme (après réflexion, il aurait peut être été plus judicieux de faire une méthode "render" dans GhostState)
        if (state == frightenedMode) {
            if (frightenedTimer <= (60 * 5) || frightenedTimer%20 > 10) {
                g.drawImage(frightenedSprite1.getSubimage((int)subimage * size, 0, size, size), this.xPos, this.yPos,null);
            }else{
                g.drawImage(frightenedSprite2.getSubimage((int)subimage * size, 0, size, size), this.xPos, this.yPos,null);
            }
        }else if (state == eatenMode) {
            g.drawImage(eatenSprite.getSubimage(direction * size, 0, size, size), this.xPos, this.yPos,null);
        }else{
            g.drawImage(sprite.getSubimage((int)subimage * size + direction * size * nbSubimagesPerCycle, 0, size, size), this.xPos, this.yPos,null);
        }

    }
}
