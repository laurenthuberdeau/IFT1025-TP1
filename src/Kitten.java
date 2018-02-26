/**
 * Représente un chatton qui a un nom, et qui peut avoir été trouvé ou pas par un robot
 */
public class Kitten extends Case {

    private String nom;
    private boolean trouve;

    /**
     * Construit un chat avec une représentation et un nom donnés
     *
     * @param representation Un caractère ASCII qui représentera le chaton dans la grille de jeu
     * @param nom            Un String correspond au nom du chaton
     */
    public Kitten(char representation, String nom) {
        super(representation);
        this.nom = nom;
        this.trouve = false;
    }

    /**
     * Vérifie si le robot peut interagir avec le chaton
     *
     * @param robot Le robot qui interagirait avec le chaton
     * @return Un booléen toujours vrai
     */
    @Override
    public boolean interactionPossible(Robot robot) {
        return true;
    }

    /**
     * Définie l'interaction d'un robot avec le chaton;
     * change l'état du chaton pour indiquer qu'il a été trouvé
     *
     * @param robot Le robot qui interagit avec le chaton
     */
    @Override
    public void interagir(Robot robot) {
        trouve = true;
    }

    /**
     * Retourne le nom du chaton
     *
     * @return Un String correspond au nom du chaton
     */
    public String getNom() {
        return nom;
    }

    /**
     * Vérifie si le chaton a été trouvé
     *
     * @return Un booléen qui est vrai si le chaton a été trouvé
     */
    public boolean estTrouve() {
        return trouve;
    }
}
