/**
 * Représente un robot avec un nom et une position, et qui peut détenir un téléporteur et un certain nombre de clés
 */
public class Robot {

    /**
     * La représentation ASCII du robot dans la grille présentée au joueur
     */
    public static final char representation = '#';

    private String nom;
    private Point position;
    private int nbCle = 0;
    private boolean hasTeleporteur = false;

    /**
     * Construit un robot avec le nom et la position spécifiée
     *
     * @param name     Un string contenant le nom du robot
     * @param startPos Un objet point correspondant à la position initiale du robot
     */
    public Robot(String name, Point startPos) {
        this.nom = name;
        this.position = startPos;
    }

    /**
     * Retourne le nom du robot
     *
     * @return Un string contenant le nom du robot
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retourne la position du robot
     *
     * @return Un objet point correspondant à la position actuelle du robot
     */
    public Point getPosition() {
        return position;
    }

    /**
     * Définie la position du robot
     *
     * @param position Un objet point correspondant à la nouvelle position du robot
     */
    public void setPosition(Point position) {
        this.position = position;
    }

    /**
     * Vérifie si le robot a un téléporteur
     *
     * @return Un booléen qui est vrai si le robot a un téléporteur, faux sinon
     */
    public boolean hasTeleporteur() {
        return hasTeleporteur;
    }

    /**
     * Mets à jour l'état du robot lorsqu'il trouve un téléporteur
     */
    public void ramasseTeleporteur() {
        hasTeleporteur = true;
    }

    /**
     * Retourne le nombre de clés que le robot possède
     *
     * @return Un int correspondant au nombre de clés
     */
    public int getNbCle() {
        return nbCle;
    }

    /**
     * Mets à jour l'état du robot lorsqu'il trouve une clé
     */
    public void ramasseCle() {
        nbCle++;
    }

    /**
     * Mets à jour l'état du robot lorsqu'il utilise une clé
     */
    public void utiliseCle() {
        if (nbCle > 0) {
            nbCle--;
        }
    }
}
