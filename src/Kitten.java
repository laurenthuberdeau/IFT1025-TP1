public class Kitten extends Case {

    private String nom;
    private boolean trouve;

    public Kitten(char representation, String nom) {
        super(representation);
        this.nom = nom;
        this.trouve = false;
    }

    @Override
    public boolean interactionPossible(Robot robot) {
        return true;
    }

    @Override
    public void interagir(Robot robot) {
        trouve = true;
    }

    public String getNom() {
        return nom;
    }

    public boolean estTrouve() {
        return trouve;
    }
}
