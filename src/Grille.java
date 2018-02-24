import java.util.Random;

/**
 * Classe représentant la planche du jeu
 */

public class Grille {
	private Case[][] grille;
	private int nbCaseLargeur, nbCaseHauteur;

	private Kitten kitten;

	// Méthodes publiques

	/**
	 * Constructeur de base de Grille
	 * @param nbrPiecesX   : Nombre de pièces placées horizontalement
	 * @param nbrPiecesY   : Nombre de pièces placées verticalement
	 * @param largeurPiece : Nombre de Case de large par pièce
	 * @param hauteurPiece : Nombre de Case de haut par pièce
	 * @param nbrNonKitten : Nombre de Case contenant un objet NonKitten
	 */
	public Grille(int nbrPiecesX, int nbrPiecesY, 
				  int largeurPiece, int hauteurPiece,
				  int nbrNonKitten) {
		creerGrille(nbrPiecesX, nbrPiecesY, largeurPiece, hauteurPiece);
		remplirGrille(nbrNonKitten, nbrPiecesX, nbrPiecesY, largeurPiece, hauteurPiece);
	}

	/**
	 * Simple accesseur pour kitten
	 * @return Objet Kitten
	 */
	public Kitten getKitten() {
		return kitten;
	}

	/**
	 * Trouve une position vide sur la planche.
	 * @return Retourne la position vide trouvée
	 * @throws IllegalStateException Si la planche est pleine et qu'on ne trouve
	 *         pas de position vide après 100 essais.
	 */
	public Point randomEmptyCell() {
		// Strategy: Pick a point and check if empty. If empty, return, else restart
		Random random = new Random();
		int essaisMax = 100;

		do {
			int x = random.nextInt(nbCaseLargeur);
			int y = random.nextInt(nbCaseHauteur);

			if (getItem(x, y) == null)
				return new Point(x, y);

			essaisMax--;
		} while (essaisMax >= 0);

		throw new IllegalStateException("Failed to find empty position. Board is probably full.");
	}

	/**
	 * Prédicat vérifiant si la position donnée est libre pour le joueur
	 * @param robot : Objet Robot représentant le joueur
	 * @param x     : Composante horizontale de la position considérée
	 * @param y     : Composante verticale de la position considérée
	 * @return      : Vrai si Position donnée permet un déplacement
	 */
	public boolean deplacementPossible(Robot robot, int x, int y) {
		Case item = getItem(x, y);
		return item == null || item.interactionPossible(robot);
	}

	/**
	 * Affiche la planche de jeu avec le joueur
	 * @param robot : Objet Robot représentant le joueur
	 */
	public void afficher(Robot robot) {
		String accum = "";
		for (int y = nbCaseHauteur - 1; y >= 0; y--) {
			for (int x = 0; x < nbCaseLargeur; x++) {
				if (robot.getPosition().egal(x, y)) {
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

	/**
	 * Fait interagir Robot avec la case à sa position actuelle
	 * @param robot : Objet Robot représentant le joueur
	 */
	public void interagir(Robot robot) {
		Case item = getItem(robot.getPosition());
		if (item != null)
			item.interagir(robot);
	}

	// Méthodes helpers

	// Crée l'object grille, les murs et les portes

	/**
	 * Crée la grille avec les paramètres donnés.
	 * Ne fait que placer les murs et les portes.
	 * @param nbrPiecesX   : Nombre de pièces placées horizontalement
	 * @param nbrPiecesY   : Nombre de pièces placées verticalement
	 * @param largeurPiece : Nombre de Case de large par pièce
	 * @param hauteurPiece : Nombre de Case de haut par pièce
	 */
	private void creerGrille(int nbrPiecesX, int nbrPiecesY, int largeurPiece, int hauteurPiece){
		this.nbCaseLargeur = nbrPiecesX * largeurPiece + 1;
		this.nbCaseHauteur = nbrPiecesY * hauteurPiece + 1;

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

	/**
	 * Place les éléments dans la grille.
	 * Plus précisément, place les clés, le kitten, le téléporteur et les NonKitten items
	 * @param nbElement    : Nombre de Case contenant un objet NonKitten
	 * @param nbrPiecesX   : Nombre de pièces placées horizontalement
	 * @param nbrPiecesY   : Nombre de pièces placées verticalement
	 * @param largeurPiece : Nombre de Case de large par pièce
	 * @param hauteurPiece : Nombre de Case de haut par pièce
	 */
	private void remplirGrille(int nbElement, int nbrPiecesX, int nbrPiecesY, int largeurPiece, int hauteurPiece) {
		placerCle(nbrPiecesX, nbrPiecesY, largeurPiece, hauteurPiece); // En premier, car ne verifie pas si case est vide.
		placerKitten();
		placerTeleporteur();
		for (int i = 0; i < nbElement; i++) {
			NonKitten item = new NonKitten(NonKitten.getRandomSymbole(), NonKitten.getRandomDescription());
			setItem(randomEmptyCell(), item);
		}
	}

	// Une clé par pièce

	/**
	 * Place une clée par pièce
	 * @param nbrPiecesX   : Nombre de pièces placées horizontalement
	 * @param nbrPiecesY   : Nombre de pièces placées verticalement
	 * @param largeurPiece : Nombre de Case de large par pièce
	 * @param hauteurPiece : Nombre de Case de haut par pièce
	 */
	private void placerCle(int nbrPiecesX, int nbrPiecesY, int largeurPiece, int hauteurPiece) {
		for (int i = 0; i < nbrPiecesX; i++){
			for (int j = 0; j < nbrPiecesY; j++){
				int x = (int) (i * largeurPiece+Math.random() * (largeurPiece - 1) + 1);
				int y = (int) (j * hauteurPiece+Math.random() * (hauteurPiece - 1) + 1);
				setItem(x, y, new Cle());
			}
		}
	}

	/**
	 * Crée et place le kitten sur la planche à une position aléatoire.
	 */
	private void placerKitten() {
		kitten = new Kitten(Kitten.getRandomSymbole(), "Feu Cumulus");
		setItem(randomEmptyCell(), kitten);
	}

	/**
	 * Crée et place le téléporteur sur la planche à une position aléatoire.
	 */
	private void placerTeleporteur() {
		Teleporteur teleporteur = new Teleporteur();
		setItem(randomEmptyCell(), teleporteur);
	}

}
