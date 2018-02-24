/**
 * Simple POJO représentant une paire d'int (Isomorphique à Paire<Int, Int>)
 * Représente soit une position ou un déplacement (Vecteur 2D)
 */

public class Point {
	private final int x, y;

	/**
	 * @param x Composante horizontale du Point
	 * @param y Composante verticale du Point
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Vérifie l'égalité de valeurs (Non référence) entre deux Points
	 * @param x Composante horizontale du Point comparé
	 * @param y Composante verticale du Point comparé
	 * @return Vrai ssi les composants sont égaux aux arguments
	 */
	public boolean egal(int x, int y) {
		return x == this.x && y == this.y;
	}

	/**
	 * Simple accesseur de la composante horizontale
	 * @return Composante horizontale
	 */
	public int getX() {
		return x;
	}

	/**
	 * Simple accesseur de la composante verticale
	 * @return Composante verticale
	 */
	public int getY() {
		return y;
	}
}