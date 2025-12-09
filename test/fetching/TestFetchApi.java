package fetching;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;


import numberlettercountfetching.FetchApiImpl;
import numberlettercountfetching.FetchRequest;
import numberlettercountfetching.IntFetchRequest;
import numberlettercountfetching.ListFetchRequest;
import numberlettercountfetching.StringFetchRequest;

// Need these for setting up dependencies
import numberlettercountcomputing.ComputingApi;
import numberlettercountcomputing.ComputingApiImpl;
import numberlettercountdatastoring.DataStoreApi;
import numberlettercountdatastoring.DataStoreApiImpl;

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
	public void testInsertRequestWithIntFetchRequest() {
		FetchApiImpl fetchApi = createConfiguredFetchApi();

		FetchRequest intRequest = new IntFetchRequest(123);
		List<Integer> result = fetchApi.insertRequest(intRequest);

		// Should return letter count for 123, not 123
		// 123 = "one hundred twenty-three" = letters count
		assertEquals(1, result.size()); // One result
		assertTrue(result.get(0) > 0, "Should return positive letter count");
	}

	@Test
	public void testInsertRequestWithListFetchRequest() {
		FetchApiImpl fetchApi = createConfiguredFetchApi();

		FetchRequest listRequest = new ListFetchRequest(List.of(1, 2, 3));
		List<Integer> result = fetchApi.insertRequest(listRequest);

		// Should return letter counts for [1, 2, 3], not [1, 2, 3]
		// 1 = "one" = 3 letters, 2 = "two" = 3 letters, 3 = "three" = 5 letters
		assertEquals(3, result.size());
		// All should be positive letter counts
		for (int count : result) {
			assertTrue(count > 0, "Should return positive letter count: " + count);
		}
	}

	@Test
	public void testInsertRequestWithStringFetchRequest() {
		FetchApiImpl fetchApi = createConfiguredFetchApi();

		FetchRequest stringRequest = new StringFetchRequest("abc");
		List<Integer> result = fetchApi.insertRequest(stringRequest);

		// StringFetchRequest converts "abc" to ASCII: [97, 98, 99]
		// These are then processed as numbers through ComputingApi
		// 97 = "ninety-seven" = letter count, etc.
		assertEquals(3, result.size());
		// All should be positive letter counts or -1 if invalid
		for (int i = 0; i < result.size(); i++) {
			int count = result.get(i);
			// Could be letter count (positive) or -1 (if ASCII value is invalid)
			assertTrue(count > 0 || count == -1, 
					"Result " + i + " should be positive letter count or -1, got: " + count);
		}
	}

	@Test
	public void testInsertRequestWithNull() {
		FetchApiImpl fetchApi = createConfiguredFetchApi();
		List<Integer> result = fetchApi.insertRequest(null);

		assertEquals(List.of(-1), result);
	}

	@Test
	public void testValidateNumber() {
		FetchApiImpl fetchApi = createConfiguredFetchApi();

		assertTrue(fetchApi.validateNumber(0));
		assertTrue(fetchApi.validateNumber(100));
		assertTrue(fetchApi.validateNumber(999));
		assertFalse(fetchApi.validateNumber(-1));
		assertFalse(fetchApi.validateNumber(-100));
	}

	@Test
	public void testGetStoredData() {
		FetchApiImpl fetchApi = createConfiguredFetchApi();

		// Initially empty
		List<Integer> emptyData = fetchApi.getStoredData();
		assertTrue(emptyData.isEmpty());

		// After inserting data - store original numbers
		fetchApi.insertRequest(new IntFetchRequest(42));
		List<Integer> data = fetchApi.getStoredData();

		assertEquals(1, data.size());
		assertEquals(42, data.get(0)); // getStoredData returns original numbers
	}

	@Test
	public void testGetStoredDataCount() {
		FetchApiImpl fetchApi = createConfiguredFetchApi();

		assertEquals(0, fetchApi.getStoredDataCount());

		fetchApi.insertRequest(new ListFetchRequest(List.of(1, 2, 3)));
		assertEquals(3, fetchApi.getStoredDataCount());
	}

	@Test
	public void testLetterCountsForSpecificNumbers() {
		FetchApiImpl fetchApi = createConfiguredFetchApi();

		// Test specific number conversions
		// 1 = "one" = 3 letters
		FetchRequest request1 = new IntFetchRequest(1);
		List<Integer> result1 = fetchApi.insertRequest(request1);
		assertEquals(1, result1.size());
		assertEquals(3, result1.get(0)); // "one" has 3 letters

		// 5 = "five" = 4 letters
		FetchRequest request5 = new IntFetchRequest(5);
		List<Integer> result5 = fetchApi.insertRequest(request5);
		assertEquals(1, result5.size());
		assertEquals(4, result5.get(0)); // "five" has 4 letters

		// 10 = "ten" = 3 letters
		FetchRequest request10 = new IntFetchRequest(10);
		List<Integer> result10 = fetchApi.insertRequest(request10);
		assertEquals(1, result10.size());
		assertEquals(3, result10.get(0)); // "ten" has 3 letters
	}


	@Test
	public void testFetchApiWithoutDependencies() {
		// Test that FetchApi returns -1 when dependencies aren't set
		FetchApiImpl fetchApi = new FetchApiImpl(); // No dependencies set

		FetchRequest request = new IntFetchRequest(5);
		List<Integer> result = fetchApi.insertRequest(request);

		// Should return -1 because no ComputingApi to get letter counts
		assertEquals(List.of(-1), result);
	}
}