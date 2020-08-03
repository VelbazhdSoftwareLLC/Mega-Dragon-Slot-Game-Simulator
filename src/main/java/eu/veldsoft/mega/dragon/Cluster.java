package eu.veldsoft.mega.dragon;

/**
 * Description of each cluster.
 * 
 * @author Todor Balabanov
 */
final class Cluster {
	/** Cluster symbol. */
	Symbol symbol;

	/** Cluster size. */
	private int count;

	/** Start of the cluster x coordinate. */
	private int x;

	/** Start of the cluster y coordinate. */
	private int y;

	/**
	 * Constructor with all parameters.
	 * 
	 * @param symbol
	 *            Cluster symbol.
	 * @param count
	 *            Cluster size.
	 * @param x
	 *            Start of the cluster x coordinate.
	 * @param y
	 *            Start of the cluster y coordinate.
	 */
	public Cluster(Symbol symbol, int count, int x, int y) {
		super();

		this.symbol = symbol;
		this.count = count;
		this.x = x;
		this.y = y;
	}

	/**
	 * Cluster symbol getter.
	 * 
	 * @return The symbol of the cluster.
	 */
	public Symbol symbol() {
		return symbol;
	}

	/**
	 * Cluster symbol setter.
	 * 
	 * @param symbol
	 *            The symbol to set.
	 */
	public void symbol(Symbol symbol) {
		this.symbol = symbol;
	}

	/**
	 * Cluster size getter.
	 * 
	 * @return The size of the cluster.
	 */
	public int count() {
		return count;
	}

	/**
	 * Cluster size setter.
	 * 
	 * @param count
	 *            The size of the cluster to set.
	 */
	public void count(int count) {
		this.count = count;
	}

	/**
	 * Cluster start x coordinate getter.
	 * 
	 * @return The x coordinate of the cluster.
	 */
	public int x() {
		return x;
	}

	/**
	 * Cluster start x coordinate setter.
	 * 
	 * @param x
	 *            The x coordinate of the cluster.
	 */
	public void x(int x) {
		this.x = x;
	}

	/**
	 * Cluster start y coordinate getter.
	 * 
	 * @return The y coordinate of the cluster.
	 */
	public int y() {
		return y;
	}

	/**
	 * Cluster start y coordinate setter.
	 * 
	 * @param y
	 *            The y coordinate of the cluster.
	 */
	public void y(int y) {
		this.y = y;
	}

	/**
	 * Represent the object content as a string.
	 */
	@Override
	public String toString() {
		return "" + symbol + " " + count + " " + x + " " + y + "";
	}
}
