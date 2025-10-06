package numberlettercountcomputing;
import java.util.List;

import numberlettercountdatastoring.DataStoreApi;
import numberlettercountfetching.FetchApi;

public class ComputingApiImpl implements ComputingApi {
	private FetchApi fetchApi;
	private DataStoreApi dataStoreApi;

	@Override
	public String initalize(List<Integer> inputData) {
		if (inputData == null || inputData.isEmpty()) {
			return "";
		}

		//        // Simple number to word mapping for demonstration
		//        String[] numberWords = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
		//        
		//        StringBuilder result = new StringBuilder();
		//        for (int i = 0; i < inputData.size(); i++) {
		//            int num = inputData.get(i);
		//            if (num >= 0 && num <= 9) {
		//                result.append(numberWords[num]);
		//            } else {
		//                result.append(num); // Fallback to number if out of range
		//            }
		//            
		//            if (i < inputData.size() - 1) {
		//                result.append(",");
		//            }
		//        }
		// Convert List<Integer> to a comma-separated string
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < inputData.size(); i++) {
			result.append(inputData.get(i));
			if (i < inputData.size() - 1) {
				result.append(",");
			}
		}
		return result.toString(); // Returns "one,two,three" for input [1,2,3]
	}
	@Override
	public List<Integer> compute() {
		return null;
	}

	@Override
	public String writeResult(String result, String delimiters) {
		return ""; // empty string
	}

	@Override
	public void insertRequest() {
		// empty implementation
	}

	@Override
	public Extract extractData() {
		return null;
	}

	@Override
	public SendInfo sendInfo() {
		return null;
	}

	@Override
	public RecieveInfo recieveInfo() {
		return null;
	}

	@Override
	public ProcessData processData() {
		return null;
	}

	@Override
	public PassData passData() {
		return null;
	}
}