public class Porte extends Case {

	private static final char representationFermee = '!';
	private static final char representationOuvert = ' ';

	public Porte() {
		super(representationFermee);
	}

	@Override
	public boolean interactionPossible(Robot robot) {
		return robot.getNbCle() != 0;
	}

	@Override
	public void interagir(Robot robot) {
		this.representation = representationOuvert;
	}

}
