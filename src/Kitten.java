public class Kitten extends Case {

	private String nom;

	public Kitten(char representation, String nom) {
		super(representation);
		this.nom = nom;
	}

	@Override
	public boolean interactionPossible(Robot robot) {
		return true;
	}

	@Override
	public void interagir(Robot robot) {

	}

	public String getNom() {
		return nom;
	}
}
