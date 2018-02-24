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
            this.desactiver();

            robot.ramasseCle();
            System.out.println("You found a key.");
            try {
                // Bloque ici et attends une entrée pour permettre au joueur de voir le message
                System.in.read();
            } catch (Exception e) {  }
        }
    }
}
