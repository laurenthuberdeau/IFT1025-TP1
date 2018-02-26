import java.util.Scanner;

/**
 * IFT1025 - Programmation 2
 * TP 1 : Jeu en console Robot Finds Kitten
 * Par Simon Chabot et Laurent Huberdeau
 *
 * Cette classe est la classe orchestrant le jeu.
 * Elle comprend le main et le REPL.
 */

public class RobotFindsKitten {

	private Grille grille;
	private Robot  robot;

	private Scanner scanner;

	/**
	 * Point d'entrée du programme
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Quel est le nom du robot? ");
		String nomRobot = scanner.nextLine().trim();
		System.out.print("Quel est le nom du chaton? ");
		String nomKitten = scanner.nextLine().trim();
		if (nomRobot.isEmpty())
			nomRobot = "R.O.B";
		if (nomKitten.isEmpty())
			nomKitten = "Feu Cumulus";

		RobotFindsKitten game = new RobotFindsKitten(nomRobot, nomKitten);
		game.play();
	}

	/**
	 * Constructeur du Jeu par défaut.
	 *
	 * @param nomRobot Nom à donner au robot
	 * @param nomKitten Nom à donner au Kitten
	 */
	public RobotFindsKitten(String nomRobot, String nomKitten) {
		grille = new Grille(2,2,12,6, 10);
		grille.getKitten().setNom(nomKitten);

		robot = new Robot(nomRobot, grille.randomEmptyCell());

		scanner = new Scanner(System.in);
	}

	/**
	 * Point d'entrée dans le REPL
	 */
	public void play() {
		showWelcome();
		while (!grille.getKitten().estTrouve()) {
			showBoard();
			char move = getMove();
			movePlayer(move);
		}
		showWinMessage();
	}

	/**
	 * Affiche message d'accueil.
	 */
	private void showWelcome() {
		System.out.println(
			"       Bienvenue dans RobotFindsKitten\n" +
			"Super Dungeon Master 3000 Ultra Turbo Edition !\n");
	}


	/**
	 * Affiche message de victoire
	 * Le message a la forme "You found kitten! Way to go, robot. \n {Kitten.getNom()} <3 {Robot.getNom()}"
	 * Sur deux lignes
	 */
	private void showWinMessage() {
		String message = "You found kitten! Way to go, robot.";
		message += "\n" + grille.getKitten().getNom() + " <3 " + robot.getNom();
		System.out.println(message);
	}

	/**
	 * Affiche la planche de jeu
	 */
	private void showBoard() {
		grille.afficher(robot);
	}

	// Waits for player input. Null => No move

	/**
	 * Attend une entrée de la part du joueur
	 * Vérifie que l'entrée est valide et qu'elle peut être jouée
	 * Partie READ du REPL
	 *
	 * @return Le mouvement sous forme de char minuscule.
	 */
	private char getMove() {
		String allowedChars = "asdw";
		if (robot.hasTeleporteur()) allowedChars+='t';

		Character moveChar = null;

		do {
			showPrompt();
			String entree = scanner.next();
			if (entree.length() == 1
				&& allowedChars.indexOf(entree.charAt(0)) != -1)
				moveChar = entree.charAt(0);
			else
				System.out.println("Unallowed character. Please enter one of " + allowedChars);
		} while(moveChar == null);

		return moveChar;
	}

	/**
	 * Affiche le début de la ligne
	 * Le message a la forme "{Robot.getNom()} [{Robot.getNbCle()}] T > "
	 */
	private void showPrompt() {
		String promptText = robot.getNom() + " [" + robot.getNbCle() + "]";
		if (robot.hasTeleporteur())
			promptText += " T ";
		promptText += "> ";

		// Important! Ne pas utiliser println
		System.out.print(promptText);
	}

	/**
	 * Modifie la position du joueur.
	 * Fait l'interaction entre le joueur et l'objet sous lui si nécessaire
	 *
	 * @param move : Mouvement retourné par this.getMove()
	 */
	private void movePlayer(char move) {
		Point newPos = getNewRobotPosition(move);
		if (grille.deplacementPossible(robot, newPos.getX(), newPos.getY())) {
			robot.setPosition(newPos);
			grille.interagir(robot);
		}
	}

	/**
	 * Modifie la position du joueur
	 *
	 * @param  move : Mouvement retourné par this.getMove()
	 * @return Point : Position modifiée du Robot après mouvement
	 * @throws IllegalArgumentException Si move ne fait pas parti de "asdwt"
	 */
	private Point getNewRobotPosition(char move) {
		Point robotPos = robot.getPosition();
		switch (move) {
			case 'a':
				return new Point(robotPos.getX() - 1, robotPos.getY());
			case 'd':
				return new Point(robotPos.getX() + 1, robotPos.getY());
			case 's':
				return new Point(robotPos.getX(), robotPos.getY() - 1);
			case 'w':
				return new Point(robotPos.getX(), robotPos.getY() + 1);
			case 't':
				return grille.randomEmptyCell();
			default:
				throw new IllegalArgumentException("Move isn't valid: " + move);
		}
	}
}
