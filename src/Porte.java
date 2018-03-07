/**
 * Représente une porte que le robot peut ouvrir s'il possède au moins une clé
 */
public class Porte extends Case {

    private static final char representation = '!';

    /**
     * Construit une porte
     */
    public Porte() {
        super(representation);
    }

    /**
     * Vérifie si l'interaction est possible avec un robot
     *
     * @param robot Le robot qui interagirait avec la porte
     * @return Un booléen qui est vrai si la porte est toujours active (barrée) et que le robot possède au moins une clé
     */
    @Override
    public boolean interactionPossible(Robot robot) {
        return robot.getNbCle() != 0 && this.estActif();
    }

    /**
     * Définie l'interaction du robot avec la porte:
     * si la porte est active (barrée), elle est désactivée;
     * la méthode utiliseCle du robot est appelée afin de mettre à jour son état (nombre de clés)
     *
     * @param robot Le robot qui interagit avec la porte
     */
    @Override
    public void interagir(Robot robot) {
        if (this.estActif()) {
            robot.utiliseCle();
            this.desactiver();
        }
    }
}
