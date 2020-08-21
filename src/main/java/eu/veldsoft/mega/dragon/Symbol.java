package eu.veldsoft.mega.dragon;

import java.util.HashMap;
import java.util.Map;

/**
 * Game symbol class representation.
 * 
 * @author Todor Balabanov
 */
final class Symbol implements Comparable {
	/** Symbol kind enumeration. */
	static enum Kind {
		NONE, REGULAR, LOW, HIGH, WILD
	};

	/** Numerical identifier of the symbol. */
	private int id;

	/** Name of the symbol. */
	private String name = "";

	/** Symbol kind flag. */
	private Symbol.Kind kind = Kind.NONE;

	/**
	 * Pay table of a single symbol. The information is given a list of values.
	 * The key is the lower limit of symbol count when the value is the pay out
	 * coefficient according to original game rules.
	 */
	private Map<Integer, Double> pays = new HashMap<Integer, Double>();

	/**
	 * Symbol identifier getter.
	 * 
	 * @return The id of the symbol
	 */
	public int id() {
		return id;
	}

	/**
	 * Symbol identifier setter.
	 * 
	 * @param id
	 *            The id of the symbol to set.
	 */
	public void id(int id) {
		this.id = id;
	}

	/**
	 * Symbol name getter.
	 * 
	 * @return The name of the symbol.
	 */
	public String name() {
		return name;
	}

	/**
	 * Symbol name setter.
	 * 
	 * @param name
	 *            The name of the symbol to set.
	 */
	public void name(String name) {
		this.name = name;
	}

	/**
	 * Symbol kind getter.
	 * 
	 * @return The kind of the symbol.
	 */
	public Symbol.Kind kind() {
		return kind;
	}

	/**
	 * Symbol kind setter.
	 * 
	 * @param kind
	 *            The kind of the symbol to set.
	 */
	public void kind(Symbol.Kind kind) {
		this.kind = kind;
	}

	/**
	 * Symbol pay out list reference getter. A deep copy should be considered a
	 * safer approach for access object members.
	 * 
	 * @return A reference to pay out list of the symbol.
	 */
	public Map<Integer, Double> pays() {
		return pays;
	}

	/**
	 * Symbol pay out list reference setter. A deep copy should be considered a
	 * safer approach for access object members.
	 * 
	 * @param pays
	 *            A reference to pay out list of the symbol to set.
	 */
	public void pays(Map<Integer, Double> pays) {
		this.pays = pays;
	}

	/**
	 * Calculate win multiplier according to the size of the cluster.
	 * 
	 * @param count
	 *            Size of the cluster.
	 * 
	 * @return Calculated win.
	 */
	public double multiplier(int count) {
		double multiplier = 0;

		for (Integer prize : pays.keySet()) {
			/* Multipliers of bigger clusters are missed. */
			if (prize > count) {
				continue;
			}

			/*
			 * Gets the bigger possible multiplier for the bigger possible
			 * cluster size.
			 */
			if (multiplier < pays.get(prize)) {
				multiplier = pays.get(prize);
			}
		}

		return multiplier;
	}

	/**
	 * Compare symbols.
	 * 
	 * @param object
	 *            Object to compare with.
	 * 
	 * @return Difference between the objects.
	 */
	@Override
	public int compareTo(Object object) {
		Symbol that = (Symbol) object;

		double result = 0;

		for (Integer key : this.pays.keySet()) {
			result += this.pays.get(key);
		}

		for (Integer key : that.pays.keySet()) {
			result -= that.pays.get(key);
		}

		return (int) result;
	}

	/**
	 * Represent the object content as a string.
	 */
	@Override
	public String toString() {
		return name;
	}
}
