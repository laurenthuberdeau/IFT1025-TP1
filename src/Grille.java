import java.util.Random;

public class Grille {
	private Case[][] grille;
	private int nbCaseLargeur, nbCaseHauteur, nbrPiecesX, nbrPiecesY, largeurPiece, hauteurPiece;

	private Kitten kitten;

	// Public interface
	
	public Grille(int nbrPiecesX, int nbrPiecesY, 
				  int largeurPiece, int hauteurPiece,
				  int nbrNonKitten) {
		this.nbrPiecesX=nbrPiecesX;
		this.nbrPiecesY=nbrPiecesY;
		this.largeurPiece=largeurPiece;
		this.hauteurPiece=hauteurPiece;
		this.nbCaseLargeur = nbrPiecesX * largeurPiece + 1;
		this.nbCaseHauteur = nbrPiecesY * hauteurPiece + 1;
		creerGrille();
		remplirGrille(nbrNonKitten);
	}

	public Kitten getKitten() {
		return kitten;
	}

	
	public Point randomEmptyCell() {
		// Strategy: Pick a point and check if empty. If empty, return, else restart
		Random random = new Random();
		Point point = null;

		do {
			int x = random.nextInt(nbCaseLargeur);
			int y = random.nextInt(nbCaseHauteur);

			if (getItem(x, y) == null)
				point = new Point(x, y);

		} while (point == null);

		return point;
	}
	
	public boolean deplacementPossible(Robot robot, int x, int y) {
		Case item = getItem(x, y);
		return !(item instanceof Mur ||
				(item instanceof Porte) && (item.getActif()) && (robot.getNbCle() == 0));
	}
	
	public void afficher(Robot robot) {
		String accum = "";
		for (int y = nbCaseHauteur - 1; y >= 0; y--) {
			for (int x = 0; x < nbCaseLargeur; x++) {
				if (robot.getPosition().getX() == x && robot.getPosition().getY() == y) {
					accum += Robot.representation;
				} else {
					Case item = getItem(x, y);
					accum += item != null ? item.getRepresentation() : ' ';
				}
			}
			accum += "\n";
		}
		System.out.println(accum);
	}
	
	public void interagir(Robot robot) {
		Case item = getItem(robot.getPosition());
		if (item != null){ item.interagir(robot); }
	}

	// Helper methods


	// Crée l'object grille, les murs et les portes

	private void creerGrille(){

		this.grille = new Case[nbCaseHauteur][nbCaseLargeur];

		for (int x = 0; x < nbCaseLargeur; x++) {
			for (int y = 0; y < nbCaseHauteur; y++) {
				Case piece = null;
				// On vérifie si on est sur un mur
				if (x % largeurPiece == 0 || y % hauteurPiece == 0) {

					// On place la porte au centre du mur, donc on regarde si on est au centre
					if (x != 0 && x != nbCaseLargeur - 1 && y != 0 && y != nbCaseHauteur - 1
							&& ((x - largeurPiece / 2) % largeurPiece == 0
							|| (y - hauteurPiece / 2) % hauteurPiece == 0))
						piece = new Porte();
					else
						piece = new Mur();
				}

				setItem(x, y, piece);
			}
		}
	}

	private Case getItem(Point point) {
		return grille[point.getY()][point.getX()];
	}

	private Case getItem(int x, int y) {
		return getItem(new Point(x, y));
	}

	private void setItem(Point point, Case item) {
		grille[point.getY()][point.getX()] = item;
	}

	private void setItem(int x, int y, Case item) {
		setItem(new Point(x,y), item);
	}

	private void remplirGrille(int nbElement) {
		placerCle(); // En premier, car ne verifie pas si case est vide.
		for (int i = 0; i < nbElement; i++) {
			NonKitten item = new NonKitten(NonKitten.getRandomSymbole(), NonKitten.getRandomDescription());
			setItem(randomEmptyCell(), item);
		}
		placerKitten();
		placerTeleporteur();
	}

	private void placerKitten() {

		//kitten = new Kitten(Kitten.getRandomSymbole(), "Feu Cumulus");
		kitten = new Kitten('K', "Feu Cumulus"); // SYMBOLE K POUR FACILITER TESTING

		setItem(randomEmptyCell(), kitten);
	}

	private void placerCle() { // Une cle par piece
		for (int i = 0; i<nbrPiecesX;i++){
			for (int j = 0; j<nbrPiecesY;j++){
				int coordX=(int)(i*largeurPiece+Math.random()*(largeurPiece-1)+1);
				int coordY=(int)(j*hauteurPiece+Math.random()*(hauteurPiece-1)+1);
				setItem(coordX, coordY, new Cle());
			}
		}
	}

	private void placerTeleporteur() {
		Teleporteur teleporteur = new Teleporteur();
		setItem(randomEmptyCell(), teleporteur);
	}

}
