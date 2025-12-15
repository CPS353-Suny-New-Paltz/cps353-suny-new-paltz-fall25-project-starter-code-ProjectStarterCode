package numberlettercountfetching;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import numberlettercountdatastoring.DataStoreApi;
import numberlettercountcomputing.ComputingApi;

public class FetchApiImpl implements FetchApi {
	private static final Logger logger = Logger.getLogger(FetchApiImpl.class.getName());
	private DataStoreApi dataStoreApi;
	private ComputingApi computingApi;

	public FetchApiImpl() {
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

	public List<BigInteger> insertRequest(FetchRequest fetchRequest) {
		try {
			if (fetchRequest == null) {
				logger.warning("FetchRequest is null");
				return List.of(BigInteger.valueOf(-1));
			}

			List<BigInteger> data = fetchRequest.getData();
			if (data == null || data.isEmpty()) {
				logger.warning("FetchRequest data is null or empty");
				return List.of(BigInteger.valueOf(-1));
			}

			List<BigInteger> results = new ArrayList<>();
			List<String> resultStrings = new ArrayList<>(); // For file writing

			for (BigInteger number : data) {
				if (number == null) {
					logger.warning("Skipping null number in request");
					results.add(BigInteger.valueOf(-1));
					resultStrings.add("-1");
					continue;
				}

				// Validate the number
				if (!validateNumber(number)) {
					logger.warning("Skipping invalid number: " + number);
					results.add(BigInteger.valueOf(-1));
					resultStrings.add("-1");
					continue;
				}
				// Compute letter count via ComputingApi (supports BigInteger)
				BigInteger letterCount = BigInteger.valueOf(-1);
				if (computingApi != null) {
					try {
						letterCount = computingApi.computeNumber(number);
						logger.info("Computed letter count for " + number + " = " + letterCount);
					} catch (Exception e) {
						logger.warning("ComputingApi failed for " + number + ": " + e.getMessage());
						letterCount = BigInteger.valueOf(-1);
					}
				}

				results.add(letterCount);
				resultStrings.add(letterCount == null ? "-1" : letterCount.toString()); // Store as string for file
				// writing
			}

			// Do not write to file here; leave persistence to higher-level orchestration
			// (e.g., processFile) so ManualTestingFramework can drive end-to-end flow.

			return results;

		} catch (Exception e) {
			logger.severe("Error in insertRequest: " + e.getMessage());
			return List.of(BigInteger.valueOf(-1));
		}
	}

	/**
	 * Process an input file end-to-end: read numbers from `inputPath`, compute
	 * letter counts,
	 * and write results to `outputPath` via the DataStoreApi.
	 */
	public boolean processFile(String inputPath, String outputPath) {
		try {
			if (inputPath == null || outputPath == null) {
				logger.warning("Input or output path is null");
				return false;
			}

			// 1) Let DataStore read the input file and return parsed integers
			if (dataStoreApi == null) {
				logger.severe("DataStoreApi not set on FetchApiImpl");
				return false;
			}

			List<BigInteger> numbers = dataStoreApi.processFile(inputPath);
			if (numbers == null || numbers.isEmpty()) {
				logger.warning("No numbers returned from DataStore.processFile");
				return false;
			}
			// 2) Delegate computation to ComputingApi (batch)
			List<BigInteger> results;
			if (computingApi != null) {
				results = computingApi.computeNumbers(numbers);
			} else {
				logger.severe("ComputingApi not set on FetchApiImpl");
				return false;
			}

			// Convert results to strings and write via DataStoreApi
			List<String> resultStrings = new ArrayList<>();
			for (BigInteger r : results) {
				resultStrings.add(r == null ? "-1" : r.toString());
			}

			boolean wrote = dataStoreApi.writeResultsToFile(outputPath, resultStrings);
			return wrote;

		} catch (Exception e) {
			logger.severe("Error in processFile: " + e.getMessage());
			return false;
		}
	}

	public boolean validateNumber(BigInteger number) {
		try {
			if (number == null) {
				logger.warning("Number is null");
				return false;
			}

			// Delegate validation to DataStoreApi if available
			if (dataStoreApi != null) {
				try {
					if (number.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) <= 0 &&
							number.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) >= 0) {
						return dataStoreApi.validateNumber(number.intValue());
					}
				} catch (Exception e) {
					logger.warning("DataStoreApi validation failed: " + e.getMessage());
				}
			}

			// Basic validation - non-negative
			boolean isValid = number.compareTo(BigInteger.ZERO) >= 0;
			logger.info("Validation for number " + number + ": " + isValid);

			return isValid;

		} catch (Exception e) {
			logger.severe("Error in validateNumber: " + e.getMessage());
			return false;
		}
	}
}