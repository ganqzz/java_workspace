package sales;

import java.io.PrintStream;
import java.util.Map;

public class SalesReport {

	private SalesAnalyser analyser;
	private PrintStream out;

	public SalesReport(final SalesAnalyser analyser, PrintStream out) {
		this.analyser = analyser;
		this.out = out;
	}

	public void run() {
		// Print things out here!
		analyser.tallyCitySales().forEach((city, tally) -> {
			out.printf("- %-15s - %6.6s -%n", city, tally);
		});
	}
}
