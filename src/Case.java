/**
 * Représente une case dans la grille de jeu.
 * Cette case a une représentation sous la forme d'un caractère ASCII et elle peut être active ou inactive.
 */
public abstract class Case {

    protected char representation;
    protected boolean actif;
    protected static char representationInactif = ' ';

    /**
     * Construit une case avec la représentation donnée
     *
     * @param representation Le char représentant la case dans la grille présentée au joueur
     */
    public Case(char representation) {
        this.representation = representation;
        this.actif = true;
    }

    /**
     * Désactive la case, en changeant son état pour indiquer que l'interaction avec le joueur n'est plus possible.
     * Elle sera maintenant représentée par une case vide dans la grille ASCII.
     */
    public void desactiver() {
        this.representation = representationInactif;
        this.actif = false;
    }

    /**
     * Vérifie si la case est active
     *
     * @return Un booléen qui est vrai si la case est active
     */
    public boolean getActif() {
        return actif;
    }

    /**
     * Retourne la représentation de la case (un seul caractère)
     *
     * @return La représentation de la case
     */
    public char getRepresentation() {
        return representation;
    }

    /**
     * Indique si une interaction entre la case et le robot est
     * possible (ex.: le robot peut interagir avec un NonKittenItem
     * en tout temps, mais ne peut pas interagir avec un mur, le robot
     * peut interagir avec une porte seulement s’il possède une clé,
     * etc.)
     *
     * @param robot Le robot qui interagirait avec la case
     * @return true si une interaction entre le robot et la case est possible
     */
    public abstract boolean interactionPossible(Robot robot);

    /**
     * Interaction entre la case et le robot
     *
     * @param robot
     */
    public abstract void interagir(Robot robot);

    /**
     * Génère un symbole aléatoire
     *
     * @return Un symbole ASCII compris entre ’:’ et ’~’
     */
    public static char getRandomSymbole() {
        return (char) (Math.random() * (126 - 58) + 58);
    }
}
