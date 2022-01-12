package game.entities;

import game.GameplayPanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

//Classe abtraite pour décrire une entité mouvante
public abstract class MovingEntity extends Entity {
    protected int spd;
    protected int xSpd = 0;
    protected int ySpd = 0;
    protected BufferedImage sprite;
    protected float subimage = 0;
    protected int nbSubimagesPerCycle;
    protected int direction = 0;
    protected float imageSpd = 0.2f;

    public MovingEntity(int size, int xPos, int yPos, int spd, String spriteName, int nbSubimagesPerCycle, float imageSpd) {
        super(size, xPos, yPos);
        this.spd = spd;
        try {
            this.sprite = ImageIO.read(getClass().getClassLoader().getResource("img/" + spriteName));
            this.nbSubimagesPerCycle = nbSubimagesPerCycle;
            this.imageSpd = imageSpd;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        updatePosition();
    }

    public void updatePosition() {
        //Mise à jour de la position de l'entité
        if (!(xSpd == 0 && ySpd == 0)) { //Si la vitesse horizontale ou la vitesse verticale n'est pas nulle, on incrémente la position horizontale et verticale en conséquence
            xPos+=xSpd;
            yPos+=ySpd;

            //En fonction de la direction emprunté, on change la valeur de la direction (un entier permettant de savoir la partie de l'image à afficher notamment)
            if (xSpd > 0) {
                direction = 0;
            } else if (xSpd < 0) {
                direction = 1;
            } else if (ySpd < 0) {
                direction = 2;
            } else if (ySpd > 0) {
                direction = 3;
            }

            //On incrémente la valeur de l'image courante de l'animation à afficher (la vitesse peut varier), et selon le nombre d'images de l'animation, la valeur fait une boucle
            subimage += imageSpd;
            if (subimage >= nbSubimagesPerCycle) {
                subimage = 0;
            }
        }

        //Si l'entité va au dela des bords de la zone de jeu, elle passe de l'autre côté
        if (xPos > GameplayPanel.width) {
            xPos = 0 - size + spd;
        }

        if (xPos < 0 - size + spd) {
            xPos = GameplayPanel.width;
        }

        if (yPos > GameplayPanel.height) {
            yPos = 0 - size + spd;
        }

        if (yPos < 0 - size + spd) {
            yPos = GameplayPanel.height;
        }
    }

    @Override
    public void render(Graphics2D g) {
        //Par défaut, on considère que chaque "sprite" contient 4 variations de l'animation correspondant à une direction et chaque animation a un certain nombre d'images
        //En sachant cela, on affiche seulement la partie de l'image du sprite correspondant à la bonne direction et à la bonne frame de l'animation
        g.drawImage(sprite.getSubimage((int)subimage * size + direction * size * nbSubimagesPerCycle, 0, size, size), this.xPos, this.yPos,null);
    }

    //Méthode pour savoir si l'entité est bien positionnée sur une case de la grille de la zone de jeu ou non
    public boolean onTheGrid() {
        return (xPos%8 == 0 && yPos%8 == 0);
    }

    //Méthode pour savoir si l'entité est dans la zone de jeu ou non
    public boolean onGameplayWindow() { return !(xPos<=0 || xPos>= GameplayPanel.width || yPos<=0 || yPos>= GameplayPanel.height); }

    public Rectangle getHitbox() {
        return new Rectangle(xPos, yPos, size, size);
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }

    public void setSprite(String spriteName) {
        try {
            this.sprite = ImageIO.read(getClass().getClassLoader().getResource("img/" + spriteName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public float getSubimage() {
        return subimage;
    }

    public void setSubimage(float subimage) {
        this.subimage = subimage;
    }

    public int getNbSubimagesPerCycle() {
        return nbSubimagesPerCycle;
    }

    public void setNbSubimagesPerCycle(int nbSubimagesPerCycle) {
        this.nbSubimagesPerCycle = nbSubimagesPerCycle;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getxSpd() {
        return xSpd;
    }

    public void setxSpd(int xSpd) {
        this.xSpd = xSpd;
    }

    public int getySpd() {
        return ySpd;
    }

    public void setySpd(int ySpd) {
        this.ySpd = ySpd;
    }

    public int getSpd() {
        return spd;
    }
}
