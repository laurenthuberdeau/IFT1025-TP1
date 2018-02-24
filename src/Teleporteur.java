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
            this.desactiver();

            robot.ramasseTeleporteur();
            System.out.println("You found the teleporter. Press T to teleport anywhere on map.");
            try {
                // Bloque ici et attends une entrée pour permettre au joueur de voir le message
                System.in.read();
            } catch (Exception e) {  }
        }
    }
}
