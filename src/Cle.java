public class Cle extends Case {

	private static final char representation = '$';

	public Cle() {
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
