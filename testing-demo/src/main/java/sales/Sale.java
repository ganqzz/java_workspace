package sales;

public final class Sale {

	private String store;
	private int number;
	private int pricePerItem;

	public Sale(final String store, final int number, final int pricePerItem) {
		this.store = store;
		this.number = number;
		this.pricePerItem = pricePerItem;
	}

	public String getStore() {
		return store;
	}

	public int getNumber() {
		return number;
	}

	public int getPricePerItem() {
		return pricePerItem;
	}

	public int getValue() {
		return number * pricePerItem;
	}

	@Override
	public String toString() {
		return "Sale [store=" + store
				+ ", number=" + number
				+ ", pricePerItem=" + pricePerItem
				+ "]";
	}
}
