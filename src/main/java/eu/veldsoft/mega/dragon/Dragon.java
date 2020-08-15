package eu.veldsoft.mega.dragon;

import java.util.List;
import java.util.Random;

/**
 * Dragon behavior interface.
 * 
 * @author Todor Balabanov
 */
interface Behavior {
	/** Pseudo-random number generator instance. */
	static final Random PRNG = new Random();

	/**
	 * Dragon behavior is executed over game screen and it modifies it according to
	 * the dragon's strength.
	 * 
	 * @param view     Game screen.
	 * @param symbols  List of all possible symbols in the game.
	 * @param strength Dragon strength.
	 */
	void execute(Symbol[][] view, List<Symbol> symbols, int strength);
}

/**
 * Green dragon behavior interface.
 * 
 * @author Todor Balabanov
 */
final class GreenBehavior implements Behavior {
	/** The number of symbols placed by the dragon. */
	private int number = 0;

	/**
	 * A single dragon move for symbol replacement.
	 * 
	 * @param symbol Replacement symbol.
	 * @param view   Game screen.
	 * @param x      Current x position.
	 * @param y      Current y position.
	 */
	private void move(Symbol symbol, Symbol[][] view, int x, int y) {
		/* If there is no more strength do not put more symbols. */
		if (number <= 0) {
			return;
		}

		/* Borders checking should be done. */
		if (x < 0) {
			return;
		}
		if (y < 0) {
			return;
		}
		if (x >= view.length) {
			return;
		}
		if (y >= view[x].length) {
			return;
		}

		/* Transform only low paying symbols. */
		if (view[x][y].kind() == Symbol.Kind.LOW) {
			/* Place the same symbol. */
			view[x][y] = symbol;
			number--;
		}

		/* Take a random direction. */
		int dx = PRNG.nextInt(3) - 1;
		int dy = PRNG.nextInt(3) - 1;
		move(symbol, view, x - dx, y - dy);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(Symbol[][] view, List<Symbol> symbols, int strength) {
		int i = -1;
		int j = -1;

		/* Find a high paying symbol. */
		do {
			i = PRNG.nextInt(view.length);
			j = PRNG.nextInt(view[i].length);
		} while (view[i][j].kind() != Symbol.Kind.HIGH);

		/* Start of symbols replacement. */
		number = strength;
		move(view[i][j], view, i, j);
	}
}

/**
 * Gold dragon behavior interface.
 * 
 * @author Todor Balabanov
 */
final class GoldBehavior implements Behavior {
	/** Reference to the wild symbol in the game. */
	private static Symbol WILD = null;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(Symbol[][] view, List<Symbol> symbols, int strength) {
		/* Lazy initialization of the wild symbol reference. */
		if (WILD == null) {
			for (Symbol symbol : symbols) {
				if (symbol.kind() == Symbol.Kind.WILD) {
					WILD = symbol;
					break;
				}
			}
		}

		while (strength > 0) {
			int i = PRNG.nextInt(view.length);
			int j = PRNG.nextInt(view[i].length);

			/* Transform only low paying symbols. */
			if (view[i][j].kind() != Symbol.Kind.LOW) {
				continue;
			}

			view[i][j] = WILD;

			strength--;
		}
	}
}

/**
 * Red dragon behavior interface.
 * 
 * @author Todor Balabanov
 */
final class RedBehavior implements Behavior {
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(Symbol[][] view, List<Symbol> symbols, int strength) {
		int i = -1, di = 0;
		int j = -1, dj = 0;

		/* Find a high paying symbol. */
		do {
			i = PRNG.nextInt(view.length);
			j = PRNG.nextInt(view[i].length);
		} while (view[i][j].kind() != Symbol.Kind.HIGH);

		/* Select a random direction in which symbol to expand. */
		do {
			switch (PRNG.nextInt(4)) {
			case 0:
				di = -1;
				dj = -1;
				break;
			case 1:
				di = +1;
				dj = -1;
				break;
			case 2:
				di = -1;
				dj = +1;
				break;
			case 3:
				di = +1;
				dj = +1;
				break;
			}
		} while (i + di * strength < 0 || j + dj * strength < 0 || i + di * strength >= view.length
				|| j + dj * strength >= view[i].length);

		/* Expand the symbol. */
		for (int x = di * strength - 1; x != i; x += di) {
			for (int y = dj * strength - 1; y != j; y += dj) {
				view[x][y] = view[i][j];
			}
		}
	}
}

/**
 * Implements dragon bonus feature.
 * 
 * @author Todor Balabanov
 */
public enum Dragon {
	/**
	 * When the dragon is not presented this constant helps null pointer not to be
	 * used.
	 */
	NONE(0.0, new int[][] { {} }, null),

	/** Green dragon properties. */
	GREEN(0.5, new int[][] { { 1, 2, 3, 4, 5 }, { 3, 5, 7, 9, 11 } }, new GreenBehavior()),

	/** Gold dragon properties. */
	GOLD(0.3, new int[][] { { 1, 2, 3, 4, 5 }, { 3, 5, 7, 9, 11 } }, new GoldBehavior()),

	/** Red dragon properties. */
	RED(0.2, new int[][] { { 1, 2, 3 }, { 2, 3, 4 } }, new RedBehavior());

	/** Pseudo-random number generator instance. */
	private static final Random PRNG = new Random();

	/** Total cumulative probability for all the dragons. */
	private static double total = 0;

	/** The probability this particular dragon to appear. */
	private double probability = 0;

	/**
	 * Each dragon has different strength according to how many wilds triggered it
	 */
	private int strength[][] = new int[2][];

	/**
	 * A functional object for dragon behavior.
	 */
	private Behavior behavior = null;

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
		 * Stop level in the cumulative function of the probability distribution.
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
	 * @param probability The probability this particular dragon to appear.
	 * @param strength    The dragon strength two-dimensional array. The first
	 *                    dimension is the number of wilds used to trigger the
	 *                    dragon. The second dimension is the response of the dragon
	 *                    as its strength.
	 * @param behavior    A functional object for dragon behavior.
	 */
	private Dragon(double probability, int strength[][], Behavior behavior) {
		this.probability = probability;
		this.strength = strength;
		this.behavior = behavior;
	}

	/**
	 * Get dragon strength according to how many wilds triggered it.
	 * 
	 * @param wilds The number of wilds which triggered the dragon.
	 * 
	 * @return A dragon strength.
	 */
	public int strength(int wilds) {
		int result = 0;

		/*
		 * The number of wilds should be in ascending order and the highest possible is
		 * found.
		 */
		for (int i = 0; i < strength[0].length && i < strength[1].length; i++) {
			if (strength[0][i] < wilds) {
				result = strength[1][i];
			}
		}

		return result;
	}

	/**
	 * Dragon behavior is executed over game screen and it modifies it according to
	 * the dragon's strength.
	 * 
	 * @param view     Game screen.
	 * @param symbols  List of all possible symbols in the game.
	 * @param strength Dragon strength.
	 */
	public void execute(Symbol[][] view, List<Symbol> symbols, int strength) {
		behavior.execute(view, symbols, strength);
	}
}
