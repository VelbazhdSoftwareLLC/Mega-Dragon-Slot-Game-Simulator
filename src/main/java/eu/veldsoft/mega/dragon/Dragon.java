package eu.veldsoft.mega.dragon;

/**
 * Implements dragon bonus feature.
 * 
 * @author Todor Balabanov
 */
public enum Dragon {
	NONE(0.0), GREEN(0.5), GOLD(0.3), RED(0.2);

	private double probability = 0;

	private Dragon(double probability) {
		this.probability = probability;
	}
}
