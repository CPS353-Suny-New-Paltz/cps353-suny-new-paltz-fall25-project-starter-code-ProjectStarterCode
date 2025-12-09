package testharness;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
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

			// Parse numbers (format: "1,15,10,5,2,3,8")
			String[] numberStrings = lines.get(0).split(",");
			Integer[] numbers = new Integer[numberStrings.length];
			for (int i = 0; i < numberStrings.length; i++) {
				try {
					numbers[i] = Integer.parseInt(numberStrings[i].trim());
				} catch (NumberFormatException e) {
					writeError(outputPath, "Invalid number: " + numberStrings[i]);
					return;
				}
			}

			// Create request and process
			numberlettercountfetching.ListFetchRequest request = 
					new numberlettercountfetching.ListFetchRequest(Arrays.asList(numbers));

			List<Integer> results = coordinator.insertRequest(request);

			if (results.isEmpty() || results.get(0) == -1) {
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