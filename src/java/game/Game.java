package game;

import game.entities.*;
import game.entities.ghosts.Blinky;
import game.entities.ghosts.Ghost;
import game.ghostFactory.*;
import game.ghostStates.EatenMode;
import game.ghostStates.FrightenedMode;
import game.utils.CollisionDetector;
import game.utils.CsvReader;
import game.utils.KeyHandler;

import java.awt.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

//Classe gérant le jeu en lui même
public class Game implements Observer {
    //Pour lister les différentes entités présentes sur la fenêtre
    private List<Entity> objects = new ArrayList();
    private List<Ghost> ghosts = new ArrayList();
    private static List<Wall> walls = new ArrayList();

    private static Pacman pacman;
    private static Blinky blinky;

    private static boolean firstInput = false;

    public Game(){
        //Initialisation du jeu

        //Chargement du fichier csv du niveau
        List<List<String>> data = null;
        try {
            data = new CsvReader().parseCsv(getClass().getClassLoader().getResource("level/level.csv").toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        int cellsPerRow = data.get(0).size();
        int cellsPerColumn = data.size();
        int cellSize = 8;

        CollisionDetector collisionDetector = new CollisionDetector(this);
        AbstractGhostFactory abstractGhostFactory = null;

        //Le niveau a une "grille", et pour chaque case du fichier csv, on affiche une entité parculière sur une case de la grille selon le caracère présent
        for(int xx = 0 ; xx < cellsPerRow ; xx++) {
            for(int yy = 0 ; yy < cellsPerColumn ; yy++) {
                String dataChar = data.get(yy).get(xx);
                if (dataChar.equals("x")) { //Création des murs
                    objects.add(new Wall(xx * cellSize, yy * cellSize));
                }else if (dataChar.equals("P")) { //Création de Pacman
                    pacman = new Pacman(xx * cellSize, yy * cellSize);
                    pacman.setCollisionDetector(collisionDetector);

                    //Enregistrement des différents observers de Pacman
                    pacman.registerObserver(GameLauncher.getUIPanel());
                    pacman.registerObserver(this);
                }else if (dataChar.equals("b") || dataChar.equals("p") || dataChar.equals("i") || dataChar.equals("c")) { //Création des fantômes en utilisant les différentes factories
                    switch (dataChar) {
                        case "b":
                            abstractGhostFactory = new BlinkyFactory();
                            break;
                        case "p":
                            abstractGhostFactory = new PinkyFactory();
                            break;
                        case "i":
                            abstractGhostFactory = new InkyFactory();
                            break;
                        case "c":
                            abstractGhostFactory = new ClydeFactory();
                            break;
                    }

                    Ghost ghost = abstractGhostFactory.makeGhost(xx * cellSize, yy * cellSize);
                    ghosts.add(ghost);
                    if (dataChar.equals("b")) {
                        blinky = (Blinky) ghost;
                    }
                }else if (dataChar.equals(".")) { //Création des PacGums
                    objects.add(new PacGum(xx * cellSize, yy * cellSize));
                }else if (dataChar.equals("o")) { //Création des SuperPacGums
                    objects.add(new SuperPacGum(xx * cellSize, yy * cellSize));
                }else if (dataChar.equals("-")) { //Création des murs de la maison des fantômes
                    objects.add(new GhostHouse(xx * cellSize, yy * cellSize));
                }
            }
        }
        objects.add(pacman);
        objects.addAll(ghosts);

        for (Entity o : objects) {
            if (o instanceof Wall) {
                walls.add((Wall) o);
            }
        }
    }

    public static List<Wall> getWalls() {
        return walls;
    }

    public List<Entity> getEntities() {
        return objects;
    }

    //Mise à jour de toutes les entités
    public void update() {
        for (Entity o: objects) {
            if (!o.isDestroyed()) o.update();
        }
    }

    //Gestion des inputs
    public void input(KeyHandler k) {
        pacman.input(k);
    }

    //Rendu de toutes les entités
    public void render(Graphics2D g) {
        for (Entity o: objects) {
            if (!o.isDestroyed()) o.render(g);
        }
    }

    public static Pacman getPacman() {
        return pacman;
    }
    public static Blinky getBlinky() {
        return blinky;
    }

    //Le jeu est notifiée lorsque Pacman est en contact avec une PacGum, une SuperPacGum ou un fantôme
    @Override
    public void updatePacGumEaten(PacGum pg) {
        pg.destroy(); //La PacGum est détruite quand Pacman la mange
    }

    @Override
    public void updateSuperPacGumEaten(SuperPacGum spg) {
        spg.destroy(); //La SuperPacGum est détruite quand Pacman la mange
        for (Ghost gh : ghosts) {
            gh.getState().superPacGumEaten(); //S'il existe une transition particulière quand une SuperPacGum est mangée, l'état des fantômes change
        }
    }

    @Override
    public void updateGhostCollision(Ghost gh) {
        if (gh.getState() instanceof FrightenedMode) {
            gh.getState().eaten(); //S'il existe une transition particulière quand le fantôme est mangé, son état change en conséquence
        }else if (!(gh.getState() instanceof EatenMode)) {
            System.out.println("Game over !\nScore : " + GameLauncher.getUIPanel().getScore()); //Quand Pacman rentre en contact avec un Fantôme qui n'est ni effrayé, ni mangé, c'est game over !
            System.exit(0); //TODO
        }
    }

    public static void setFirstInput(boolean b) {
        firstInput = b;
    }

    public static boolean getFirstInput() {
        return firstInput;
    }
}
