import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Application entry point class.
 * 
 * @author Todor Balabanov
 */
public class Main {
	/**
	 * Game symbol class representation.
	 */
	private static final class Symbol {
		/** Name of the symbol. */
		String name = "";

		/**
		 * Paytable of a single symbol. The information is given a list of
		 * values. The key is the lower limit of symbol count when the value is
		 * the payout coefficient according to original game rules.
		 */
		Map<Integer, Double> pays = new HashMap<Integer, Double>();

		/**
		 * Represent the object content as a string.
		 */
		@Override
		public String toString() {
			return name;
		}
	}

	/** Pseudo-random number generator instance. */
	private static final Random PRNG = new Random();

	/** List of all possible symbols in the game. */
	private static final List<Symbol> SYMBOLS = new ArrayList<Symbol>();

	/** A number of rows on the screen. */
	private static final int NUMBER_OF_ROWS = 7;

	/** A number of columns on the screen. */
	private static final int NUMBER_OF_COLUMNS = 8;

	/** Array with symbols references as virtual game reels. */
	private static final Symbol REELS[][] = new Symbol[NUMBER_OF_COLUMNS][];

	/** Static members initialization. */
	static {
		Symbol symbol = null;

		symbol = new Symbol();
		symbol.name = "LOW01";
		symbol.pays.put(5, 0.1D);
		symbol.pays.put(9, 0.8D);
		symbol.pays.put(12, 1.2D);
		symbol.pays.put(15, 3D);
		symbol.pays.put(18, 6D);
		symbol.pays.put(20, 15D);
		symbol.pays.put(22, 30D);
		symbol.pays.put(25, 60D);
		SYMBOLS.add(symbol);

		symbol = new Symbol();
		symbol.name = "LOW02";
		symbol.pays.put(5, 0.1D);
		symbol.pays.put(9, 1D);
		symbol.pays.put(12, 2D);
		symbol.pays.put(15, 4D);
		symbol.pays.put(18, 8D);
		symbol.pays.put(20, 18D);
		symbol.pays.put(22, 35D);
		symbol.pays.put(25, 88D);
		SYMBOLS.add(symbol);

		symbol = new Symbol();
		symbol.name = "LOW03";
		symbol.pays.put(5, 0.2D);
		symbol.pays.put(9, 1.2D);
		symbol.pays.put(12, 2.4D);
		symbol.pays.put(15, 5D);
		symbol.pays.put(18, 10D);
		symbol.pays.put(20, 20D);
		symbol.pays.put(22, 40D);
		symbol.pays.put(25, 100D);
		SYMBOLS.add(symbol);

		symbol = new Symbol();
		symbol.name = "LOW04";
		symbol.pays.put(5, 0.2D);
		symbol.pays.put(9, 1.5D);
		symbol.pays.put(12, 3D);
		symbol.pays.put(15, 6D);
		symbol.pays.put(18, 12D);
		symbol.pays.put(20, 27D);
		symbol.pays.put(22, 50D);
		symbol.pays.put(25, 120D);
		SYMBOLS.add(symbol);

		symbol = new Symbol();
		symbol.name = "LOW05";
		symbol.pays.put(5, 0.3D);
		symbol.pays.put(9, 2D);
		symbol.pays.put(12, 4D);
		symbol.pays.put(15, 8D);
		symbol.pays.put(18, 15D);
		symbol.pays.put(20, 30D);
		symbol.pays.put(22, 60D);
		symbol.pays.put(25, 150D);
		SYMBOLS.add(symbol);

		symbol = new Symbol();
		symbol.name = "HIGH06";
		symbol.pays.put(5, 0.5D);
		symbol.pays.put(9, 3D);
		symbol.pays.put(12, 6D);
		symbol.pays.put(15, 12D);
		symbol.pays.put(18, 25D);
		symbol.pays.put(20, 50D);
		symbol.pays.put(22, 100D);
		symbol.pays.put(25, 200D);
		SYMBOLS.add(symbol);

		symbol = new Symbol();
		symbol.name = "HIGH07";
		symbol.pays.put(5, 0.6D);
		symbol.pays.put(9, 4D);
		symbol.pays.put(12, 8D);
		symbol.pays.put(15, 16D);
		symbol.pays.put(18, 30D);
		symbol.pays.put(20, 60D);
		symbol.pays.put(22, 128D);
		symbol.pays.put(25, 288D);
		SYMBOLS.add(symbol);

		symbol = new Symbol();
		symbol.name = "HIGH08";
		symbol.pays.put(5, 0.7D);
		symbol.pays.put(9, 5D);
		symbol.pays.put(12, 10D);
		symbol.pays.put(15, 20D);
		symbol.pays.put(18, 40D);
		symbol.pays.put(20, 88D);
		symbol.pays.put(22, 188D);
		symbol.pays.put(25, 388D);
		SYMBOLS.add(symbol);

		symbol = new Symbol();
		symbol.name = "HIGH09";
		symbol.pays.put(5, 1D);
		symbol.pays.put(9, 8D);
		symbol.pays.put(12, 20D);
		symbol.pays.put(15, 35D);
		symbol.pays.put(18, 70D);
		symbol.pays.put(20, 188D);
		symbol.pays.put(22, 388D);
		symbol.pays.put(25, 888D);
		SYMBOLS.add(symbol);

		symbol = new Symbol();
		symbol.name = "WILD";
		SYMBOLS.add(symbol);

		REELS[0] = new Symbol[]{SYMBOLS.get(0), SYMBOLS.get(1), SYMBOLS.get(2),
				SYMBOLS.get(3), SYMBOLS.get(4), SYMBOLS.get(5), SYMBOLS.get(6),
				SYMBOLS.get(7), SYMBOLS.get(8)};
		REELS[1] = new Symbol[]{SYMBOLS.get(0), SYMBOLS.get(1), SYMBOLS.get(2),
				SYMBOLS.get(3), SYMBOLS.get(4), SYMBOLS.get(5), SYMBOLS.get(6),
				SYMBOLS.get(7), SYMBOLS.get(8)};
		REELS[2] = new Symbol[]{SYMBOLS.get(0), SYMBOLS.get(1), SYMBOLS.get(2),
				SYMBOLS.get(3), SYMBOLS.get(4), SYMBOLS.get(5), SYMBOLS.get(6),
				SYMBOLS.get(7), SYMBOLS.get(8)};
		REELS[3] = new Symbol[]{SYMBOLS.get(0), SYMBOLS.get(1), SYMBOLS.get(2),
				SYMBOLS.get(3), SYMBOLS.get(4), SYMBOLS.get(5), SYMBOLS.get(6),
				SYMBOLS.get(7), SYMBOLS.get(8)};
		REELS[4] = new Symbol[]{SYMBOLS.get(0), SYMBOLS.get(1), SYMBOLS.get(2),
				SYMBOLS.get(3), SYMBOLS.get(4), SYMBOLS.get(5), SYMBOLS.get(6),
				SYMBOLS.get(7), SYMBOLS.get(8)};
		REELS[5] = new Symbol[]{SYMBOLS.get(0), SYMBOLS.get(1), SYMBOLS.get(2),
				SYMBOLS.get(3), SYMBOLS.get(4), SYMBOLS.get(5), SYMBOLS.get(6),
				SYMBOLS.get(7), SYMBOLS.get(8)};
		REELS[6] = new Symbol[]{SYMBOLS.get(0), SYMBOLS.get(1), SYMBOLS.get(2),
				SYMBOLS.get(3), SYMBOLS.get(4), SYMBOLS.get(5), SYMBOLS.get(6),
				SYMBOLS.get(7), SYMBOLS.get(8)};
		REELS[7] = new Symbol[]{SYMBOLS.get(0), SYMBOLS.get(1), SYMBOLS.get(2),
				SYMBOLS.get(3), SYMBOLS.get(4), SYMBOLS.get(5), SYMBOLS.get(6),
				SYMBOLS.get(7), SYMBOLS.get(8)};
	}

	/** Visible screen with the symbols. */
	private static Symbol view[][] = new Symbol[NUMBER_OF_COLUMNS][NUMBER_OF_ROWS];

	/**
	 * Single reels spin to fill the view with symbols.
	 *
	 * @param view
	 *            Screen with symbols reference.
	 * @param reels
	 *            Reels strips reference.
	 */
	private static void spin(Symbol[][] view, Symbol[][] reels) {
		/* Loop over each reel. */
		for (int i = 0; i < view.length && i < reels.length; i++) {
			/* Select random stop position. */
			int r = PRNG.nextInt(reels[i].length);

			/* Fill the other positions. */
			for (int j = 0; j < view[i].length; j++) {
				view[i][j] = reels[i][(r + j) % reels[i].length];
			}
		}
	}

	/**
	 * Application single entry point method.
	 * 
	 * @param args
	 *            Command line arguments.
	 */
	public static void main(String[] args) {
		// System.out.println(SYMBOLS);
		// System.out.println(Arrays.deepToString(REELS));
		
		spin(view, REELS);
		
		System.out.println(Arrays.deepToString(view));
	}
}
