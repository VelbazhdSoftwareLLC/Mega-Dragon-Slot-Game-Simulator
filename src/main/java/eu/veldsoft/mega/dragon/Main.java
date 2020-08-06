package eu.veldsoft.mega.dragon;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Application entry point class.
 * 
 * @author Todor Balabanov
 */
public class Main {
	/** Pseudo-random number generator instance. */
	private static final Random PRNG = new Random();

	/** List of all possible symbols in the game. */
	private static final List<Symbol> SYMBOLS = new ArrayList<Symbol>();

	/** A number of rows on the screen. */
	private static final int NUMBER_OF_ROWS = 8;

	/** A number of columns on the screen. */
	private static final int NUMBER_OF_COLUMNS = 7;

	/** Array with symbols references as virtual game reels. */
	private static final Symbol REELS[][] = new Symbol[NUMBER_OF_COLUMNS][];

	/** Reference to missing symbol object. */
	private static final Symbol NONE;

	/** Reference to wild symbol object. */
	private static final Symbol WILD;

	/** Static members initialization. */
	static {
		Symbol symbol = null;

		NONE = symbol = new Symbol();
		symbol.id(0);
		symbol.name("NONE");
		symbol.kind(Symbol.Kind.NONE);
		SYMBOLS.add(symbol);

		symbol = new Symbol();
		symbol.id(1);
		symbol.name("LOW01");
		symbol.kind(Symbol.Kind.LOW);
		symbol.pays().put(5, 0.1D);
		symbol.pays().put(9, 0.8D);
		symbol.pays().put(12, 1.2D);
		symbol.pays().put(15, 3D);
		symbol.pays().put(18, 6D);
		symbol.pays().put(20, 15D);
		symbol.pays().put(22, 30D);
		symbol.pays().put(25, 60D);
		SYMBOLS.add(symbol);

		symbol = new Symbol();
		symbol.id(2);
		symbol.name("LOW02");
		symbol.kind(Symbol.Kind.LOW);
		symbol.pays().put(5, 0.1D);
		symbol.pays().put(9, 1D);
		symbol.pays().put(12, 2D);
		symbol.pays().put(15, 4D);
		symbol.pays().put(18, 8D);
		symbol.pays().put(20, 18D);
		symbol.pays().put(22, 35D);
		symbol.pays().put(25, 88D);
		SYMBOLS.add(symbol);

		symbol = new Symbol();
		symbol.id(3);
		symbol.name("LOW03");
		symbol.kind(Symbol.Kind.LOW);
		symbol.pays().put(5, 0.2D);
		symbol.pays().put(9, 1.2D);
		symbol.pays().put(12, 2.4D);
		symbol.pays().put(15, 5D);
		symbol.pays().put(18, 10D);
		symbol.pays().put(20, 20D);
		symbol.pays().put(22, 40D);
		symbol.pays().put(25, 100D);
		SYMBOLS.add(symbol);

		symbol = new Symbol();
		symbol.id(4);
		symbol.name("LOW04");
		symbol.kind(Symbol.Kind.LOW);
		symbol.pays().put(5, 0.2D);
		symbol.pays().put(9, 1.5D);
		symbol.pays().put(12, 3D);
		symbol.pays().put(15, 6D);
		symbol.pays().put(18, 12D);
		symbol.pays().put(20, 27D);
		symbol.pays().put(22, 50D);
		symbol.pays().put(25, 120D);
		SYMBOLS.add(symbol);

		symbol = new Symbol();
		symbol.id(5);
		symbol.name("LOW05");
		symbol.kind(Symbol.Kind.LOW);
		symbol.pays().put(5, 0.3D);
		symbol.pays().put(9, 2D);
		symbol.pays().put(12, 4D);
		symbol.pays().put(15, 8D);
		symbol.pays().put(18, 15D);
		symbol.pays().put(20, 30D);
		symbol.pays().put(22, 60D);
		symbol.pays().put(25, 150D);
		SYMBOLS.add(symbol);

		symbol = new Symbol();
		symbol.id(6);
		symbol.name("HIGH06");
		symbol.kind(Symbol.Kind.HIGH);
		symbol.pays().put(5, 0.5D);
		symbol.pays().put(9, 3D);
		symbol.pays().put(12, 6D);
		symbol.pays().put(15, 12D);
		symbol.pays().put(18, 25D);
		symbol.pays().put(20, 50D);
		symbol.pays().put(22, 100D);
		symbol.pays().put(25, 200D);
		SYMBOLS.add(symbol);

		symbol = new Symbol();
		symbol.id(7);
		symbol.name("HIGH07");
		symbol.kind(Symbol.Kind.HIGH);
		symbol.pays().put(5, 0.6D);
		symbol.pays().put(9, 4D);
		symbol.pays().put(12, 8D);
		symbol.pays().put(15, 16D);
		symbol.pays().put(18, 30D);
		symbol.pays().put(20, 60D);
		symbol.pays().put(22, 128D);
		symbol.pays().put(25, 288D);
		SYMBOLS.add(symbol);

		symbol = new Symbol();
		symbol.id(8);
		symbol.name("HIGH08");
		symbol.kind(Symbol.Kind.HIGH);
		symbol.pays().put(5, 0.7D);
		symbol.pays().put(9, 5D);
		symbol.pays().put(12, 10D);
		symbol.pays().put(15, 20D);
		symbol.pays().put(18, 40D);
		symbol.pays().put(20, 88D);
		symbol.pays().put(22, 188D);
		symbol.pays().put(25, 388D);
		SYMBOLS.add(symbol);

		symbol = new Symbol();
		symbol.id(9);
		symbol.name("HIGH09");
		symbol.kind(Symbol.Kind.HIGH);
		symbol.pays().put(5, 1D);
		symbol.pays().put(9, 8D);
		symbol.pays().put(12, 20D);
		symbol.pays().put(15, 35D);
		symbol.pays().put(18, 70D);
		symbol.pays().put(20, 188D);
		symbol.pays().put(22, 388D);
		symbol.pays().put(25, 888D);
		SYMBOLS.add(symbol);

		WILD = symbol = new Symbol();
		symbol.id(10);
		symbol.name("WILD");
		symbol.kind(Symbol.Kind.WILD);
		SYMBOLS.add(symbol);

		REELS[0] = new Symbol[]{SYMBOLS.get(9), SYMBOLS.get(1), SYMBOLS.get(2),
				SYMBOLS.get(3), SYMBOLS.get(4), SYMBOLS.get(5), SYMBOLS.get(6),
				SYMBOLS.get(7), SYMBOLS.get(8), SYMBOLS.get(8), SYMBOLS.get(8),
				SYMBOLS.get(8), SYMBOLS.get(8)};
		REELS[1] = new Symbol[]{SYMBOLS.get(9), SYMBOLS.get(1), SYMBOLS.get(2),
				SYMBOLS.get(3), SYMBOLS.get(4), SYMBOLS.get(5), SYMBOLS.get(6),
				SYMBOLS.get(7), SYMBOLS.get(8), SYMBOLS.get(8), SYMBOLS.get(8),
				SYMBOLS.get(8), SYMBOLS.get(8)};
		REELS[2] = new Symbol[]{SYMBOLS.get(9), SYMBOLS.get(1), SYMBOLS.get(2),
				SYMBOLS.get(3), SYMBOLS.get(4), SYMBOLS.get(5), SYMBOLS.get(6),
				SYMBOLS.get(7), SYMBOLS.get(8), SYMBOLS.get(8), SYMBOLS.get(8),
				SYMBOLS.get(8), SYMBOLS.get(8)};
		REELS[3] = new Symbol[]{SYMBOLS.get(9), SYMBOLS.get(1), SYMBOLS.get(2),
				SYMBOLS.get(3), SYMBOLS.get(4), SYMBOLS.get(5), SYMBOLS.get(6),
				SYMBOLS.get(7), SYMBOLS.get(8), SYMBOLS.get(8), SYMBOLS.get(8),
				SYMBOLS.get(8), SYMBOLS.get(8)};
		REELS[4] = new Symbol[]{SYMBOLS.get(9), SYMBOLS.get(1), SYMBOLS.get(2),
				SYMBOLS.get(3), SYMBOLS.get(4), SYMBOLS.get(5), SYMBOLS.get(6),
				SYMBOLS.get(7), SYMBOLS.get(8), SYMBOLS.get(8), SYMBOLS.get(8),
				SYMBOLS.get(8), SYMBOLS.get(8)};
		REELS[5] = new Symbol[]{SYMBOLS.get(9), SYMBOLS.get(1), SYMBOLS.get(2),
				SYMBOLS.get(3), SYMBOLS.get(4), SYMBOLS.get(5), SYMBOLS.get(6),
				SYMBOLS.get(7), SYMBOLS.get(8), SYMBOLS.get(8), SYMBOLS.get(8),
				SYMBOLS.get(8), SYMBOLS.get(8)};
		REELS[6] = new Symbol[]{SYMBOLS.get(9), SYMBOLS.get(1), SYMBOLS.get(2),
				SYMBOLS.get(3), SYMBOLS.get(4), SYMBOLS.get(5), SYMBOLS.get(6),
				SYMBOLS.get(7), SYMBOLS.get(8), SYMBOLS.get(8), SYMBOLS.get(8),
				SYMBOLS.get(8), SYMBOLS.get(8)};
	}

	/** Visible screen with the symbols. */
	private static Symbol view[][] = new Symbol[NUMBER_OF_COLUMNS][NUMBER_OF_ROWS];

	/** Clusters bit mask by id of the symbol. */
	private static int bitmask[][] = new int[NUMBER_OF_COLUMNS][NUMBER_OF_ROWS];

	private static List<Cluster> clusters = null;

	/** Current stops on the reels. */
	private static int stops[] = new int[NUMBER_OF_COLUMNS];

	/** Total bet for a single game run. */
	private static double totalBet = 1;

	/** Total win for a single game run. */
	private static double totalWin = 0;

	/**
	 * Single reels spin to fill the view with symbols.
	 *
	 * @param view
	 *            Screen with symbols reference.
	 * @param reels
	 *            Reels strips reference.
	 * @param stops
	 *            Indices of the reels stops.
	 */
	private static void spin(Symbol[][] view, Symbol[][] reels, int stops[]) {
		/* Loop over each reel. */
		for (int i = 0; i < view.length && i < reels.length; i++) {
			/* Select random stop position. */
			int r = stops[i] = PRNG.nextInt(reels[i].length);

			/* Fill the other positions. */
			for (int j = 0; j < view[i].length; j++) {
				view[i][j] = reels[i][(r + j) % reels[i].length];
			}
		}
	}
	/**
	 * Single reels additional fill the view with symbols.
	 *
	 * @param view
	 *            Screen with symbols reference.
	 * @param reels
	 *            Reels strips reference.
	 * @param stops
	 *            Indices of the reels stops.
	 */
	private static void respin(Symbol[][] view, Symbol[][] reels, int stops[]) {
		/* Loop over each reel. */
		for (int i = 0; i < view.length && i < reels.length; i++) {
			/* Fill missing symbols. */
			for (int j = view[i].length - 1; j >= 0; j--) {
				/* If the cell is full do nothing. */
				if (view[i][j] != null) {
					continue;
				}

				/* Get index of the symbol on the reel. */
				int r = stops[i];
				stops[i]--;
				if (stops[i] < 0) {
					stops[i] = reels[i].length - 1;
				}

				/* Refill the screen. */
				view[i][j] = reels[i][r];
			}
		}
	}

	/**
	 * Recursive procedure for clusters identification.
	 * 
	 * @param clusters
	 *            Output matrix with markings.
	 * @param view
	 *            Game screen with symbols.
	 * @param x
	 *            Coordinates of the central cell.
	 * @param y
	 *            Coordinates of the central cell.
	 * @param symbol
	 *            The symbol of the cluster.
	 * @param coordinates
	 *            List of coordinates for the cells which are part of the
	 *            cluster.
	 * 
	 * @return Count of symbols part of the cluster.
	 */
	private static int mark(int[][] clusters, Symbol[][] view, int x, int y,
			Symbol symbol, List<SimpleEntry<Integer, Integer>> coordinates) {
		if (clusters == null) {
			return 0;
		}

		if (view == null) {
			return 0;
		}

		/* Borders checking should be done. */
		if (x < 0) {
			return 0;
		}
		if (y < 0) {
			return 0;
		}
		if (x >= clusters.length) {
			return 0;
		}
		if (x >= view.length) {
			return 0;
		}
		if (y >= clusters[x].length) {
			return 0;
		}
		if (y >= view[x].length) {
			return 0;
		}

		/* Cells without symbols should not be checked. */
		if (view[x][y] == null) {
			return 0;
		}

		/*
		 * If the symbol is not same as the cluster's one or wild do not handle
		 * it.
		 */
		if (view[x][y].id() != symbol.id()
				&& view[x][y].kind() != Symbol.Kind.WILD) {
			return 0;
		}

		/* If the cell is already part of another cluster do not handle it. */
		if (clusters[x][y] != 0) {
			return 0;
		}

		/* Mark as part of a cluster and investigate neighbors. */
		clusters[x][y] = symbol.id();

		/* Keep track of cell's coordinates. */
		if (view[x][y].kind() != Symbol.Kind.WILD) {
			coordinates.add(new SimpleEntry<Integer, Integer>(x, y));
		}

		/* Calculate neighbors. */
		return 1 + mark(clusters, view, x + 1, y, symbol, coordinates)
				+ mark(clusters, view, x - 1, y, symbol, coordinates)
				+ mark(clusters, view, x, y + 1, symbol, coordinates)
				+ mark(clusters, view, x, y - 1, symbol, coordinates);
	}

	/**
	 * Calculate center of the cluster.
	 * 
	 * @param coordinates
	 *            List of cluster cells coordinates.
	 * 
	 * @return Array with two numbers for x and y coordinates.
	 */
	private static int[] center(
			List<SimpleEntry<Integer, Integer>> coordinates) {
		int center[] = {-1, -1};

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
			if (distance < min) {
				center[0] = a.getKey();
				center[1] = a.getValue();

				min = distance;
			}
		}

		return center;
	}

	/**
	 * Mark clusters with different numbers. If there is no cluster in cell zero
	 * is written.
	 * 
	 * @param clusters
	 *            Output matrix with markings.
	 * @param view
	 *            Game screen with symbols.
	 * 
	 * @return Clusters information as symbol and count of occurrences.
	 */
	private static List<Cluster> mark(int clusters[][], Symbol[][] view) {
		/* Clear cluster flags. */
		for (int i = 0; i < clusters.length; i++) {
			for (int j = 0; j < clusters[i].length; j++) {
				clusters[i][j] = 0;
			}
		}

		List<Cluster> result = new ArrayList<Cluster>();

		for (int i = 0; i < view.length; i++) {
			for (int j = 0; j < view[i].length; j++) {
				/*
				 * It is possible view to has null pointers. If the cell is
				 * marked as already part of a cluster it is not investigated
				 * anymore.
				 */
				if (view[i][j] == null || clusters[i][j] != 0) {
					continue;
				}

				/*
				 * The wild symbol is not allowed to be part of its own cluster.
				 */
				if (view[i][j].kind() == Symbol.Kind.WILD) {
					continue;
				}

				/* Mark as part of a cluster and investigate neighbors. */
				clusters[i][j] = view[i][j].id();
				List<AbstractMap.SimpleEntry<Integer, Integer>> coordinates = new ArrayList<SimpleEntry<Integer, Integer>>();
				coordinates.add(new SimpleEntry<Integer, Integer>(i, j));

				/* Calculate the size of the cluster. */
				int count = 1
						+ mark(clusters, view, i + 1, j, view[i][j],
								coordinates)
						+ mark(clusters, view, i - 1, j, view[i][j],
								coordinates)
						+ mark(clusters, view, i, j + 1, view[i][j],
								coordinates)
						+ mark(clusters, view, i, j - 1, view[i][j],
								coordinates);

				/* Calculate the center of the cluster. */
				int center[] = {i, j};
				if (count > 1) {
					center = center(coordinates);
				}

				/* Keep track of the information for the found cluster. */
				result.add(new Cluster(view[i][j], center[0], center[1], count,
						coordinates));
			}
		}

		return result;
	}

	/**
	 * Remove a cluster from the screen.
	 * 
	 * @param cluster
	 *            The cluster to be removed.
	 * @param view
	 *            Game screen model reference.
	 */
	private static void remove(Cluster cluster, Symbol[][] view) {
		for (SimpleEntry<Integer, Integer> cell : cluster.coordinates()) {
			/* Wilds are not removed. */
			if (view[cell.getKey()][cell.getValue()]
					.kind() == Symbol.Kind.WILD) {
				continue;
			}

			// TODO Null pointers are not a good idea. It is much better symbol
			// with NONE kind to be used.
			view[cell.getKey()][cell.getValue()] = null;
		}
	}

	/**
	 * Collect win.
	 * 
	 * @param bet
	 *            Total bet in the game to be multiplied with the coefficient
	 *            for each cluster.
	 * @param view
	 *            Game screen with symbols.
	 * @param bitmask
	 *            The topology of the clusters.
	 * @param clusters
	 *            List of clusters information.
	 */
	private static List<Win> collect(double bet, Symbol[][] view,
			int[][] bitmask, List<Cluster> clusters) {
		List<Win> result = new ArrayList<Win>();

		/* Collect each cluster separately. */
		for (Cluster info : clusters) {
			double win = bet * info.symbol.multiplier(info.count());

			if (win > 0) {
				/* Track only a positive win. */
				result.add(new Win(bet, win, info));

				/* Remove cluster but keep wilds. */
				remove(info, view);

				/*
				 * High paying symbols generate wild in the center of the
				 * winning cluster.
				 */
				if (info.symbol().kind() == Symbol.Kind.HIGH) {
					view[info.x()][info.y()] = WILD;
				}
			}
		}

		return result;
	}

	/**
	 * Pack screen after clusters removal.
	 * 
	 * @param view
	 *            Game screen with symbols.
	 */
	private static void pack(Symbol[][] view) {
		/* Do the packing column by column. */
		for (int i = 0; i < view.length; i++) {
			boolean done = true;

			for (int j = 0; j < view[i].length - 1; j++) {
				/* If the cell below is not empty do nothing. */
				if (view[i][j + 1] != null) {
					continue;
				}

				/* There is no need to swap two missing symbols. */
				if (view[i][j] == null) {
					continue;
				}

				done = false;
				Symbol symbol = view[i][j + 1];
				view[i][j + 1] = view[i][j];
				view[i][j] = symbol;
			}

			/* Stay on the same column if packing is not finished. */
			if (done == false) {
				i--;
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
		spin(view, REELS, stops);
		List<Win> paid = collect(totalBet, view, bitmask,
				clusters = mark(bitmask, view));
		pack(view);
		respin(view, REELS, stops);

//		System.out.println(SYMBOLS);
//		System.out.println(Arrays.deepToString(REELS).replace("[[", "").replace("]]", "")
//				.replace("],", "\n").replace(" [", "").replace(",", "\t"));

//		System.out.println();
//		System.out.println(Arrays.deepToString(view).replace("[[", "")
//				.replace("]]", "").replace("],", "\n").replace(" [", "")
//				.replace(",", "\t"));

//		System.out.println();
//		System.out.println(clusters.toString().replace("[", "").replace("]", "")
//				.replace(", ", "\n" + "").replace(" ", "\t"));

//		System.out.println();
//		System.out.println(Arrays.deepToString(bitmask).replace("[[", "")
//				.replace("]]", "").replace("],", "\n").replace(" [", "")
//				.replace(",", "\t"));

//		System.out.println();
//		System.out.println(paid.toString().replace("[[", "").replace("]]", "")
//				.replace("],", "\n").replace(" [", "").replace(",", "\t"));
	}
}
