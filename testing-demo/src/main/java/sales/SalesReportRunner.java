package sales;

import java.io.PrintStream;

public class SalesReportRunner {

	private final PrintStream out;

	public static void main(String[] args) {
		String fileLocation = args[0];
		new SalesReportRunner(System.out).run(fileLocation);
	}

	public SalesReportRunner(final PrintStream out) {
		this.out = out;
	}

	public void run(final String fileLocation) {
//		out.println("Hello World!");
		SalesRepository repo = new SalesRepository(fileLocation);
		SalesAnalyser analyser = new SalesAnalyser(repo);
		SalesReport report = new SalesReport(analyser, out);
		report.run();
	}
}
