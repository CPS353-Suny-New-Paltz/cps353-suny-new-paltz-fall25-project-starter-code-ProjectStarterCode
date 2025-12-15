package testharness;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

//will correct for checkpoint 6

public class TestUser {

	private final numberlettercountfetching.FetchApi coordinator;

	public TestUser(numberlettercountfetching.FetchApi coordinator) {
		this.coordinator = coordinator;
	}

	public void run(String outputPath) {
		try {
			String inputPath = "test" + File.separatorChar + "testInputFile.test";
			char delimiter = ';';

			// Read input file
			List<String> lines = Files.readAllLines(Paths.get(inputPath));
			if (lines.isEmpty()) {
				writeError(outputPath, "Input file is empty");
				return;
			}

			// Parse numbers as BigInteger
			String[] numberStrings = lines.get(0).split(",");
			List<BigInteger> numbers = new ArrayList<>();
			for (String numStr : numberStrings) {
				try {
					numbers.add(new BigInteger(numStr.trim()));
				} catch (NumberFormatException e) {
					writeError(outputPath, "Invalid number: " + numStr);
					return;
				}
			}

			// Create request and process
			numberlettercountfetching.FetchRequest request = 
					new numberlettercountfetching.FetchRequest(numbers);

			List<BigInteger> results = coordinator.insertRequest(request);

			if (results.isEmpty() || results.get(0).equals(BigInteger.valueOf(-1))) {
				writeError(outputPath, "Failed to process request");
				return;
			}

			// Write results
			StringBuilder output = new StringBuilder();
			for (int i = 0; i < results.size(); i++) {
				if (i > 0) {
					output.append(delimiter);
				}
				output.append(results.get(i));
			}

			Files.write(Paths.get(outputPath), output.toString().getBytes(),
					StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

		} catch (IOException e) {
			System.err.println("Error in TestUser.run: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Unexpected error in TestUser.run: " + e.getMessage());
		}
	}

	private void writeError(String outputPath, String errorMessage) {
		try {
			Files.write(Paths.get(outputPath), ("ERROR: " + errorMessage).getBytes(),
					StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			System.err.println("Failed to write error: " + e.getMessage());
		}
	}
}