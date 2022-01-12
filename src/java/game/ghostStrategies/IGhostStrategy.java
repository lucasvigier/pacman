package game.ghostStrategies;

//Interface pour décrire les stratégies des différents fantômes (cette vidéo les explique bien : https://www.youtube.com/watch?v=ataGotQ7ir8)
public interface IGhostStrategy {
    int[] getChaseTargetPosition(); //Case ciblée lorsque le fantôme poursuit Pacman
    int[] getScatterTargetPosition(); //Case ciblée lorsque le fantôme fait une pause
}
