import java.util.Scanner;

/**
 * Représente un téléporteur qui est ramassée par le robot s'il interagit avec;
 * sa représentation est aléatoire.
 */
public class Teleporteur extends Case {

    /**
     * Construit un téléporteur
     */
    public Teleporteur() {
        super(getRandomSymbole());
    }

    /**
     * Vérifie si l'interaction est possible avec un robot
     *
     * @param robot Le robot qui interagirait avec le téléporteur
     * @return Un booléen qui est vrai si le téléporteur est toujours actif (non ramassé)
     */
    @Override
    public boolean interactionPossible(Robot robot) {
        return this.estActif();
    }

    /**
     * Définie l'interaction du robot avec le téléporteur:
     * si le téléporteur est actif (pas encore ramassée), alors il est désactivée
     * et la méthode ramasseTeleporteur du robot est appelée pour mettre à jour son état (possession d'un téléporteur);
     * un message est affichée pour informer le joueur
     *
     * @param robot Le robot qui interagit avec le téléporteur
     */
    @Override
    public void interagir(Robot robot) {
        if (this.estActif()) {
            this.desactiver();
            robot.ramasseTeleporteur();

            System.out.println("You found the teleporter. Press T to teleport anywhere on map.");
            // Bloque ici et attends une entrée pour permettre au joueur de voir le message
            new Scanner(System.in).nextLine();
        }
    }
}
