public class Teleporteur extends Case {

    private static final char representation = getRandomSymbole();

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
