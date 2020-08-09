package eu.veldsoft.mega.dragon;

import java.util.Map;
import java.util.HashMap;

/**
 * Implements dragon bonus feature.
 * 
 * @author Todor Balabanov
 */
public enum Dragon {
	NONE(0.0), GREEN(0.5), GOLD(0.3), RED(0.2);

	private static final Map<Integer, Integer> CLUSTER_SIZE_TO_AMOUNT = new HashMap<Integer, Integer>();

	private double probability = 0;

	/** Initialization of the static members. */
	static {
		CLUSTER_SIZE_TO_AMOUNT.put( 5, 1);
		CLUSTER_SIZE_TO_AMOUNT.put(10, 2);
		CLUSTER_SIZE_TO_AMOUNT.put(15, 3);
		CLUSTER_SIZE_TO_AMOUNT.put(20, 4);
		CLUSTER_SIZE_TO_AMOUNT.put(25, 5);
		CLUSTER_SIZE_TO_AMOUNT.put(30, 6);
		CLUSTER_SIZE_TO_AMOUNT.put(35, 7);
		CLUSTER_SIZE_TO_AMOUNT.put(40, 8);
		CLUSTER_SIZE_TO_AMOUNT.put(45, 9);
		CLUSTER_SIZE_TO_AMOUNT.put(50, 10);
		CLUSTER_SIZE_TO_AMOUNT.put(55, 11);
	}

	public static int amount(int size) {
		int result = 0;

		for(Map.Entry<Integer, Integer> entry : CLUSTER_SIZE_TO_AMOUNT.entrySet()) {
			/* If the cluster is smaller than the table size value skip it. */
			if(size < entry.getKey()) {
				continue;
			}

			/* If the amount is already bigger than the table value skip it. */
			if(result > entry.getValue()) {
				continue;
			}

			result = entry.getValue();
		}

		return result;
	}

	private Dragon(double probability) {
		this.probability = probability;
	}
}
