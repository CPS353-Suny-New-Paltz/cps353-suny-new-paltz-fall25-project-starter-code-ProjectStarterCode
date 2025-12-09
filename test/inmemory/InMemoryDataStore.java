// File: InMemoryDataStore.java (FIXED - implements DataStoreApi methods)
package inmemory;

import numberlettercountdatastoring.DataStoreApi;
import numberlettercountdatastoring.DataRequest;
import configuration.TestInputConfiguration;
import configuration.TestOutputConfiguration;
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
		String[] numberWords = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
		if (number >= 0 && number <= 9) {
			return numberWords[number];
		}
		return String.valueOf(number);
	}
}