package game;

import javax.swing.*;
import java.io.IOException;

//Point d'entrée de l'application
public class GameLauncher {
    private static UIPanel uiPanel;

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setTitle("Pacman");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel gameWindow = new JPanel();

        //Création de la "zone de jeu"
        try {
            gameWindow.add(new GameplayPanel(448,496));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Création de l'UI (pour afficher le score)
        uiPanel = new UIPanel(256,496);
        gameWindow.add(uiPanel);

        window.setContentPane(gameWindow);
        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public static UIPanel getUIPanel() {
        return uiPanel;
    }
}
