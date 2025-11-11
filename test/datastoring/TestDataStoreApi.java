package datastoring;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import numberlettercountcomputing.ComputingApi;
import numberlettercountdatastoring.DataRequest;
import numberlettercountdatastoring.DataStoreApiImpl;

public class TestDataStoreApi {

	@Test
	public void testInsertRequest() {
		// Create mock dependencies
		ComputingApi mockComputingApi = Mockito.mock(ComputingApi.class);

		// Create implementation with mocked dependency
		DataStoreApiImpl dataStoreApi = new DataStoreApiImpl();
		dataStoreApi.setComputingApi(mockComputingApi);

		// Test data
		DataRequest request = new DataRequest(123, "test_source", "test_data");
		int result = dataStoreApi.insertRequest(request);

		// Should return 0 for success, -1 for failure
		assertEquals(0, result); // Now returns 0 for success instead of -1

		assertTrue(result >= 0, "Insert request should return success code (>= 0)");
	}

	@Test
	public void testInsertRequestWithNull() {
		DataStoreApiImpl dataStoreApi = new DataStoreApiImpl();
		int result = dataStoreApi.insertRequest(null);

		assertEquals(-1, result); // Should return -1 for null request
	}

	@Test
	public void testSerializingData() {
		DataStoreApiImpl dataStoreApi = new DataStoreApiImpl();
		assertNotNull(dataStoreApi.serializingData());
	}

	@Test
	public void testFormatData() {
		DataStoreApiImpl dataStoreApi = new DataStoreApiImpl();
		assertNotNull(dataStoreApi.formatData());
	}

	@Test
	public void testResult() {
		DataStoreApiImpl dataStoreApi = new DataStoreApiImpl();
		assertNotNull(dataStoreApi.result());
	}

	@Test
	public void testStoreData() {
		DataStoreApiImpl dataStoreApi = new DataStoreApiImpl();
		boolean result = dataStoreApi.storeData("test data", "test_location");

		assertEquals(true, result); // Should return true for successful storage
	}
}