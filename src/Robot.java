public class Robot {

    public static final char representation = '#';

    private String nom;
    private Point position;
    private int nbCle = 0;
    private boolean hasTeleporteur = false;

    public Robot(String name, Point startPos) {
        this.nom = name;
        this.position = startPos;
    }

    public String getNom() {
        return nom;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public boolean hasTeleporteur() {
        return hasTeleporteur;
    }

    public void ramasseTeleporteur() {
        hasTeleporteur = true;
    }

    public int getNbCle() {
        return nbCle;
    }

    public void ramasseCle() {
        nbCle++;
    }

    public boolean utiliseCle() {
        if (nbCle > 0) {
            nbCle--;
            return true;
        }
        return false;
    }
}
