/**
 * Représente un segment de mur
 */
public class Mur extends Case {

    private static final char representation = '%';

    /**
     * Construit un segment de mur
     */
    public Mur() {
        super(representation);
    }

    /**
     * Vérifie si l'interaction est possible avec un robot
     *
     * @param robot Le robot qui interagirait avec la case
     * @return Booléen toujours faux
     */
    @Override
    public boolean interactionPossible(Robot robot) {
        return false;
    }

    /**
     * Définie l'interaction du robot avec le mur (aucun effet)
     *
     * @param robot  Le robot qui interagit avec la case
     */
    @Override
    public void interagir(Robot robot) {

    }
}
