package sales;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class SalesReportTest {

	private static final int TALLY = 235;
	private static final String CITY = "LONDON";

	PrintStream mockOut = mock(PrintStream.class);
	SalesAnalyser mockAnalyser = mock(SalesAnalyser.class);
	SalesReport report = new SalesReport(mockAnalyser, mockOut);

	@Before
	public void setUp() throws Exception {}

	@Test
	public void shouldPrintCityTallies() {
		Map<String, Integer> cityTallies = new HashMap<>();
		cityTallies.put(CITY, TALLY);
		when(mockAnalyser.tallyCitySales()).thenReturn(cityTallies);

		report.run();
		verify(mockOut).printf(anyString(), eq(CITY), eq(TALLY));
	}
}
