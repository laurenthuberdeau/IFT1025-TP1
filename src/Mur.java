public class Mur extends Case {

	private static final char representation = '%';

	public Mur() {
		super(representation);
	}

	@Override
	public boolean interactionPossible(Robot robot) {
		return false;
	}

	@Override
	public void interagir(Robot robot) {

	}

}
