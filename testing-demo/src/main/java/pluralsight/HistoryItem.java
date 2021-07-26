package pluralsight;

/**
 * DTO
 * @author falcon
 *
 */
public class HistoryItem {
	private final int id;
	private final int amount;
	private final String operation;
	private final int total;

	public HistoryItem(int id, int amount, String operation, int total) {
		this.id = id;
		this.amount = amount;
		this.operation = operation;
		this.total = total;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}
}
