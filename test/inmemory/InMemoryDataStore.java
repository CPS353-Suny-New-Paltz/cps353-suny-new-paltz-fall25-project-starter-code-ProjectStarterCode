// File: InMemoryDataStore.java (FIXED - implements DataStoreApi methods)
package inmemory;

import numberlettercountdatastoring.DataStoreApi;
import numberlettercountdatastoring.DataRequest;
import configuration.TestInputConfiguration;
import configuration.TestOutputConfiguration;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class InMemoryDataStore implements DataStoreApi {
	private TestInputConfiguration inputConfig;
	private TestOutputConfiguration outputConfig;

	public InMemoryDataStore(TestInputConfiguration inputConfig, TestOutputConfiguration outputConfig) {
		this.inputConfig = inputConfig;
		this.outputConfig = outputConfig;
	}

	public int insertRequest(DataRequest dataRequest) {
		if (inputConfig != null && outputConfig != null) {
			outputConfig.clearOutput();
			for (Integer number : inputConfig.getInputNumbers()) {
				String word = convertNumberToWord(number);
				outputConfig.addOutputString(word);
			}
			return 0;
		}
		return -1;
	}

	public List<Integer> fetchAllData() {
		if (inputConfig != null) {
			return new ArrayList<>(inputConfig.getInputNumbers());
		}
		return new ArrayList<>();
	}

	public boolean processRequest() {
		return inputConfig != null && outputConfig != null;
	}

	public boolean validateNumber(int number) {
		return number >= 0;
	}

	private String convertNumberToWord(int number) {
		String[] numberWords = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
		if (number >= 0 && number <= 9) {
			return numberWords[number];
		}
		return String.valueOf(number);
	}

	public boolean writeResultsToFile(String filePath, List<String> results) {
		if (outputConfig == null || results == null) {
			return false;
		}

		try {
			// Clear existing output
			outputConfig.clearOutput();

			// Add all results to output configuration
			for (String result : results) {
				outputConfig.addOutputString(result);
			}

			System.out.println("InMemoryDataStore: Written " + results.size() +
					" results to output configuration (simulated file: " + filePath + ")");
			return true;

		} catch (Exception e) {
			System.err.println("Error writing to in-memory store: " + e.getMessage());
			return false;
		}
	}

	public boolean processFile(String input, String output) {
		if (inputConfig == null || outputConfig == null) {
			System.err.println("InMemoryDataStore: Missing input or output configuration");
			return false;
		}

		try {
			System.out.println("InMemoryDataStore: Processing simulated file from " + input + " to " + output);

			// Clear existing output
			outputConfig.clearOutput();

			// Get numbers from input configuration
			List<Integer> numbers = inputConfig.getInputNumbers();

			if (numbers.isEmpty()) {
				System.err.println("InMemoryDataStore: No input numbers to process");
				return false;
			}

			// Process each number: convert to word and add to output
			for (Integer number : numbers) {
				if (validateNumber(number)) {
					String word = convertNumberToWord(number);
					outputConfig.addOutputString(word);
					System.out.println("InMemoryDataStore: Processed " + number + " -> \"" + word + "\"");
				} else {
					System.err.println("InMemoryDataStore: Skipping invalid number: " + number);
					outputConfig.addOutputString("ERROR: Invalid number " + number);
				}
			}

			System.out.println("InMemoryDataStore: Successfully processed " + numbers.size() + " numbers");
			return true;

		} catch (Exception e) {
			System.err.println("InMemoryDataStore: Error processing file: " + e.getMessage());
			return false;
		}
	}

	public List<BigInteger> processFile(String input) {
		// Simulate reading an input file by returning the configured input numbers as
		// BigInteger.
		if (inputConfig == null) {
			System.err.println("InMemoryDataStore: input configuration is missing");
			return new ArrayList<>();
		}

		List<BigInteger> out = new ArrayList<>();
		for (Integer n : inputConfig.getInputNumbers()) {
			out.add(BigInteger.valueOf(n));
		}
		return out;
	}
}