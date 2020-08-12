package eu.veldsoft.mega.dragon;

import java.util.Random;

/**
 * Implements dragon bonus feature.
 * 
 * @author Todor Balabanov
 */
public enum Dragon {
	/**
	 * When the dragon is not presented this constant helps null pointer not to
	 * be used.
	 */
	NONE(0.0, new int[][]{{}}),

	/** Green dragon properties. */
	GREEN(0.5, new int[][]{{1, 2, 3, 4, 5}, {3, 5, 7, 9, 11}}),

	/** Gold dragon properties. */
	GOLD(0.3, new int[][]{{1, 2, 3, 4, 5}, {3, 5, 7, 9, 11}}),

	/** Red dragon properties. */
	RED(0.2, new int[][]{{1, 2, 3}, {2, 3, 4}});

	/** Pseudo-random number generator instance. */
	private static final Random PRNG = new Random();

	/** Total cumulative probability for all the dragons. */
	private static double total = 0;

	/** The probability this particular dragon to appear. */
	private double probability = 0;

	/**
	 * Each dragon has different strength according to how many wilds triggered
	 * it
	 */
	private int strength[][] = new int[2][];

	/** Some static members initialization. */
	static {
		total = 0;
		for (Dragon dragon : values()) {
			total += dragon.probability;
		}
	}

	/**
	 * Pick one of the dragons according to their chances to appear.
	 * 
	 * @return A dragon selected after the scrambling.
	 */
	public static Dragon scramble() {
		/*
		 * Stop level in the cumulative function of the probability
		 * distribution.
		 */
		double threshold = PRNG.nextDouble() * total;

		double level = 0;
		Dragon result = NONE;
		for (Dragon dragon : values()) {
			if (level < threshold) {
				result = dragon;
			}

			level += dragon.probability;
		}

		return result;
	}

	/**
	 * A constructor with all fields as parameters.
	 * 
	 * @param probability
	 *            The probability this particular dragon to appear.
	 * @param strength
	 *            The dragon strength two-dimensional array. The first dimension
	 *            is the number of wilds used to trigger the dragon. The second
	 *            dimension is the response of the dragon as its strength.
	 */
	private Dragon(double probability, int strength[][]) {
		this.probability = probability;
		this.strength = strength;
	}

	/**
	 * Get dragon strength according to how many wilds triggered it.
	 * 
	 * @param wilds
	 *            The number of wilds which triggered the dragon.
	 * 
	 * @return A dragon strength.
	 */
	public int strength(int wilds) {
		int result = 0;

		/*
		 * The number of wilds should be in ascending order and the highest
		 * possible is found.
		 */
		for (int i = 0; i < strength[0].length && i < strength[1].length; i++) {
			if (strength[0][i] < wilds) {
				result = strength[1][i];
			}
		}

		return result;
	}
}
