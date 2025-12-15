package numberlettercountfetching;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import project.annotations.NetworkAPIPrototype;

public class FetchApiPrototype {

	@NetworkAPIPrototype
	public void prototype(FetchApi fetchApi) {
		System.out.println("=== Fetch API Prototype Demo (Arbitrarily Large Numbers) ===");

		// Test with regular numbers
		FetchRequest basicRequest = new FetchRequest(Arrays.asList(
				BigInteger.valueOf(1), 
				BigInteger.valueOf(2), 
				BigInteger.valueOf(3)
				));
		List<BigInteger> result1 = fetchApi.insertRequest(basicRequest);
		System.out.println("1. Basic request result size: " + result1.size());

		// Test with large numbers
		IntFetchRequest largeIntRequest = new IntFetchRequest(new BigInteger("123456789012345678901234567890"));
		List<BigInteger> result2 = fetchApi.insertRequest(largeIntRequest);
		System.out.println("2. Large int request result: " + result2.get(0));

		// Test with very large numbers
		IntFetchRequest veryLargeRequest = new IntFetchRequest(
				new BigInteger("99999999999999999999999999999999999999999999999999999999999999999999999999999999")
				);
		List<BigInteger> result3 = fetchApi.insertRequest(veryLargeRequest);
		System.out.println("3. Very large request result digits: " + result3.get(0).toString().length());

		StringFetchRequest stringRequest = new StringFetchRequest("test");
		List<BigInteger> result4 = fetchApi.insertRequest(stringRequest);
		System.out.println("4. String request result size: " + result4.size());

		ListFetchRequest listRequest = new ListFetchRequest(Arrays.asList(
				BigInteger.valueOf(7), 
				BigInteger.valueOf(8), 
				BigInteger.valueOf(9)
				));
		List<BigInteger> result5 = fetchApi.insertRequest(listRequest);
		System.out.println("5. List request result size: " + result5.size());

		// Test validation
		boolean valid1 = fetchApi.validateNumber(new BigInteger("1000000000000000000000000"));
		System.out.println("6. Validation for 10^24: " + valid1);

		boolean valid2 = fetchApi.validateNumber(new BigInteger("-5"));
		System.out.println("7. Validation for -5: " + valid2);

		// Test with extremely large number
		BigInteger extremelyLarge = new BigInteger("1".repeat(1000)); // 1000-digit number
		boolean valid3 = fetchApi.validateNumber(extremelyLarge);
		System.out.println("8. Validation for 1000-digit number: " + valid3);

		System.out.println("=== Fetch API Prototype Demo Complete ===");
	}
}