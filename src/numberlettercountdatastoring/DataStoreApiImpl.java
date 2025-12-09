package numberlettercountdatastoring;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DataStoreApiImpl implements DataStoreApi {
	private static final Logger logger = Logger.getLogger(DataStoreApiImpl.class.getName());
	private List<Integer> storedNumbers = new ArrayList<>();

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
					int number = Integer.parseInt(numStr.trim());

					// Validate number before storing
					if (number < 0) {
						logger.warning("Skipping negative number: " + number);
						continue;
					}

					storedNumbers.add(number);
					storedCount++;
					logger.info("Stored number: " + number);

				} catch (NumberFormatException e) {
					logger.warning("Invalid number format: '" + numStr + "'");
				}
			}

			if (storedCount == 0) {
				logger.warning("No valid numbers stored from request");
				return -1;
			}

			logger.info("Successfully stored " + storedCount + " numbers");
			return storedCount; // Return count of stored numbers

		} catch (Exception e) {
			logger.severe("Error in insertRequest: " + e.getMessage());
			return -1;
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
	public List<Integer> getStoredNumbers() {
		return new ArrayList<>(storedNumbers);
	}
}