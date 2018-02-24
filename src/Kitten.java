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
        System.out.println("You found kitten! Way to go, robot.\n" + nom + " <3 " + robot.getNom());
        this.trouve = true;
    }

    public String getNom() {
        return nom;
    }

    public boolean getTrouve() {
        return trouve;
    }
}
