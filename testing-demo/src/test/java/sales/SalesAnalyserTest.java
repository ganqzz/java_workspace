package sales;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class SalesAnalyserTest {

	private static final List<Sale> exampleSales = Arrays.asList(
			new Sale("Cardiff", 10, 2),
			new Sale("Cardiff", 3, 5),
			new Sale("Cardiff", 6, 20),
			new Sale("London", 5, 7));

	private static final Map<String, Integer> expectedStoreSales = new HashMap<>();
	static {
		expectedStoreSales.put("Cardiff", 155);
		expectedStoreSales.put("London", 35);
	}

	@Before
	public void setUp() throws Exception {}

	@Test
	public void shouldAggregateSales() {
		// given
		SalesRepository mockRepository = mock(SalesRepository.class);
		when(mockRepository.loadSales()).thenReturn(exampleSales);

		SalesAnalyser analyser = new SalesAnalyser(mockRepository);

		// when
		Map<String, Integer> storesSales = analyser.tallyCitySales();

		// then
		assertEquals("Calculated wrong store sales", expectedStoreSales, storesSales);
		verify(mockRepository, times(1)).loadSales();
	}
}
