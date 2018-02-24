public class Porte extends Case {

    private static final char representation = '!';

    public Porte() {
        super(representation);
    }

    @Override
    public boolean interactionPossible(Robot robot) {
        return (robot.getNbCle() != 0) && (this.actif);
    }

    @Override
    public void interagir(Robot robot) {
        if (this.actif) {
            robot.utiliseCle();
            this.desactiver();
        }
    }
}
