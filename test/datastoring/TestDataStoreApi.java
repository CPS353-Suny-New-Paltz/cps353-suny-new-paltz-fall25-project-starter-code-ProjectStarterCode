package datastoring;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
		// Note: We'll need to add setter methods or package-private access for computingApi

		// Test will fail because implementation returns empty string
		DataRequest request = new DataRequest(123);
		int result = dataStoreApi.insertRequest(request);

		// This test will fail as expected
		assertEquals("SUCCESS",result);
	}
}