public class Teleporteur extends Case {

	private static final char representation = ' ';

	public Teleporteur() {
		super(representation);
	}

	@Override
	public boolean interactionPossible(Robot robot) {
		return true;
	}

	@Override
	public void interagir(Robot robot) {

	}

}
