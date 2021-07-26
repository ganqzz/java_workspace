package sales;

import java.util.Map;
import static java.util.stream.Collectors.*;

public class SalesAnalyser {

	private SalesRepository repo;

	public SalesAnalyser(final SalesRepository repo) {
		this.repo = repo;
	}

	public Map<String, Integer> tallyCitySales() {
		return repo.loadSales()
				.stream()
				.collect(groupingBy(Sale::getStore, summingInt(Sale::getValue)));
	}
}
