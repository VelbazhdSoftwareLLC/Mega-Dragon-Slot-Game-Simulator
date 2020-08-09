package eu.veldsoft.mega.dragon;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.AbstractMap.SimpleEntry;

/**
 * Description of each cluster.
 * 
 * @author Todor Balabanov
 */
final class Cluster {
	/** Pseudo-random number generator instance. */
	private static final Random PRNG = new Random();

	/** Cluster symbol. */
	private Symbol symbol;

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
		coordinates( coordinates );

		centering();
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

		/* Sorting is very important in order hash code calculation to work. */
		boolean done = false;
		while(done == false) {
			done = true;

			for(int i=0; i<this.coordinates.size()-1; i++) {
				/* When elements are in proper order do nothing. */
				if(this.coordinates.get(i).getKey() < this.coordinates.get(i+1).getKey()) {
					continue;
				} else if(this.coordinates.get(i).getKey() == this.coordinates.get(i+1).getKey() && this.coordinates.get(i).getValue() < this.coordinates.get(i+1).getValue()) {
					continue;
				}

				/* Swap elements to be in better order. */
				SimpleEntry<Integer, Integer> value = this.coordinates.get(i);
				this.coordinates.set(i, this.coordinates.get(i+1));
				this.coordinates.set(i+1, value);

				/* Loop once again. */
				done = false;
			}
		}
	}

	/**
	 * Calculate center of the cluster.
	 * 
	 * @return Array with two numbers for x and y coordinates.
	 */
	public void centering() {
		int min = Integer.MAX_VALUE;
		for (SimpleEntry<Integer, Integer> a : coordinates) {
			int distance = 0;
			for (SimpleEntry<Integer, Integer> b : coordinates) {
				/* The distance between the cell itself is zero. */
				if (a == b) {
					continue;
				}

				/* Euclidean distance but without a square root. */
				distance += (a.getKey() - b.getKey())
						* (a.getKey() - b.getKey())
						+ (a.getValue() - b.getValue())
								* (a.getValue() - b.getValue());
			}

			/* If a shorter distance is found keep cell coordinates. */
			if (distance < min && symbol.kind() != Symbol.Kind.WILD) {
				x = a.getKey();
				y = a.getValue();

				min = distance;
			}
		}
	}

	/**
	 * Select coordinates for the wilds but in such way that wilds to be as far from each other as possible. 
	 *
	 * @param number How many wilds to be positioned.
	 *
	 * @return List of wilds coordinates.
	 */
	public List<SimpleEntry<Integer, Integer>> wilds(int number) {
		List<SimpleEntry<Integer, Integer>> result = null;

		/* If certain times there is no improvement keep the found configuration. */
		for(int attempt=0, max=0; attempt<10;) {
			List<SimpleEntry<Integer, Integer>> wilds = new ArrayList<SimpleEntry<Integer, Integer>>();

			/* Generate a random candidate configuration. */
			for(int i=0; i<number && i<coordinates.size(); i++) {
				SimpleEntry<Integer, Integer> value = null;
				do { value = coordinates.get(PRNG.nextInt(coordinates.size())); } while( wilds.contains(value) );
				wilds.add( value );
			}

			/* Calculate total distance between the cells. */
			int distance = 0;
			for (SimpleEntry<Integer, Integer> a : coordinates) {
				for (SimpleEntry<Integer, Integer> b : coordinates) {
					/* The distance between the cell itself is zero. */
					if (a == b) {
						continue;
					}

					/* Euclidean distance but without a square root. */
					distance += (a.getKey() - b.getKey())
							* (a.getKey() - b.getKey())
							+ (a.getValue() - b.getValue())
									* (a.getValue() - b.getValue());
				}
			}

			/* If bigger distance is found just keep it as a possible final solution. */
			if(max < distance) {
				max = distance;
				result = wilds;
				attempt = 0;
			} else {
				attempt++;
			}
		}

		return result;
	}

	/**
	 * Represent the object content as a string.
	 */
	@Override
	public String toString() {
		return "" + symbol + " " + count + " " + x + " " + y + "";
	}
	
	/** 
	 * The simplest possible hash code of the object.
	 * 
	 * @return The hash code.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coordinates == null) ? 0 : coordinates.hashCode());
		return result;
	}

	/**
	 * Compares two objects.
	 * 
	 * @return True if they are equal and false otherwise.	
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cluster other = (Cluster) obj;
		if (coordinates == null) {
			if (other.coordinates != null)
				return false;
		} else if (!coordinates.equals(other.coordinates))
			return false;
		return true;
	}
}
