package numberlettercountdatastoring;

import project.annotations.ProcessAPIPrototype;

public class DataStoreApiPrototype {

	@ProcessAPIPrototype
	public void prototype(DataStoreApi dataApi) {
		System.out.println("=== DataStore API Prototype Demo ===");

		// Test insert request
		DataRequest request = new DataRequest(123, "file", "1,2,3,4,5");
		int result = dataApi.insertRequest(request);
		System.out.println("Insert request result: " + result);

		// Test validation
		boolean valid1 = dataApi.validateNumber(5);
		System.out.println("Validation for 5: " + valid1);

		boolean valid2 = dataApi.validateNumber(-1);
		System.out.println("Validation for -1: " + valid2);

		System.out.println("=== DataStore API Prototype Demo Complete ===");
	}
}