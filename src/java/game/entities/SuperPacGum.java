package game.entities;

import java.awt.*;

//Classe pour les SuperPacGums
public class SuperPacGum extends StaticEntity {
    private int frameCount = 0;

    public SuperPacGum(int xPos, int yPos) {
        super(16, xPos, yPos);
    }

    @Override
    public void render(Graphics2D g) {
        //Pour faire en sorte que les SuperPacGums clignotent, on ne fait le rendu que 30 frames sur 60.
        if (frameCount%60 < 30) {
            g.setColor(new Color(255, 183, 174));
            g.fillOval(this.xPos, this.yPos, this.size, this.size);
        }
    }

    @Override
    public void update() {
        frameCount++;
    }
}
