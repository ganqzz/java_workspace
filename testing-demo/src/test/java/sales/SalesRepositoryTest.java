package sales;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class SalesRepositoryTest {

	@Before
	public void setUp() throws Exception {}

	@Test
	public void shouldLoadSampleData() {
		SalesRepository repository = new SalesRepository("resources/example-sales.csv");

		List<Sale> sales = repository.loadSales();

		// London, 2, 30
		assertThat(sales, hasItem(allOf(
				hasProperty("store", equalTo("London")),
				hasProperty("number", equalTo(2)),
				hasProperty("pricePerItem", equalTo(30))
		)));
	}
}
