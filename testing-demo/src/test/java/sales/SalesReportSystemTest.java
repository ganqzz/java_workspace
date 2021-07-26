package sales;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 総合テスト
 */
public class SalesReportSystemTest {

	@Before
	public void setUp() throws Exception {}

	@Test
	public void shouldPrintStoreReportForSampleData() {
		ApplicationRunner runner = new ApplicationRunner();
		
		String report = runner.run("resources/example-sales.csv");

		assertThat(report, containsString("- London          -    235 -"));
	}
}
