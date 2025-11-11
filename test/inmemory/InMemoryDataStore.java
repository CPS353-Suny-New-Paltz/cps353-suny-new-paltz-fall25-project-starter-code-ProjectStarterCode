package inmemory;

import numberlettercountdatastoring.DataStoreApi;
import numberlettercountdatastoring.Delimiters;
import numberlettercountdatastoring.DataRequest;
import numberlettercountdatastoring.Serialize;
import numberlettercountdatastoring.FormatData;
import numberlettercountdatastoring.ResultOfCountLetter;
import numberlettercountdatastoring.SendInfo;
import numberlettercountdatastoring.RecieveInfo;
import configuration.TestInputConfiguration;
import configuration.TestOutputConfiguration;

public class InMemoryDataStore implements DataStoreApi {
	private TestInputConfiguration inputConfig;
	private TestOutputConfiguration outputConfig;

	public InMemoryDataStore(TestInputConfiguration inputConfig, TestOutputConfiguration outputConfig) {
		this.inputConfig = inputConfig;
		this.outputConfig = outputConfig;
	}


	public int insertRequest(DataRequest dataRequest) {
		// Read from input config and write to output config
		if (inputConfig != null && outputConfig != null) {
			outputConfig.clearOutput(); // Clear previous results

			for (Integer number : inputConfig.getInputNumbers()) {
				// Convert number to words (simple implementation for 0-9)
				String word = convertNumberToWord(number);
				outputConfig.addOutputString(word);
			}
			return 0;  // SUCCESS as int (0 typically means success)
		}
		return -1;  // FAILED as int (-1 typically means failure)
	}


	public Serialize serializingData() {
		if (outputConfig == null) {
			return new Serialize("json", "No data available", false);
		}

		// Serialize the output data
		StringBuilder serializedData = new StringBuilder();
		serializedData.append("{\"results\": [");

		for (int i = 0; i < outputConfig.getOutputStrings().size(); i++) {
			if (i > 0) {
				serializedData.append(", ");
			}
			serializedData.append("\"").append(outputConfig.getOutputStrings().get(i)).append("\"");
		}
		serializedData.append("]}");

		return new Serialize("json", serializedData.toString(), false);
	}


	public FormatData formatData() {
		FormatData format = new FormatData();
		format.setFormatType("text");

		Delimiters delimiters = new Delimiters();
		delimiters.setFieldDelimiter(",");
		delimiters.setRecordDelimiter("\n");
		delimiters.setOutputDelimiter(" ");

		format.setDelimiters(delimiters);
		format.setIncludeSpaces(true);
		format.setCapitalizeWords(false);

		return format;
	}


	public ResultOfCountLetter result() {
		if (outputConfig == null || outputConfig.getOutputStrings().isEmpty()) {
			return new ResultOfCountLetter(0, "No input", "No output");
		}

		// Calculate total letter count
		int totalLetters = 0;
		StringBuilder allOutput = new StringBuilder();

		for (String output : outputConfig.getOutputStrings()) {
			totalLetters += output.replaceAll("[^a-zA-Z]", "").length();
			if (allOutput.length() > 0) {
				allOutput.append(", ");
			}
			allOutput.append(output);
		}

		StringBuilder inputNumbers = new StringBuilder();
		if (inputConfig != null) {
			for (Integer number : inputConfig.getInputNumbers()) {
				if (inputNumbers.length() > 0) {
					inputNumbers.append(", ");
				}
				inputNumbers.append(number);
			}
		}

		return new ResultOfCountLetter(totalLetters, inputNumbers.toString(), allOutput.toString());
	}


	public SendInfo sentData() {
		if (outputConfig == null) {
			return new SendInfo("No data to send", "nowhere", "none");
		}

		StringBuilder dataToSend = new StringBuilder();
		for (String output : outputConfig.getOutputStrings()) {
			if (dataToSend.length() > 0) {
				dataToSend.append(" | ");
			}
			dataToSend.append(output);
		}

		return new SendInfo(dataToSend.toString(), "memory_output", "in_memory");
	}


	public RecieveInfo recieveData() {
		if (inputConfig == null) {
			return new RecieveInfo("No data received", "nowhere", "none");
		}

		StringBuilder receivedData = new StringBuilder();
		for (Integer number : inputConfig.getInputNumbers()) {
			if (receivedData.length() > 0) {
				receivedData.append(", ");
			}
			receivedData.append(number);
		}

		return new RecieveInfo(receivedData.toString(), "memory_input", "in_memory");
	}


	public boolean storeData(String data, String location) {
		if (outputConfig != null && data != null) {
			outputConfig.addOutputString(data);
			return true;
		}
		return false;
	}


	public String readData(String source) {
		if (inputConfig == null || inputConfig.getInputNumbers().isEmpty()) {
			return "";
		}

		StringBuilder data = new StringBuilder();
		for (Integer number : inputConfig.getInputNumbers()) {
			if (data.length() > 0) {
				data.append(",");
			}
			data.append(number);
		}

		return data.toString();
	}


	public boolean initializeStorage() {
		if (outputConfig != null) {
			outputConfig.clearOutput();
			return true;
		}
		return false;
	}


	public boolean validateData(String data) {
		return data != null && !data.trim().isEmpty();
	}

	// Helper  to convert numbers to words
	private String convertNumberToWord(int number) {
		String[] numberWords = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

		if (number >= 0 && number <= 9) {
			return numberWords[number];
		} else {
			// For numbers > 9, implement more complex conversion if needed
			return String.valueOf(number);
		}
	}
}
