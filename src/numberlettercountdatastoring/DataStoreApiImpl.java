package numberlettercountdatastoring;

import numberlettercountcomputing.ComputingApi;

public class DataStoreApiImpl implements DataStoreApi {
	private ComputingApi computingApi;

	public DataStoreApiImpl() {}

	public DataStoreApiImpl(ComputingApi computingApi) {
		this.computingApi = computingApi;
	}

	public void setComputingApi(ComputingApi computingApi) {
		this.computingApi = computingApi;
	}


	public int insertRequest(DataRequest dataRequest) {
		if (dataRequest == null) {
			return -1; // failure
		}

		System.out.println("Processing data request: " + dataRequest);

		// Store the data and return success code
		boolean stored = storeData(dataRequest.getDataContent(), "storage_location_" + dataRequest.getRequestId());

		return stored ? 0 : -1; // 0 for success, -1 for failure
	}


	public Serialize serializingData() {
		Serialize serialize = new Serialize();
		serialize.setData("Sample serialized data");
		serialize.setSerializationFormat("json");
		serialize.setCompressed(false);

		System.out.println("Serializing data: " + serialize);
		return serialize;
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

		System.out.println("Formatting data: " + format);
		return format;
	}


	public ResultOfCountLetter result() {
		ResultOfCountLetter result = new ResultOfCountLetter();
		result.setLetterCount(42); // Example count
		result.setOriginalData("sample input data");
		result.setProcessedData("sample processed output");

		System.out.println("Returning result: " + result);
		return result;
	}


	public SendInfo sentData() {
		SendInfo sendInfo = new SendInfo();
		sendInfo.setData("Data to be sent");
		sendInfo.setDestination("output_file.txt");
		sendInfo.setSendMethod("file");

		System.out.println("Sending data: " + sendInfo);
		return sendInfo;
	}


	public RecieveInfo recieveData() {
		RecieveInfo receiveInfo = new RecieveInfo();
		receiveInfo.setData("Received data content");
		receiveInfo.setSource("input_file.txt");
		receiveInfo.setReceiveMethod("file");

		System.out.println("Receiving data: " + receiveInfo);
		return receiveInfo;
	}


	public boolean storeData(String data, String location) {
		if (data == null || location == null) {
			return false;
		}

		System.out.println("Storing data '" + data + "' at location: " + location);
		// Simulate successful storage
		return true;
	}


	public String readData(String source) {
		if (source == null) {
			return "";
		}

		System.out.println("Reading data from source: " + source);
		// Simulate reading data
		return "Sample data from " + source;
	}


	public boolean initializeStorage() {
		System.out.println("Initializing data storage");
		// Simulate successful initialization
		return true;
	}


	public boolean validateData(String data) {
		if (data == null || data.trim().isEmpty()) {
			return false;
		}

		System.out.println("Validating data: " + data);
		// Simple validation - data should not be empty
		return !data.trim().isEmpty();
	}
}
