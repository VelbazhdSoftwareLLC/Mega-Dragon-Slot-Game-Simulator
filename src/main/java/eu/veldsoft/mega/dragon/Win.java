package eu.veldsoft.mega.dragon;

/**
 * It is used for description of every single win.
 * 
 * @author Todor Balabanov
 */
final class Win {
	/** Bet in the run when the win was achieved. */
	private double bet;

	/** The achieved win. */
	private double win;

	/** Information for the cluster used for the winning achievement. */
	private Cluster cluster;

	/**
	 * Constructor with all fields as arguments.
	 * 
	 * @param bet     Bet in the run when the win was achieved.
	 * @param win     The achieved win.
	 * @param cluster Information for the cluster used for the winning achievement.
	 */
	public Win(double bet, double win, Cluster cluster) {
		this.bet = bet;
		this.win = win;
		this.cluster = cluster;
	}

	/**
	 * Bet in the run getter.
	 * 
	 * @return The bet amount.
	 */
	public double bet() {
		return bet;
	}

	/**
	 * Bet in the run setter.
	 * 
	 * @param bet The bet amount to set.
	 */
	public void bet(double bet) {
		this.bet = bet;
	}

	/**
	 * The win amount getter.
	 * 
	 * @return The win achieved.
	 */
	public double win() {
		return win;
	}

	/**
	 * The win amount setter.
	 * 
	 * @param win The win achieved to set.
	 */
	public void win(double win) {
		this.win = win;
	}

	/**
	 * Reference to the cluster associated to the win getter.
	 * 
	 * @return The cluster reference.
	 */
	public Cluster cluster() {
		return cluster;
	}

	/**
	 * Reference to the cluster associated to the win setter.
	 * 
	 * @param cluster The cluster reference to set.
	 */
	public void cluster(Cluster cluster) {
		this.cluster = cluster;
	}

	/**
	 * Represent the object content as a string.
	 */
	@Override
	public String toString() {
		return "[bet = " + bet + ", win = " + win + ", cluster = " + cluster + "]";
	}
}
