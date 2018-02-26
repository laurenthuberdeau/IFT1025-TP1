import java.util.Scanner;

/**
 * Représente une clé qui est ramassée par le robot s'il interagit avec
 */
public class Cle extends Case {

    private static final char representation = '\'';

    /**
     * Construit une clé
     */
    public Cle() {
        super(representation);
    }

    /**
     * Vérifie si l'interaction est possible avec un robot
     *
     * @param robot Le robot qui interagirait avec la clé
     * @return Un booléen qui est vrai si la clé est toujours active (non ramassée)
     */
    @Override
    public boolean interactionPossible(Robot robot) {
        return this.actif;
    }

    /**
     * Définie l'interaction du robot avec la clé:
     * si la clé est active (pas encore ramassée), alors elle est désactivée et la méthode ramasseCle du robot
     * est appelée pour mettre à jour son état (nombre de clés);
     * un message est affichée pour informer le joueur
     *
     * @param robot Le robot qui interagit avec la clé
     */
    @Override
    public void interagir(Robot robot) {
        if (this.actif) {
            this.desactiver();

            robot.ramasseCle();
            System.out.println("You found a key.");
            // Bloque ici et attends une entrée pour permettre au joueur de voir le message
            new Scanner(System.in).nextLine();
        }
    }
}
