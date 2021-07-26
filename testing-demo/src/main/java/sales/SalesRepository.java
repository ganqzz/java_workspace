package sales;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;

public class SalesRepository {

	private final String fileLocation;
	private List<Sale> sales;

	public SalesRepository(final String fileLocation) {
		this.fileLocation = fileLocation;
	}

	private int parseInt(String value) {
		return Integer.parseInt(value.trim());
	}

	public List<Sale> loadSales() {
		if (sales == null) {
			sales = new ArrayList<>();
			final File file = new File(fileLocation);
			if (!file.exists() || !file.isFile() || !file.canRead()) {
				System.err.println("Unable to find readable file: " + file.getAbsolutePath());
			}
			try (CSVReader reader = new CSVReader(new FileReader(fileLocation))) {
				String[] nextLine;
				while ((nextLine = reader.readNext()) != null) {
					String store = nextLine[1].trim();
					int number = parseInt(nextLine[2]);
					int pricePerItem = parseInt(nextLine[3]);
					sales.add(new Sale(store, number, pricePerItem));
				}
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}

		return sales;
	}
}
