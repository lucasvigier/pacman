# Justification des designs patterns choisis
## Abstract Factory
Il y a 4 fantômes différents, chacun ayant un sprite (image) et un comportement différent.
 * **Blinky** le fantôme rouge
 * **Pinky** le fantôme rose
 * **Inky** le fantôme bleu
 * et **Clyde** le fantôme jaune 
 
Les 4 fantômes "concrets" implementent une même classe abstraite "Ghost". Pour les instancier, le pattern Abstract Factory est utilisé. Différentes "Concrete Factories" appellent les différents constructeurs des fantômes concrets.

## State
Les fantômes ont chacun différents états, et ils doivent pouvoir passer d'un état à un autre selon certaines conditions (cf. diagramme GhostState.png). Selon leur état, la case ciblée ne sera pas la même, ou bien leur sprite sera différent par exemple.
Le pattern State est donc idéal dans ce cas. en respectant le principe de responsabilité unique, les différents états sont gérés dans des classes dédiées qui étendent une même classe "GhostState" abtraite.
 * **HouseMode** : État des fantômes lorsqu'ils sont dans leur maison au début. Ils vont chercher à en sortir.
 * **ChaseMode** : Dans cet état, les fantômes essayent de poursuivre Pacman, chaque fantôme ayant sa propre stratégie pour se faire (cf. pattern Strategy). Avec un timer, les fantômes alternent entre l'état ChaseMode et l'état ScatterMode.
 * **ScatterMode** : Dans cet état, les fantômes arrêtent de poursuivre Pacman, et ciblent un coin à la place. Chaque fantôme cible un coin qui lui est propre (cf. pattern Strategy).  Avec un timer, les fantômes alternent entre l'état ChaseMode et l'état ScatterMode.
 * **FrightenedMode** : État des fantômes après que Pacman ait mangé une SuperPacGum. Dans cette état, la direction choisie par les fantôme est aléatoire, et Pacman est capable de les "manger". Au bout d'un certain temps, les fantômes retournent dans l'état ChaseMode ou ScatterMode.
 * **EatenMode** : État des fantômes une fois "mangés" par Pacman. Ils cherchent alors à retourner dans leur maison. Pacman peut les toucher sans danger dans ce cas.

 ## Strategy
 Chacun des 4 fantômes a sa propre stratégie (la case ciblée n'est pas la même) pour poursuivre Pacman en mode "ChaseMode", et a son propre coin en mode "ScatterMode". On utilise donc le pattern Srategy pour décrire ces variantes de comportements, dans différentes classes étendant  une même classe abstraite "GhostStrategy".
 * **BlinkyStrategy** : Dans l'état ChaseMode, Blinky cible directement la position de Pacman, et dans l'état ScatterMode Blinky cible le coin en haut à droite.
 * **PinkyStrategy** : Dans l'état ChaseMode, Pinky cible la position 2 cases devant Pacman, et dans l'état ScatterMode Pinky cible le coin en haut à gauche. 
 * **InkyStrategy** : Pour faire simple, dans l'état ChaseMode, Inky cicle une case basée sur les position de Blinky et de Pacman pour essayer d'encercler ce dernier. Dans l'état ScatterMode Inky cible le coin en bas à droite. 
 * **ClydeStrategy** : Dans l'état ChaseMode, Clyde cible directement la position de Pacman s'il est dans un rayon de plus de 8 cases de ce dernier, et sinon il cible la même case que son état ScatterMode. Dans l'état ScatterMode Clyde cible le coin en bas à gauche. 
 
 ## Observer
 Lorsque Pacman entre en contact avec une PacGum, une super PacGum, ou un fantôme, d'autres classes doivent être notifiées afin de déclencher certaines chose, ceci est géré grâce au pattern Observer :
 * Lorsque Pacman mange une PacGum, le score affiché géré par la classe "UIPanel" augmente de 10, et la classe "Game" gérant toutes les entités doit considérer cette PacGum comme détruite.
 * Lorsque Pacman mange une SuperPacGum, le score affiché géré par la classe "UIPanel" augmente de 100, et la classe "Game" gérant toutes les entités doit considérer cette SuperPacGum comme détruite  et faire passer les fantômes dans l'état "FrightenedMode".
 * Lorsque Pacman entre en contact avec un fantôme, si ce dernier n'est ni dans l'état "FrightenedMode", ni "EatenMode", la classe Game indique que c'est game over ! Sinon, si le fantôme est dans l'état "FrightenedMode", ce dernier passe dans l'état "EatenMode" et le score affiché augmente de 500.
 
_____
 
***Notes*** *:
Grâce à ces patterns, il serait par exemple facile d'ajouter d'autres types de fantômes avec leur propres comportements, ou de leur ajouter d'autres états particuliers sans avoir à modifier le contexte existant. 
Toutefois, après réflexion, on peut noter que la classe "Game" devrait peut-être avoir moins de responsabilités, par exemple les fantômes pourraient implémenter eux-même l'interface "Observer"*
