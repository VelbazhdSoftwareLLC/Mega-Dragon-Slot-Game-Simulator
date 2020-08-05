package eu.veldsoft.mega.dragon;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;

/**
 * Description of each cluster.
 * 
 * @author Todor Balabanov
 */
final class Cluster {
	/** Cluster symbol. */
	Symbol symbol;

	/** Start of the cluster x coordinate. */
	private int x;

	/** Start of the cluster y coordinate. */
	private int y;

	/** Cluster size. */
	private int count;

	/** Coordinates of the symbols in the cluster, but wilds are excluded. */
	private List<SimpleEntry<Integer, Integer>> coordinates;

	/**
	 * Constructor with all parameters.
	 * 
	 * @param symbol
	 *            Cluster symbol.
	 * @param x
	 *            Start of the cluster x coordinate.
	 * @param y
	 *            Start of the cluster y coordinate.
	 * @param count
	 *            Cluster size.
	 * @param coordinates
	 *            Coordinates of the symbols in the cluster, but wilds are
	 *            excluded.
	 */
	public Cluster(Symbol symbol, int x, int y, int count,
			List<SimpleEntry<Integer, Integer>> coordinates) {
		super();

		this.symbol = symbol;
		this.x = x;
		this.y = y;
		this.count = count;
		this.coordinates = coordinates;
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
	 * Coordinates of the symbols in the cluster getter.
	 * 
	 * @return The coordinates of the symbols.
	 */
	public List<SimpleEntry<Integer, Integer>> coordinates() {
		return coordinates;
	}

	/**
	 * Coordinates of the symbols in the cluster getter.
	 * 
	 * @param coordinates
	 *            The coordinates of symbols to set.
	 */
	public void coordinates(List<SimpleEntry<Integer, Integer>> coordinates) {
		this.coordinates = coordinates;
	}

	/**
	 * Represent the object content as a string.
	 */
	@Override
	public String toString() {
		return "" + symbol + " " + count + " " + x + " " + y + "";
	}
}
