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
	 * @param bet
	 *            Bet in the run when the win was achieved.
	 * @param win
	 *            The achieved win.
	 * @param cluster
	 *            Information for the cluster used for the winning achievement.
	 */
	public Win(double bet, double win, Cluster cluster) {
		this.bet = bet;
		this.win = win;
		this.cluster = cluster;
	}
}
