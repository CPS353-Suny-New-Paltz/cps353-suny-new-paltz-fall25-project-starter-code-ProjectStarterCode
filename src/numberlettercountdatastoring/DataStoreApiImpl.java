package numberlettercountdatastoring;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DataStoreApiImpl implements DataStoreApi {
	private static final Logger logger = Logger.getLogger(DataStoreApiImpl.class.getName());
	private List<BigInteger> storedNumbers = new ArrayList<>();

	public DataStoreApiImpl() {
		logger.info("DataStoreApiImpl created");
	}

	public int insertRequest(DataRequest dataRequest) {
		try {
			if (dataRequest == null) {
				logger.warning("DataRequest is null");
				return -1;
			}

			String dataContent = dataRequest.getDataContent();
			if (dataContent == null || dataContent.trim().isEmpty()) {
				logger.warning("Data content is empty for request ID: " + dataRequest.getRequestId());
				return -1;
			}

			logger.info("Processing data request: " + dataRequest);

			// Process the data content
			String[] numberStrings = dataContent.split(",");
			int storedCount = 0;

			for (String numStr : numberStrings) {
				try {
					// Parse as BigInteger to support large numbers
					BigInteger number = new BigInteger(numStr.trim());

					// Validate number before storing - using int validation for now
					// since validateNumber expects int
					if (number.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) <= 0 
							&& number.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) >= 0) {

						int intNumber = number.intValue();
						if (validateNumber(intNumber)) {
							storedNumbers.add(number);
							storedCount++;
							logger.info("Stored number: " + number);
						} else {
							logger.warning("Skipping invalid number: " + number);
						}
					} else {
						// Number is too large for int validation, but we can still store it
						if (number.compareTo(BigInteger.ZERO) >= 0) {
							storedNumbers.add(number);
							storedCount++;
							logger.info("Stored large number: " + number);
						} else {
							logger.warning("Skipping negative large number: " + number);
						}
					}

				} catch (NumberFormatException e) {
					logger.warning("Invalid number format: '" + numStr + "'");
				}
			}

			if (storedCount == 0) {
				logger.warning("No valid numbers stored from request");
				return -1;
			}

			logger.info("Successfully stored " + storedCount + " numbers");
			return storedCount;

		} catch (Exception e) {
			logger.severe("Error in insertRequest: " + e.getMessage());
			return -1;
		}
	}

	// New method to process a file and store numbers
	public List<BigInteger> processFile(String filePath) {
		List<BigInteger> processedNumbers = new ArrayList<>();

		try {
			Path path = Paths.get(filePath);
			if (!Files.exists(path)) {
				logger.severe("File not found: " + filePath);
				return processedNumbers;
			}

			List<String> lines = Files.readAllLines(path);
			logger.info("Reading " + lines.size() + " lines from file: " + filePath);

			for (String line : lines) {
				try {
					// Parse as BigInteger to support arbitrarily large inputs
					BigInteger number = new BigInteger(line.trim());

					if (number.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) <= 0 
							&& number.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) >= 0) {

						int intNumber = number.intValue();
						if (validateNumber(intNumber)) {
							storedNumbers.add(number);
							processedNumbers.add(number);
							logger.info("Processed and stored number from file: " + number);
						} else {
							logger.warning("Skipping invalid number from file: " + number);
						}
					} else {
						// Large number - store it even though it doesn't fit in int
						if (number.compareTo(BigInteger.ZERO) >= 0) {
							storedNumbers.add(number);
							processedNumbers.add(number);
							logger.info("Processed and stored large number from file: " + number);
						} else {
							logger.warning("Skipping negative large number from file: " + number);
						}
					}

				} catch (NumberFormatException e) {
					logger.warning("Invalid number format in file: '" + line + "'");
				}
			}

			logger.info("Successfully processed " + processedNumbers.size() + " numbers from file");
			return processedNumbers;

		} catch (IOException e) {
			logger.severe("Error reading file: " + e.getMessage());
			return processedNumbers;
		} catch (Exception e) {
			logger.severe("Error in processFile: " + e.getMessage());
			return processedNumbers;
		}
	}

	// New method to write results to a file
	public boolean writeResultsToFile(String filePath, List<String> results) {
		try {
			if (results == null || results.isEmpty()) {
				logger.warning("No results to write to file");
				return false;
			}

			Path path = Paths.get(filePath);
			StringBuilder content = new StringBuilder();

			// Write all results on ONE LINE, comma-separated
			for (int i = 0; i < results.size(); i++) {
				content.append(results.get(i));
				if (i < results.size() - 1) {
					content.append(",");
				}
			}

			Files.write(path, content.toString().getBytes(),
					StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

			logger.info("Successfully wrote " + results.size() + " results to file: " + filePath);
			return true;

		} catch (IOException e) {
			logger.severe("Error writing to file: " + e.getMessage());
			return false;
		} catch (Exception e) {
			logger.severe("Error in writeResultsToFile: " + e.getMessage());
			return false;
		}
	}

	
	public boolean validateNumber(int number) {
		try {
			// Basic validation - only check if number is non-negative
			return number >= 0;
		} catch (Exception e) {
			logger.severe("Error in validateNumber: " + e.getMessage());
			return false;
		}
	}

	// Internal helper method (not in interface, for testing/debugging)
	public List<BigInteger> getStoredNumbers() {
		return new ArrayList<>(storedNumbers);
	}

	public boolean processFile(String input, String output) {
		try {
			logger.info("Processing file: " + input + " -> " + output);

			// 1. Read numbers from input file
			List<BigInteger> numbers = processFile(input);

			if (numbers.isEmpty()) {
				logger.warning("No valid numbers processed from input file: " + input);
				return false;
			}

			// 2. Convert numbers to strings for output
			List<String> results = new ArrayList<>();
			for (BigInteger number : numbers) {
				results.add(number.toString());
			}

			// 3. Write results to output file
			boolean success = writeResultsToFile(output, results);

			if (success) {
				logger.info("Successfully processed " + numbers.size() +
						" numbers from " + input + " to " + output);
			} else {
				logger.warning("Failed to write results to: " + output);
			}

			return success;

		} catch (Exception e) {
			logger.severe("Error in processFile(" + input + ", " + output + "): " + e.getMessage());
			return false;
		}
	}
}