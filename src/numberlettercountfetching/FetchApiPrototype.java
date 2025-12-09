package numberlettercountfetching;

import java.util.Arrays;
import java.util.List;

import project.annotations.NetworkAPIPrototype;

public class FetchApiPrototype {

	@NetworkAPIPrototype
	public void prototype(FetchApi fetchApi) {
		System.out.println("=== Fetch API Prototype Demo ===");

		// Test with different types of fetch requests
		FetchRequest basicRequest = new FetchRequest(Arrays.asList(1, 2, 3));
		List<Integer> result1 = fetchApi.insertRequest(basicRequest);
		System.out.println("1. Basic request result: " + result1);

		IntFetchRequest intRequest = new IntFetchRequest(42);
		List<Integer> result2 = fetchApi.insertRequest(intRequest);
		System.out.println("2. Int request result: " + result2);

		StringFetchRequest stringRequest = new StringFetchRequest("test");
		List<Integer> result3 = fetchApi.insertRequest(stringRequest);
		System.out.println("3. String request result: " + result3);

		ListFetchRequest listRequest = new ListFetchRequest(Arrays.asList(7, 8, 9));
		List<Integer> result4 = fetchApi.insertRequest(listRequest);
		System.out.println("4. List request result: " + result4);

		// Test validation
		boolean valid1 = fetchApi.validateNumber(10);
		System.out.println("5. Validation for 10: " + valid1);

		boolean valid2 = fetchApi.validateNumber(-5);
		System.out.println("6. Validation for -5: " + valid2);

		System.out.println("=== Fetch API Prototype Demo Complete ===");
	}
}