package numberlettercountfetching;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import numberlettercountdatastoring.DataStoreApi;
import numberlettercountdatastoring.DataRequest; 
import numberlettercountcomputing.ComputingApi;
import numberlettercountcomputing.PassData; 
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FetchApiImpl implements FetchApi {
	private static final Logger logger = Logger.getLogger(FetchApiImpl.class.getName());
	private DataStoreApi dataStoreApi;
	private ComputingApi computingApi;
	private List<Integer> storedData = new ArrayList<>();
	private List<Integer> lastResults = new ArrayList<>();

	// This constructor matches the CoordinatorApiImpl constructor
	public FetchApiImpl() {
		// These will be set later via setter methods
		logger.info("FetchApiImpl created - dependencies need to be set");
	}

	public void setDataStoreApi(DataStoreApi dataStoreApi) {
		this.dataStoreApi = dataStoreApi;
		logger.info("DataStoreApi dependency set");
	}

	public void setComputingApi(ComputingApi computingApi) {
		this.computingApi = computingApi;
		logger.info("ComputingApi dependency set");
	}

	// CoordinatorAPI methods
	public boolean processFile(String inputFile, String outputFile) {
		try {
			// 1. Read numbers from input file
			Path inputPath = Paths.get(inputFile);
			List<Integer> numbers = new ArrayList<>();

			if (!Files.exists(inputPath)) {
				System.err.println("Input file not found: " + inputFile);
				logger.severe("Input file not found: " + inputFile);
				return false;
			}

			for (String line : Files.readAllLines(inputPath)) {
				try {
					numbers.add(Integer.parseInt(line.trim()));
				} catch (NumberFormatException e) {
					System.err.println("Skipping invalid line: " + line);
					logger.warning("Skipping invalid line: " + line);
				}
			}

			if (numbers.isEmpty()) {
				System.err.println("No valid numbers in input file");
				logger.warning("No valid numbers in input file");
				return false;
			}

			System.out.println("Processing " + numbers.size() + " numbers: " + numbers);
			logger.info("Processing " + numbers.size() + " numbers from file: " + inputFile);

			// 2. Process each number through ComputingApi
			List<Integer> letterCounts = new ArrayList<>();
			if (computingApi == null) {
				System.err.println("ComputingApi not available");
				logger.severe("ComputingApi not available");
				return false;
			}

			for (Integer number : numbers) {
				PassData passData = computingApi.passData(number);
				List<Integer> results = computingApi.processPassData(passData);
				if (!results.isEmpty()) {
					letterCounts.add(results.get(0)); // Letter count
					logger.info("Processed number " + number + " -> result: " + results.get(0));
				}
			}

			this.lastResults = letterCounts;

			// 3. Store the numbers via FetchApi's insertRequest
			FetchRequest fetchRequest = new FetchRequest(numbers);
			List<Integer> insertResult = this.insertRequest(fetchRequest);
			if (insertResult != null && (insertResult.size() == 1 && insertResult.get(0) == -1)) {
				logger.warning("Failed to store numbers via insertRequest");
			} else {
				logger.info("Successfully stored numbers via insertRequest");
			}

			// 4. Store computed results via DataStoreApi
			if (dataStoreApi != null && !letterCounts.isEmpty()) {
				int storedCount = 0;
				for (Integer result : letterCounts) {
					if (result != null) {
						DataRequest dataRequest = new DataRequest(result);
						int dataStoreResult = dataStoreApi.insertRequest(dataRequest);
						if (dataStoreResult >= 0) {
							storedCount++;
						}
					}
				}
				logger.info("Stored " + storedCount + " computed results via DataStoreApi");
			}

			// 5. Write results to output file
			StringBuilder output = new StringBuilder();
			for (int i = 0; i < letterCounts.size(); i++) {
				output.append(letterCounts.get(i));
				if (i < letterCounts.size() - 1) {
					output.append(",");
				}
			}

			Path outputPath = Paths.get(outputFile);
			Files.write(outputPath, output.toString().getBytes(),
					StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

			System.out.println("Successfully wrote " + letterCounts.size() + 
					" results to: " + outputFile);
			System.out.println("Output: " + output.toString());

			logger.info("Successfully wrote " + letterCounts.size() + 
					" results to: " + outputFile);

			return true;

		} catch (IOException e) {
			System.err.println("Error processing file: " + e.getMessage());
			logger.severe("Error processing file: " + e.getMessage());
			return false;
		} catch (Exception e) {
			System.err.println("Unexpected error: " + e.getMessage());
			logger.severe("Unexpected error in processFile: " + e.getMessage());
			return false;
		}
	}

	public List<Integer> getLastResults() {
		return new ArrayList<>(lastResults); // Return defensive copy
	}

	// Original FetchApi methods
	public List<Integer> insertRequest(FetchRequest fetchRequest) {
		try {
			// Parameter validation
			if (fetchRequest == null) {
				logger.warning("FetchRequest is null");
				return List.of(-1);
			}

			List<Integer> data = fetchRequest.getData();
			if (data == null) {
				logger.warning("FetchRequest data is null");
				return List.of(-1);
			}

			if (data.isEmpty()) {
				logger.warning("FetchRequest data is empty");
				return List.of(-1);
			}

			// Validate and store each number
			int validCount = 0;
			for (Integer number : data) {
				if (number == null) {
					logger.warning("Skipping null number in request");
					continue;
				}

				// Use internal validation
				if (!validateNumber(number)) {
					logger.warning("Skipping invalid number: " + number);
					continue;
				}

				// Store the valid number
				storedData.add(number);
				validCount++;
				logger.info("Stored number: " + number);
			}

			if (validCount == 0) {
				logger.warning("No valid numbers found in request");
				return List.of(-1);
			}

			logger.info("Successfully stored " + validCount + " numbers");
			return new ArrayList<>(data); // Return defensive copy

		} catch (Exception e) {
			logger.severe("Error in insertRequest: " + e.getMessage());
			return List.of(-1);
		}
	}

	public boolean validateNumber(int number) {
		try {
			// Basic validation
			boolean basicValidation = number >= 0;
			logger.info("Basic validation for number " + number + ": " + basicValidation);

			if (!basicValidation) {
				return false;
			}

			// Additional validation with DataStoreApi if available
			if (dataStoreApi != null) {
				try {
					boolean dataStoreValidation = dataStoreApi.validateNumber(number);
					logger.info("DataStoreApi validation for number " + number + ": " + dataStoreValidation);
					return dataStoreValidation;
				} catch (Exception e) {
					logger.warning("DataStoreApi validation failed: " + e.getMessage());
					// Fall back to basic validation
				}
			}

			return basicValidation;

		} catch (Exception e) {
			logger.severe("Error in validateNumber: " + e.getMessage());
			return false;
		}
	}

	public List<Integer> getStoredData() {
		try {
			logger.info("Returning " + storedData.size() + " stored items");
			return new ArrayList<>(storedData);
		} catch (Exception e) {
			logger.severe("Error in getStoredData: " + e.getMessage());
			return new ArrayList<>();
		}
	}

	public int getStoredDataCount() {
		return storedData.size();
	}

	// Helper method with error handling
	public void clearStoredData() {
		try {
			int count = storedData.size();
			storedData.clear();
			logger.info("Cleared " + count + " items from storage");
		} catch (Exception e) {
			logger.severe("Error clearing stored data: " + e.getMessage());
		}
	}

	// New method to coordinate computation for all stored data
	public List<Integer> computeAllStoredData() {
		List<Integer> allComputedResults = new ArrayList<>();

		if (computingApi == null) {
			logger.warning("ComputingApi not available for computation");
			return allComputedResults;
		}

		try {
			for (Integer number : storedData) {
				if (number != null) {
					// Create PassData for each number
					PassData passData = computingApi.passData(number);

					// Process PassData to get results
					List<Integer> computedResults = computingApi.processPassData(passData);

					if (computedResults != null) {
						allComputedResults.addAll(computedResults);
						logger.info("Computed results for " + number + ": " + computedResults);
					}
				}
			}
		} catch (Exception e) {
			logger.severe("Error in computeAllStoredData: " + e.getMessage());
		}

		return allComputedResults;
	}

	// New method to store computed results via DataStoreApi
	public boolean storeComputedResults(List<Integer> computedResults) {
		if (dataStoreApi == null) {
			logger.warning("DataStoreApi not available for storing computed results");
			return false;
		}

		if (computedResults == null || computedResults.isEmpty()) {
			logger.warning("No computed results to store");
			return false;
		}

		int storedCount = 0;
		try {
			for (Integer result : computedResults) {
				if (result != null) {
					// Create DataRequest for each result
					DataRequest dataRequest = new DataRequest(result);
					int insertResult = dataStoreApi.insertRequest(dataRequest);

					if (insertResult >= 0) { // Assuming non-negative return means success
						storedCount++;
						logger.info("Successfully stored computed result: " + result);
					} else {
						logger.warning("Failed to store computed result: " + result);
					}
				}
			}
			logger.info("Stored " + storedCount + " computed results via DataStoreApi");
			return storedCount > 0;
		} catch (Exception e) {
			logger.severe("Error storing computed results: " + e.getMessage());
			return false;
		}
	}
}