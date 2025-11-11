package numberlettercountdatastoring;

import project.annotations.ProcessAPIPrototype;

public class DataStoreApiPrototype {

	@ProcessAPIPrototype
	public void prototype(DataStoreApi dataapi) {
		System.out.println("=== DataStore API Prototype Demo ===");

		// Test insert request
		DataRequest request = new DataRequest(123, "file", "sample data content");
		int result = dataapi.insertRequest(request);
		System.out.println("Insert request result: " + result);

		// Test serialization
		Serialize serialized = dataapi.serializingData();
		System.out.println("Serialized: " + serialized);

		// Test formatting
		FormatData formatted = dataapi.formatData();
		System.out.println("Formatted: " + formatted);

		// Test results
		ResultOfCountLetter countResult = dataapi.result();
		System.out.println("Count result: " + countResult);

		// Test sending
		SendInfo sent = dataapi.sentData();
		System.out.println("Sent: " + sent);

		// Test receiving
		RecieveInfo received = dataapi.recieveData();
		System.out.println("Received: " + received);

		// Test additional methods
		boolean stored = dataapi.storeData("test data", "test_location");
		System.out.println("Data stored: " + stored);

		String readData = dataapi.readData("test_source");
		System.out.println("Data read: " + readData);

		boolean initialized = dataapi.initializeStorage();
		System.out.println("Storage initialized: " + initialized);

		boolean valid = dataapi.validateData("valid data");
		System.out.println("Data valid: " + valid);

		System.out.println("=== DataStore API Prototype Demo Complete ===");
	}

	//	public void prototype(DataStoreApi dataapi) {
	//
	//
	//		dataapi.insertRequest(new DataRequest(123));
	//		//Read in many integers froma user speficed source (memory,file/database)
	//		//Store bunch of result and get back true or false after they are stored. no converting yet Look back at computingapi and computilapiimpl and test of comupting
	//	}

	//	public void result(String result) {
	//		this.result(result);
	//	}
	//	
	//	public void sent(SendInfo sent) {
	//		this.sent(sent);
	//	}
	//	
	//	public void format(FormatData format) {
	//		this.format(format);
	//	}
	//	
	//	public void recieve(RecieveInfo recieve) {
	//		 this.recieve(recieve);
	//	}
	//	
	//	public void serialize(Serialize serialize) {
	//		this.serialize(serialize);
	//	}
	//delimiter is for the user to see from the output like 123 to one hundred,(space)	
}
