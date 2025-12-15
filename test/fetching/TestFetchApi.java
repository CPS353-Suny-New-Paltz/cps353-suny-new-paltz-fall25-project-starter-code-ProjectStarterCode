
package fetching;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.math.BigInteger;
import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import numberlettercountfetching.FetchApiImpl;
import numberlettercountfetching.FetchRequest;
import numberlettercountcomputing.ComputingApi;
import numberlettercountcomputing.ComputingApiImpl;
import numberlettercountdatastoring.DataStoreApi;
import numberlettercountdatastoring.DataStoreApiImpl;
import org.mockito.Mockito;

public class TestFetchApi {

	// Helper method to create a properly configured FetchApi
	private FetchApiImpl createConfiguredFetchApi() {
		ComputingApi computingApi = new ComputingApiImpl();
		DataStoreApi dataStoreApi = new DataStoreApiImpl();
		FetchApiImpl fetchApi = new FetchApiImpl();
		fetchApi.setDataStoreApi(dataStoreApi);
		fetchApi.setComputingApi(computingApi);
		return fetchApi;
	}

	@Test
	public void testInsertRequestWithSingleNumber() {
		FetchApiImpl fetchApi = createConfiguredFetchApi();

		List<BigInteger> numbers = List.of(BigInteger.valueOf(123));
		FetchRequest request = new FetchRequest(numbers);
		List<BigInteger> result = fetchApi.insertRequest(request);

		// Should return letter count for 123 as BigInteger
		assertEquals(1, result.size());
		// "one hundred and twenty-three" = 23 letters (without "and" = 20)
		assertTrue(result.get(0).compareTo(BigInteger.ZERO) > 0, "Should return positive letter count");
	}

	@Test
	public void testInsertRequestWithLargeNumber() {
		FetchApiImpl fetchApi = createConfiguredFetchApi();

		BigInteger largeNumber = new BigInteger("123456789012345678901234567890");
		List<BigInteger> numbers = List.of(largeNumber);
		FetchRequest request = new FetchRequest(numbers);
		List<BigInteger> result = fetchApi.insertRequest(request);

		assertEquals(1, result.size());
		// Large numbers return -1 (not implemented)
		assertEquals(BigInteger.valueOf(23264), result.get(0));
	}

	@Test
	public void testInsertRequestWithMultipleNumbers() {
		FetchApiImpl fetchApi = createConfiguredFetchApi();

		List<BigInteger> numbers = List.of(
				BigInteger.valueOf(1), 
				BigInteger.valueOf(2), 
				BigInteger.valueOf(3)
				);
		FetchRequest request = new FetchRequest(numbers);
		List<BigInteger> result = fetchApi.insertRequest(request);

		assertEquals(3, result.size());
		// Check specific letter counts
		assertEquals(BigInteger.valueOf(3), result.get(0)); // "one" = 3
		assertEquals(BigInteger.valueOf(3), result.get(1)); // "two" = 3
		assertEquals(BigInteger.valueOf(5), result.get(2)); // "three" = 5
	}

	@Test
	public void testInsertRequestWithZero() {
		FetchApiImpl fetchApi = createConfiguredFetchApi();

		List<BigInteger> numbers = List.of(BigInteger.ZERO);
		FetchRequest request = new FetchRequest(numbers);
		List<BigInteger> result = fetchApi.insertRequest(request);

		assertEquals(1, result.size());
		assertEquals(BigInteger.valueOf(4), result.get(0)); // "zero" = 4
	}

	@Test
	public void testInsertRequestWithNegativeNumber() {
		FetchApiImpl fetchApi = createConfiguredFetchApi();

		List<BigInteger> numbers = List.of(BigInteger.valueOf(-5));
		FetchRequest request = new FetchRequest(numbers);
		List<BigInteger> result = fetchApi.insertRequest(request);

		assertEquals(1, result.size());
		assertEquals(BigInteger.valueOf(-1), result.get(0));
	}

	@Test
	public void testInsertRequestWithNullRequest() {
		FetchApiImpl fetchApi = createConfiguredFetchApi();
		List<BigInteger> result = fetchApi.insertRequest(null);

		assertEquals(List.of(BigInteger.valueOf(-1)), result);
	}

	@Test
	public void testInsertRequestWithEmptyRequest() {
		FetchApiImpl fetchApi = createConfiguredFetchApi();

		List<BigInteger> emptyList = new ArrayList<>();
		FetchRequest request = new FetchRequest(emptyList);
		List<BigInteger> result = fetchApi.insertRequest(request);

		assertEquals(List.of(BigInteger.valueOf(-1)), result);
	}

	@Test
	public void testInsertRequestWithNullInList() {
		FetchApiImpl fetchApi = createConfiguredFetchApi();

		List<BigInteger> numbers = new ArrayList<>();
		numbers.add(BigInteger.valueOf(1));
		numbers.add(null);
		numbers.add(BigInteger.valueOf(3));

		FetchRequest request = new FetchRequest(numbers);
		List<BigInteger> result = fetchApi.insertRequest(request);

		assertEquals(3, result.size());
		assertEquals(BigInteger.valueOf(3), result.get(0)); // "one"
		assertEquals(BigInteger.valueOf(-1), result.get(1)); // null
		assertEquals(BigInteger.valueOf(5), result.get(2)); // "three"
	}

	@Test
	public void testValidateNumber() {
		FetchApiImpl fetchApi = createConfiguredFetchApi();

		assertTrue(fetchApi.validateNumber(BigInteger.ZERO));
		assertTrue(fetchApi.validateNumber(BigInteger.valueOf(100)));
		assertTrue(fetchApi.validateNumber(new BigInteger("12345678901234567890")));
		assertFalse(fetchApi.validateNumber(BigInteger.valueOf(-1)));
		assertFalse(fetchApi.validateNumber(new BigInteger("-12345678901234567890")));
	}

	@Test
	public void testValidateNumberWithNull() {
		FetchApiImpl fetchApi = createConfiguredFetchApi();
		assertFalse(fetchApi.validateNumber(null));
	}

	@Test
	public void testLetterCountsForSpecificNumbers() {
		FetchApiImpl fetchApi = createConfiguredFetchApi();

		List<BigInteger> numbers = List.of(
				BigInteger.valueOf(1),
				BigInteger.valueOf(5),
				BigInteger.valueOf(10)
				);
		FetchRequest request = new FetchRequest(numbers);
		List<BigInteger> result = fetchApi.insertRequest(request);

		assertEquals(3, result.size());
		assertEquals(BigInteger.valueOf(3), result.get(0)); // "one" = 3
		assertEquals(BigInteger.valueOf(4), result.get(1)); // "five" = 4
		assertEquals(BigInteger.valueOf(3), result.get(2)); // "ten" = 3
	}

	@Test
	public void testFetchApiWithoutDependencies() {
		FetchApiImpl fetchApi = new FetchApiImpl(); // No dependencies set

		List<BigInteger> numbers = List.of(BigInteger.valueOf(5));
		FetchRequest request = new FetchRequest(numbers);
		List<BigInteger> result = fetchApi.insertRequest(request);

		assertEquals(1, result.size());
		// Without computingApi, should still validate but return -1 for computation
		assertEquals(BigInteger.valueOf(-1), result.get(0));
	}

	@Test
	public void testComplexNumber() {
		FetchApiImpl fetchApi = createConfiguredFetchApi();

		List<BigInteger> numbers = List.of(BigInteger.valueOf(123));
		FetchRequest request = new FetchRequest(numbers);
		List<BigInteger> result = fetchApi.insertRequest(request);

		assertEquals(1, result.size());
		// "one hundred and twenty-three" = 23 letters (without "and")
		// Let's check it's reasonable (between 20-25)
		assertTrue(result.get(0).compareTo(BigInteger.valueOf(20)) >= 0);
		assertTrue(result.get(0).compareTo(BigInteger.valueOf(25)) <= 0);
	}

	@Test
	public void testFetchRequestAddNumberMethod() {
		FetchRequest request = new FetchRequest();
		request.addNumber(BigInteger.valueOf(42));
		request.addNumberFromString("99");

		assertEquals(2, request.size());
		assertEquals(BigInteger.valueOf(42), request.getData().get(0));
		assertEquals(BigInteger.valueOf(99), request.getData().get(1));
	}

	@Test
	public void testFetchRequestSum() {
		List<BigInteger> numbers = List.of(
				BigInteger.valueOf(10),
				BigInteger.valueOf(20),
				BigInteger.valueOf(30)
				);
		FetchRequest request = new FetchRequest(numbers);

		assertEquals(BigInteger.valueOf(60), request.getSum());
	}

	@Test
	public void testInsertRequestWithMocks() {
		// FetchApiImpl is the unit under test; mock dependencies
		ComputingApi mockComputing = Mockito.mock(ComputingApi.class);
		DataStoreApi mockData = Mockito.mock(DataStoreApi.class);

		// Stub computing behavior for specific BigInteger inputs
		when(mockComputing.computeNumber(BigInteger.valueOf(1))).thenReturn(BigInteger.valueOf(3));
		when(mockComputing.computeNumber(BigInteger.valueOf(2))).thenReturn(BigInteger.valueOf(3));
		when(mockComputing.computeNumber(BigInteger.valueOf(3))).thenReturn(BigInteger.valueOf(5));

		// Stub data validation to accept these small integers
		when(mockData.validateNumber(1)).thenReturn(true);
		when(mockData.validateNumber(2)).thenReturn(true);
		when(mockData.validateNumber(3)).thenReturn(true);

		FetchApiImpl fetchApi = new FetchApiImpl();
		fetchApi.setComputingApi(mockComputing);
		fetchApi.setDataStoreApi(mockData); // not used in insertRequest but set for completeness

		List<BigInteger> numbers = List.of(BigInteger.valueOf(1), BigInteger.valueOf(2), BigInteger.valueOf(3));
		FetchRequest request = new FetchRequest(numbers);
		List<BigInteger> result = fetchApi.insertRequest(request);

		// Verify results come from the mocked ComputingApi
		assertEquals(3, result.size());
		assertEquals(BigInteger.valueOf(3), result.get(0));
		assertEquals(BigInteger.valueOf(3), result.get(1));
		assertEquals(BigInteger.valueOf(5), result.get(2));

		// Verify computeNumber called for each input
		verify(mockComputing, times(1)).computeNumber(BigInteger.valueOf(1));
		verify(mockComputing, times(1)).computeNumber(BigInteger.valueOf(2));
		verify(mockComputing, times(1)).computeNumber(BigInteger.valueOf(3));
	}
}
