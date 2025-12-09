package datastoring;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;


import numberlettercountdatastoring.DataRequest;
import numberlettercountdatastoring.DataStoreApi;
import numberlettercountdatastoring.DataStoreApiImpl;

public class TestDataStoreApi {

	@Test
	public void testInsertRequest() {
		DataStoreApi dataStoreApi = new DataStoreApiImpl();

		DataRequest request = new DataRequest(123, "test_source", "1,2,3");
		int result = dataStoreApi.insertRequest(request);

		// Should return count of stored numbers (3)
		assertEquals(3, result);
	}

	@Test
	public void testInsertRequestWithSingleNumber() {
		DataStoreApi dataStoreApi = new DataStoreApiImpl();

		DataRequest request = new DataRequest(123, "test_source", "42");
		int result = dataStoreApi.insertRequest(request);

		assertEquals(1, result);
	}

	@Test
	public void testInsertRequestWithNegativeNumbers() {
		DataStoreApi dataStoreApi = new DataStoreApiImpl();

		DataRequest request = new DataRequest(123, "test_source", "1,-2,3");
		int result = dataStoreApi.insertRequest(request);

		// Should only store positive numbers (1 and 3)
		assertEquals(2, result);
	}

	@Test
	public void testInsertRequestWithNull() {
		DataStoreApi dataStoreApi = new DataStoreApiImpl();
		int result = dataStoreApi.insertRequest(null);
		assertEquals(-1, result);
	}

	@Test
	public void testInsertRequestWithEmptyData() {
		DataStoreApi dataStoreApi = new DataStoreApiImpl();
		DataRequest request = new DataRequest(123, "test_source", "");
		int result = dataStoreApi.insertRequest(request);
		assertEquals(-1, result);
	}

	@Test
	public void testInsertRequestWithInvalidDataFormat() {
		DataStoreApi dataStoreApi = new DataStoreApiImpl();
		DataRequest request = new DataRequest(123, "test_source", "invalid,data,here");
		int result = dataStoreApi.insertRequest(request);
		assertEquals(-1, result); // No valid numbers to store
	}

	@Test
	public void testInsertRequestWithMixedValidInvalidData() {
		DataStoreApi dataStoreApi = new DataStoreApiImpl();
		DataRequest request = new DataRequest(123, "test_source", "10,invalid,20,notnumber,30");
		int result = dataStoreApi.insertRequest(request);
		assertEquals(3, result); // Should store 10, 20, 30
	}

	@Test
	public void testValidateNumber() {
		DataStoreApi dataStoreApi = new DataStoreApiImpl();
		assertTrue(dataStoreApi.validateNumber(5));
		assertTrue(dataStoreApi.validateNumber(0));
		assertFalse(dataStoreApi.validateNumber(-1));
	}

	@Test
	public void testValidateNumberLargeNumber() {
		DataStoreApi dataStoreApi = new DataStoreApiImpl();
		assertTrue(dataStoreApi.validateNumber(1000000));
		assertTrue(dataStoreApi.validateNumber(Integer.MAX_VALUE));
	}

	@Test
	public void testFetchAllData() {
		DataStoreApiImpl dataStoreApi = new DataStoreApiImpl();
		DataRequest request = new DataRequest(1, "source", "10,20");
		dataStoreApi.insertRequest(request);

		List<Integer> result = dataStoreApi.getStoredNumbers();
		assertEquals(2, result.size());
		assertTrue(result.contains(10));
		assertTrue(result.contains(20));
	}

	@Test
	public void testFetchAllDataMultipleRequests() {
		DataStoreApiImpl dataStoreApi = new DataStoreApiImpl();

		dataStoreApi.insertRequest(new DataRequest(1, "source1", "1,2,3"));
		dataStoreApi.insertRequest(new DataRequest(2, "source2", "4,5"));
		dataStoreApi.insertRequest(new DataRequest(3, "source3", "6"));

		List<Integer> result = dataStoreApi.getStoredNumbers();
		assertEquals(6, result.size());
		assertTrue(result.containsAll(List.of(1, 2, 3, 4, 5, 6)));
	}

	@Test
	public void testGetStoredDataCount() {
		DataStoreApiImpl dataStoreApi = new DataStoreApiImpl();
		dataStoreApi.insertRequest(new DataRequest(1, "source", "1,2,3,4,5"));
		assertEquals(5, dataStoreApi.getStoredNumbers().size());
	}

	@Test
	public void testClearStorage() {
		DataStoreApiImpl dataStoreApi = new DataStoreApiImpl();
		dataStoreApi.insertRequest(new DataRequest(1, "source", "1,2,3"));
		assertEquals(3, dataStoreApi.getStoredNumbers().size());

		DataStoreApiImpl freshInstance = new DataStoreApiImpl();
		assertTrue(freshInstance.getStoredNumbers().isEmpty());
	}
}