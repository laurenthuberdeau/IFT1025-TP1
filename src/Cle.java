public class Cle extends Case {

    private static final char representation = '\'';

    public Cle() {
        super(representation);
    }

    @Override
    public boolean interactionPossible(Robot robot) {
        return this.actif;
    }

    @Override
    public void interagir(Robot robot) {
        if (this.actif) {
            robot.ramasseCle();
            this.desactiver();
        }
    }
}

