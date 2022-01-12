package game.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

//Classe regroupant différentes fonctions utiles
public class Utils {
    private static Map<Integer, Double> directionConverterMap = new HashMap<>();

    static {
        directionConverterMap.put(0, 0d);
        directionConverterMap.put(1, Math.PI);
        directionConverterMap.put(2, Math.PI / 2);
        directionConverterMap.put(3, Math.PI * (3/2));
    }

    //Fonction pour obtenir la distance entre deux points
    public static double getDistance(double xA, double yA, double xB, double yB) {
        return Math.sqrt( Math.pow(xB - xA, 2) + Math.pow(yB - yA, 2) );
    }

    //Fonction pour obtenir l'angle formé entre deux points
    public static double getDirection(double xA, double yA, double xB, double yB) {
        return Math.atan2((yB - yA), (xB - xA));
    }

    //Fonction pour obtenir le point à partir d'un premier point, d'un angle et d'une distance
    public static int[] getPointDistanceDirection(int x, int y, double distance, double direction) {
        int[] point = new int[2];
        point[0] = x + (int)(Math.cos(direction) * distance);
        point[1] = y + (int)(Math.sin(direction) * distance);
        return point;
    }

    //Fonction pour convertir une "direction" d'une entité en un angle en radians grâce à la map créée plus haut
    public static double directionConverter(int spriteDirection) {
        return directionConverterMap.get(spriteDirection);
    }

    //Fonction pour générer un entier entre 0 et n
    public static int randomInt(int n) {
        Random r = new Random();
        return r.nextInt(n);
    }

    //Fonction pour générer un entier entre x et y inclus
    public static int randomInt(int min, int max) {
        Random r = new Random();
        return r.nextInt(max-min) + min;
    }

    //Fonction pour générer un booléen aléatoire
    public static boolean randomBool() {
        return (randomInt(1) == 1);
    }
}
