public class Teleporteur extends Case {

    // SYMBOLE T POUR FACILITER TESTING
    private static final char representation = 'T'; // getRandomSymbole();

    public Teleporteur() {
        super(representation);
    }

    @Override
    public boolean interactionPossible(Robot robot) {
        return (this.actif);
    }

    @Override
    public void interagir(Robot robot) {
        if (this.actif) {
            robot.ramasseTeleporteur();
            this.desactiver();
        }
    }
}
