package numberlettercountfetching;

import java.util.ArrayList;
import java.util.List;

import numberlettercountdatastoring.DataStoreApi;

public class FetchApiImpl implements FetchApi {
	private DataStoreApi dataStoreApi;
	private List<Integer> storedData = new ArrayList<>();
	private InputSource inputSource = new InputSource();
	private OutputResult outputResult = new OutputResult();
	private Delimiters delimiters = new Delimiters();
	private Display display = new Display();

	public void setDataStoreApi(DataStoreApi dataStoreApi) {
		this.dataStoreApi = dataStoreApi;
	}

	@Override
	public List<Integer> insertRequest(FetchRequest fetchRequest) {
		if (fetchRequest != null && fetchRequest.getData() != null) {
			storedData.addAll(fetchRequest.getData());
			return fetchRequest.getData(); // Return the inserted data
		}
		return List.of(-1); // failure value
	}

	@Override
	public InputSource getInputSource() {
		return inputSource;
	}

	@Override
	public OutputResult getOutputResult() {
		return outputResult;
	}

	@Override
	public Delimiters getDelimiters() {
		return delimiters;
	}

	@Override
	public PassData inputSource() {
		return new PassData(); // Return actual object instead of null
	}

	@Override
	public Display displayIt() {
		return display;
	}

	// Helper method to get stored data
	public List<Integer> getStoredData() {
		return new ArrayList<>(storedData);
	}
}