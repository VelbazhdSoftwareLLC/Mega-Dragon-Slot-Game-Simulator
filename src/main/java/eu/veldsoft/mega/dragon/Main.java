package eu.veldsoft.mega.dragon;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

	/** A total number of Monte-Carlo simulation game runs. */
	private static long TOTAL_RUNS = 10_000_000;

	/** Number of seconds for reporting. */
	private static long REPORT_PER_SECONDS = 1;

	/** List of all possible symbols in the game. */
	private static final List<Symbol> SYMBOLS = new ArrayList<Symbol>();

	/** Reference to missing symbol object. */
	private static final Symbol NONE;

	/** Reference to wild symbol object. */
	private static final Symbol WILD;

	/** A number of rows on the screen. */
	private static final int NUMBER_OF_ROWS = 8;

	/** A number of columns on the screen. */
	private static final int NUMBER_OF_COLUMNS = 7;

	/** Array with symbols references as virtual game reels. */
	private static final Symbol REELS[][] = new Symbol[NUMBER_OF_COLUMNS][];

	/** The text representation of the reels. */
	private static final String REELS_TEXT = "LOW03	LOW04	LOW01	LOW03	LOW02	LOW02	LOW03\n"
			+ "LOW04	LOW01	LOW02	LOW01	LOW01	LOW05	LOW01\n"
			+ "LOW03	LOW03	LOW04	HIGH08	LOW03	LOW01	LOW04\n"
			+ "LOW02	LOW05	LOW03	LOW02	LOW01	LOW02	LOW01\n"
			+ "LOW03	LOW01	LOW01	HIGH09	HIGH09	HIGH06	LOW02\n"
			+ "LOW01	LOW04	LOW02	HIGH07	LOW02	LOW03	LOW05\n"
			+ "LOW05	LOW02	LOW03	LOW01	LOW04	HIGH06	LOW04\n"
			+ "LOW02	LOW04	LOW02	LOW03	LOW02	LOW03	LOW03\n"
			+ "HIGH09	LOW02	LOW01	LOW01	LOW01	LOW02	LOW05\n"
			+ "LOW02	LOW01	LOW03	LOW02	HIGH07	LOW01	LOW04\n"
			+ "LOW04	LOW04	HIGH09	LOW05	LOW03	HIGH09	LOW03\n"
			+ "LOW01	LOW02	LOW02	LOW04	HIGH06	LOW01	HIGH06\n"
			+ "LOW05	LOW01	LOW01	LOW03	LOW03	LOW02	LOW04\n"
			+ "LOW01	HIGH08	LOW02	LOW01	LOW04	LOW05	LOW02\n"
			+ "LOW05	HIGH07	LOW05	LOW03	LOW03	LOW01	LOW01\n"
			+ "HIGH06	LOW04	HIGH06	LOW01	LOW01	LOW02	LOW05\n"
			+ "LOW02	LOW02	LOW03	HIGH08	LOW04	LOW01	LOW03\n"
			+ "LOW01	LOW01	LOW01	LOW03	LOW02	HIGH08	LOW05\n"
			+ "HIGH07	LOW05	LOW05	LOW02	HIGH06	LOW01	HIGH06\n"
			+ "LOW02	LOW02	LOW01	LOW01	LOW02	LOW02	LOW01\n"
			+ "LOW04	LOW05	LOW02	LOW02	LOW01	LOW05	LOW02\n"
			+ "LOW05	LOW04	LOW01	LOW03	LOW02	LOW03	LOW01\n"
			+ "LOW02	LOW03	HIGH07	LOW01	LOW01	LOW04	LOW02\n"
			+ "LOW01	LOW01	LOW01	LOW05	LOW03	LOW01	HIGH06\n"
			+ "LOW03	HIGH07	LOW04	HIGH06	LOW01	LOW03	HIGH07\n"
			+ "LOW04	LOW01	LOW02	LOW02	LOW02	LOW01	LOW03\n"
			+ "LOW03	LOW05	LOW01	LOW04	LOW05	LOW03	LOW02\n"
			+ "LOW01	LOW02	LOW04	HIGH07	LOW02	LOW04	LOW04\n"
			+ "LOW02	LOW04	LOW02	LOW05	LOW03	LOW01	LOW01\n"
			+ "LOW01	LOW01	LOW04	LOW01	HIGH07	LOW02	HIGH08\n"
			+ "LOW03	LOW03	LOW01	LOW05	LOW01	LOW04	LOW02\n"
			+ "LOW01	LOW01	HIGH07	LOW02	LOW04	LOW02	LOW01\n"
			+ "HIGH08	LOW03	HIGH06	LOW05	LOW03	LOW01	LOW03\n"
			+ "HIGH06	LOW04	LOW03	LOW01	LOW01	LOW04	LOW05\n"
			+ "HIGH08	LOW05	LOW04	LOW04	HIGH06	LOW01	LOW02\n"
			+ "LOW01	HIGH08	HIGH06	LOW02	LOW04	HIGH07	HIGH06\n"
			+ "HIGH07	LOW03	LOW05	HIGH06	LOW03	LOW04	LOW05\n"
			+ "LOW05	HIGH06	LOW01	LOW01	LOW01	LOW05	LOW01\n"
			+ "LOW02	LOW02	LOW04	LOW05	LOW02	LOW03	HIGH08\n"
			+ "LOW01	LOW03	LOW02	HIGH06	LOW01	LOW02	LOW03\n"
			+ "LOW04	LOW01	LOW03	LOW04	LOW04	LOW01	LOW01\n"
			+ "LOW01	HIGH09	LOW05	LOW03	LOW02	LOW03	HIGH07\n"
			+ "LOW03	LOW01	HIGH06	LOW01	LOW05	HIGH06	LOW02\n"
			+ "LOW02	LOW02	LOW03	LOW04	LOW03	HIGH07	LOW01\n"
			+ "LOW03	LOW01	LOW01	LOW03	LOW01	LOW02	LOW02\n"
			+ "LOW02	HIGH06	LOW04	LOW04	LOW05	HIGH08	LOW01\n"
			+ "LOW01	LOW01	LOW01	LOW02	HIGH08	LOW05	LOW03\n"
			+ "LOW04	LOW02	HIGH07	LOW01	HIGH07	LOW02	LOW02\n"
			+ "HIGH06	LOW03	HIGH08	LOW02	LOW04	LOW04	LOW04\n"
			+ "LOW04	LOW02	LOW04	LOW03	LOW01	HIGH07	LOW02\n"
			+ "LOW05	LOW03	LOW02	LOW02	LOW05	HIGH06	HIGH09\n"
			+ "LOW01	LOW05	LOW03	HIGH07	HIGH06	LOW01	LOW03\n"
			+ "HIGH07	LOW03	HIGH08	LOW04	LOW05	LOW05	LOW01\n"
			+ "HIGH06	LOW01	LOW05	LOW02	LOW01	LOW04	LOW04\n"
			+ "LOW03	HIGH06	LOW02	HIGH06	HIGH08	LOW03	LOW01\n"
			+ "LOW01	LOW02	LOW01	LOW01	LOW04	LOW01	LOW04\n"
			+ "LOW04	HIGH07	LOW05	LOW04	LOW02	LOW03	HIGH07\n"
			+ "LOW02	HIGH06	LOW03	LOW01	LOW05	LOW04	LOW01\n";

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

		/* Parse text of the reels. */
		List<String> values = new ArrayList<String>();
		for (String row : REELS_TEXT.split("\n")) {
			values.addAll(Arrays.asList(row.split("	")));
		}
		int length = values.size() / NUMBER_OF_COLUMNS;

		/* Fill reels structure. */
		for (int i = 0; i < NUMBER_OF_COLUMNS; i++) {
			REELS[i] = new Symbol[length];

			for (int j = 0; j < length; j++) {
				REELS[i][j] = null;
				for (Symbol value : SYMBOLS) {
					if (values.get(i + NUMBER_OF_COLUMNS * j)
							.equals(value.name()) == false) {
						continue;
					}

					REELS[i][j] = value;
					break;
				}
			}
		}
	}

	/** Visible screen with the symbols. */
	private static Symbol view[][] = new Symbol[NUMBER_OF_COLUMNS][NUMBER_OF_ROWS];

	/** Current stops on the reels. */
	private static int stops[] = new int[NUMBER_OF_COLUMNS];

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
	 * @param bitmask
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
	private static int mark(int[][] bitmask, Symbol[][] view, int x, int y,
			Symbol symbol, List<SimpleEntry<Integer, Integer>> coordinates) {
		if (bitmask == null) {
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
		if (x >= bitmask.length) {
			return 0;
		}
		if (x >= view.length) {
			return 0;
		}
		if (y >= bitmask[x].length) {
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
		if (bitmask[x][y] != -1) {
			return 0;
		}

		/* Mark as part of a cluster and investigate neighbors. */
		bitmask[x][y] = symbol.id();
		coordinates.add(new SimpleEntry<Integer, Integer>(x, y));

		/* Calculate neighbors. */
		return 1 + mark(bitmask, view, x + 1, y, symbol, coordinates)
				+ mark(bitmask, view, x - 1, y, symbol, coordinates)
				+ mark(bitmask, view, x, y + 1, symbol, coordinates)
				+ mark(bitmask, view, x, y - 1, symbol, coordinates);
	}

	/**
	 * Mark clusters with different numbers. If there is no cluster in cell zero
	 * is written.
	 * 
	 * @param view
	 *            Game screen with symbols.
	 * 
	 * @return Clusters information as symbol and count of occurrences.
	 */
	private static List<Cluster> mark(Symbol[][] view) {
		/* List of clusters informatio. */
		List<Cluster> result = new ArrayList<Cluster>();

		/* Clusters bit mask by id of the symbol. */
		int bitmask[][] = new int[view.length][];
		for (int i = 0; i < bitmask.length; i++) {
			bitmask[i] = new int[view[i].length];
			for (int j = 0; j < bitmask[i].length; j++) {
				bitmask[i][j] = -1;
			}
		}

		for (int i = 0; i < view.length; i++) {
			for (int j = 0; j < view[i].length; j++) {
				/*
				 * It is possible view to has null pointers.
				 */
				if (view[i][j] == null) {
					continue;
				}

				/*
				 * Clear cluster flags for wilds because they need to
				 * participate in other clusters.
				 */
				for (int k = 0; k < bitmask.length; k++) {
					for (int l = 0; l < bitmask[k].length; l++) {
						if (view[k][l].kind() == Symbol.Kind.WILD) {
							bitmask[k][l] = -1;
						}
					}
				}

				/* Mark as part of a cluster and investigate neighbors. */
				bitmask[i][j] = view[i][j].id();
				List<AbstractMap.SimpleEntry<Integer, Integer>> coordinates = new ArrayList<SimpleEntry<Integer, Integer>>();
				coordinates.add(new SimpleEntry<Integer, Integer>(i, j));

				/* Calculate the size of the cluster. */
				int count = 1
						+ mark(bitmask, view, i + 1, j, view[i][j], coordinates)
						+ mark(bitmask, view, i - 1, j, view[i][j], coordinates)
						+ mark(bitmask, view, i, j + 1, view[i][j], coordinates)
						+ mark(bitmask, view, i, j - 1, view[i][j],
								coordinates);

				if (count > 1) {
					/* Keep track of the information for the found cluster. */
					Cluster cluster = new Cluster(view[i][j], i, j, count,
							coordinates);
					if (result.contains(cluster) == false) {
						result.add(cluster);
					}
				}
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
	 * @param wilds
	 *            A cluster of wilds flag.
	 */
	private static void remove(Cluster cluster, Symbol[][] view,
			boolean wilds) {
		for (SimpleEntry<Integer, Integer> cell : cluster.coordinates()) {
			/* Do not handle empty cells. */
			if (view[cell.getKey()][cell.getValue()] == null) {
				continue;
			}

			/* Wilds are not removed when the flag is low. */
			if (view[cell.getKey()][cell.getValue()].kind() == Symbol.Kind.WILD
					&& wilds == false) {
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
	 * @param clusters
	 *            List of clusters information.
	 */
	private static List<Win> collect(double bet, Symbol[][] view,
			List<Cluster> clusters) {
		List<Win> result = new ArrayList<Win>();

		/* Collect each cluster separately. */
		for (Cluster cluster : clusters) {
			double win = bet * cluster.symbol().multiplier(cluster.count());

			if (win > 0) {
				/* Track only a positive win. */
				result.add(new Win(bet, win, cluster));

				/* Remove cluster but keep wilds. */
				remove(cluster, view, false);

				/*
				 * High paying symbols generate wild(s) in the space of the
				 * winning cluster.
				 */
				if (cluster.symbol().kind() == Symbol.Kind.HIGH) {
					for (SimpleEntry<Integer, Integer> coordinate : cluster
							.wilds()) {
						view[coordinate.getKey()][coordinate.getValue()] = WILD;
					}
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
	 * Manipulate the game screen according to dragons rules.
	 * 
	 * @param view
	 *            Game screen with symbols.
	 * @param clusters
	 *            List of clusters information.
	 * 
	 * @return True if dragons ran, false otherwise.
	 */
	private static boolean dragons(Symbol[][] view, List<Cluster> clusters) {
		boolean result = false;

		for (Cluster cluster : clusters) {
			/* If cluster is non wild cluster do nothing. */
			if (cluster.symbol().kind() != Symbol.Kind.WILD) {
				continue;
			}

			/* Execute dragon behavior. */
			Dragon dragon = Dragon.scramble();
			dragon.execute(view, SYMBOLS, cluster.count());

			/* Remove the cluster with wilds. */
			remove(cluster, view, true);

			/* Dragons were available. */
			result = true;
		}

		return result;
	}

	/**
	 * Shuffle single reel in groups according to given size for high and low
	 * symbols.
	 * 
	 * @param reel
	 *            Array with single reel.
	 * @param high
	 *            Size of the high symbols group.
	 * @param low
	 *            Size of the low symbols group.
	 */
	private static void shuffle(Symbol[] reel, int high, int low) {
		List<List<Symbol>> groups = new ArrayList<List<Symbol>>();

		/* Prepare group container. */
		List<Symbol> group = new ArrayList<Symbol>();

		Arrays.sort(reel);
		for (Symbol symbol : reel) {
			/* Start new group if the current one is full. */
			if (symbol.kind() == Symbol.Kind.HIGH && group.size() >= high) {
				groups.add(group);
				group = new ArrayList<Symbol>();
			}

			/* Start new group if the current one is full. */
			if (symbol.kind() == Symbol.Kind.LOW && group.size() >= low) {
				groups.add(group);
				group = new ArrayList<Symbol>();
			}

			/*
			 * If there is a start of a new group when the previous one is not
			 * finished, just start it.
			 */
			if (group.size() > 0 && symbol != group.get(0)) {
				groups.add(group);
				group = new ArrayList<Symbol>();
			}

			group.add(symbol);
		}

		/* Add an unadded group. */
		if (group.size() > 0) {
			groups.add(group);
		}

		/* Shuffle groups. */
		Collections.shuffle(groups);

		/* Extra shuffle for repeats. */
		boolean repeats = true;
		while (repeats == true) {
			repeats = false;

			for (int i = 0; i < groups.size(); i++) {
				/* If groups are different do nothing. */
				if (groups.get(i).get(0) != groups.get((i + 1) % groups.size())
						.get(0)) {
					continue;
				}

				/* Extra shuffle. */
				group = groups.get(i);
				groups.remove(i);
				groups.add(i + PRNG.nextInt(groups.size() - i), group);
				// groups.add(group);

				repeats = true;
			}
		}

		/*  */
		int i = 0;
		for (List<Symbol> list : groups) {
			for (Symbol symbol : list) {
				reel[i] = symbol;
				i++;
			}
		}
	}

	/**
	 * Shuffle reels in groups according to given size for high and low symbols.
	 * 
	 * @param reels
	 *            Array with symbols reels.
	 * @param high
	 *            Size of the high symbols group.
	 * @param low
	 *            Size of the low symbols group.
	 */
	private static void shuffle(Symbol[][] reels, int high, int low) {
		for (Symbol reel[] : reels) {
			shuffle(reel, high, low);
		}
	}

	/**
	 * Application single entry point method.
	 * 
	 * @param args
	 *            Command line arguments.
	 */
	public static void main(String[] args) {
		// System.err.println(SYMBOLS);

		shuffle(REELS, 2, 3);
		System.err.println(Arrays.deepToString(REELS).replace("[[", "")
				.replace("]]", "").replace("],", "\n").replace(" [", "")
				.replace(",", "\t").replace(" ", ""));

		double totalBet = 1;
		double totalWin = 0;

		double lostMoney = 0;
		double wonMoney = 0;

		long numberOfRuns = 0;

		long time = System.currentTimeMillis();
		for (numberOfRuns = 0; numberOfRuns < TOTAL_RUNS; numberOfRuns++) {
			/* Take a bet. */
			lostMoney += totalBet;

			/* Run the game in the base game spin. */
			spin(view, REELS, stops);
			totalWin = 0;

			/* Handle the results from the base game spin. */
			boolean bonus = false;
			do {
				/* Run a regular game. */
				List<Win> paid = null;
				List<Cluster> clusters = null;
				do {
					clusters = mark(view);
					paid = collect(totalBet, view, clusters);
					pack(view);
					respin(view, REELS, stops);

					/* Register wins. */
					for (Win won : paid) {
						wonMoney += won.win();
						totalWin += won.win();
					}
				} while (paid.size() > 0);

				/* Run the bonus feature by checking for dragons. */
				bonus = dragons(view, clusters);
				if (bonus == true) {
					pack(view);
					respin(view, REELS, stops);
				}
			} while (bonus == true);

			/* Report progress. */
			if (time + 1000 * REPORT_PER_SECONDS < System.currentTimeMillis()) {
				time = System.currentTimeMillis();

				System.out.print("[");
				System.out.print(String.format("%3d",
						(100 * numberOfRuns / TOTAL_RUNS)));
				System.out.print("% ]");
				System.out.print("\t");
				System.out.print(wonMoney / lostMoney);
				System.out.print("\t");
				System.out.print(wonMoney);
				System.out.print("\t");
				System.out.print(lostMoney);
				System.out.print("\n");
			}
		}

		System.out.print("\n");

		System.out.print("Total Number of Games:");
		System.out.print("\t");
		System.out.print(numberOfRuns);
		System.out.print("\n");

		System.out.print("\n");

		System.out.print("Total Won Money:");
		System.out.print("\t");
		System.out.print(String.format("%12.2f", wonMoney));
		System.out.print("\n");

		System.out.print("Total Lost Money:");
		System.out.print("\t");
		System.out.print(String.format("%12.2f", lostMoney));
		System.out.print("\n");

		System.out.print("\n");

		System.out.print("Return to Player:");
		System.out.print("\t");
		System.out.print(wonMoney / lostMoney);
		System.out.print("\n");

		// System.err.println();
		// System.err.println(Arrays.deepToString(view).replace("[[", "")
		// .replace("]]", "").replace("],", "\n").replace(" [", "")
		// .replace(",", "\t"));

		// System.err.println();
		// System.err.println(clusters.toString().replace("[", "").replace("]",
		// "")
		// .replace(", ", "\n" + "").replace(" ", "\t"));

		// System.err.println();
		// System.err.println(Arrays.deepToString(bitmask).replace("[[", "")
		// .replace("]]", "").replace("],", "\n").replace(" [", "")
		// .replace(",", "\t"));

		// System.err.println();
		// System.err.println(paid.toString().replace("[[", "").replace("]]",
		// "")
		// .replace("],", "\n").replace(" [", "").replace(",", "\t"));
	}
}
