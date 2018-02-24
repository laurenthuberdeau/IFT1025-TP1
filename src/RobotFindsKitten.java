import java.util.Scanner;

public class RobotFindsKitten {

	private Grille grille;
	private Robot  robot;

	private Scanner scanner;

	public static void main(String[] args) {
		RobotFindsKitten game = new RobotFindsKitten();
		game.play();
	}

	public RobotFindsKitten() {
		grille = new Grille(2,2,12,6, 10);
		robot = new Robot("R.O.B.", grille.randomEmptyCell());
		scanner = new Scanner(System.in);
	}

	//  Game enty point (REPL: Read Eval Print Loop)
	public void play() {
		showWelcome();
		while (!grille.getKitten().estTrouve()) {
			showBoard();
			char move = getMove();
			movePlayer(move);
		}
		showWinMessage();
	}

	// Prints welcome message
	private void showWelcome() {
		System.out.println(
			"       Bienvenue dans RobotFindsKitten\n" +
			"Super Dungeon Master 3000 Ultra Turbo Edition !");
	}

	// Shows You found kitten...
	private void showWinMessage() {
		String message = "You found kitten! Way to go, robot.";
		message += grille.getKitten().getNom() + "<3" + robot.getNom();
		System.out.println(message);
	}

	// Shows map with objects (Kitten included) and player on it
	private void showBoard() {
		grille.afficher(robot);
	}

	// Waits for player input. Null => No move
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

	// Shows {PlayerName} [{KeyCount}] >
	private void showPrompt() {
		String promptText = robot.getNom() + " [" + robot.getNbCle() + "]";
		if (robot.hasTeleporteur())
			promptText += "T";
		promptText += "> ";

		// Important! No newline after!
		System.out.print(promptText);
	}

	// Moves player if possible and/or (inspect object or open door)
	private void movePlayer(char move) {
		Point newPos = getNewRobotPosition(move);
		if (grille.deplacementPossible(robot, newPos.getX(), newPos.getY())) {
			robot.setPosition(newPos);
			grille.interagir(robot);
		}
	}

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
