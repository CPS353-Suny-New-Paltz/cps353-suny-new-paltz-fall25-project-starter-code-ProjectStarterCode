package computing;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import numberlettercountcomputing.ComputingApiImpl;
import numberlettercountcomputing.PassData;
import numberlettercountdatastoring.DataStoreApi;
import numberlettercountfetching.FetchApi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

public class TestComputingApi {

	@Test
	public void testInitializeAndCompute() {
		// Create mock dependencies
		FetchApi mockFetchApi = Mockito.mock(FetchApi.class);
		DataStoreApi mockDataStoreApi = Mockito.mock(DataStoreApi.class);

		// Create implementation with mocked dependencies
		ComputingApiImpl computingApi = new ComputingApiImpl();
		computingApi.setFetchApi(mockFetchApi);
		computingApi.setDataStoreApi(mockDataStoreApi);

		// Test data
		List<Integer> inputData = Arrays.asList(1, 2, 3);
		String result = computingApi.initalize(inputData);
		String expected = "one,two,three";

		assertEquals(expected, result);
	}

	@Test
	public void testInitializeWithEmptyList() {
		ComputingApiImpl computingApi = new ComputingApiImpl();
		List<Integer> inputData = Arrays.asList();
		String result = computingApi.initalize(inputData);

		assertEquals("", result);
	}

	@Test
	public void testInitializeWithNumbersOutOfRange() {
		ComputingApiImpl computingApi = new ComputingApiImpl();
		List<Integer> inputData = Arrays.asList(1, 15, 3); // 15 is out of 0-9 range
		String result = computingApi.initalize(inputData);
		String expected = "one,15,three";

		assertEquals(expected, result);
	}

	@Test
	public void testPassDataCreation() {
		ComputingApiImpl computingApi = new ComputingApiImpl();

		// Test passData() method - USING PassData import
		PassData passData = computingApi.passData();
		assertNotNull(passData);
		assertEquals("computing module", passData.getFromComponent());
		assertEquals("storage module", passData.getToComponent());
	}

	@Test
	public void testPassDataWithNumber() {
		ComputingApiImpl computingApi = new ComputingApiImpl();

		// Test passData(int number) method - USING PassData import
		PassData passData = computingApi.passData(5);
		assertNotNull(passData);
		assertEquals("five", passData.getData());
		assertEquals("number_converter", passData.getFromComponent());
		assertEquals("output_processor", passData.getToComponent());
	}

	@Test
	public void testProcessPassData() {
		ComputingApiImpl computingApi = new ComputingApiImpl();

		// Create a PassData object - USING PassData import
		PassData passData = new PassData();
		passData.setData("test data");
		passData.setFromComponent("source");
		passData.setToComponent("destination");

		// Test processPassData method
		List<Integer> result = computingApi.processPassData(passData);
		assertNotNull(result);

		// Should contain letter count (8 for "test data") and lengths of from/to components
		assertEquals(8, result.get(0)); // "test data" has 8 letters (excluding space)
		assertEquals(6, result.get(1)); // "source" has 6 letters
		assertEquals(11, result.get(2)); // "destination" has 11 letters
	}

	@Test
	public void testComputeMethod() {
		ComputingApiImpl computingApi = new ComputingApiImpl();
		List<Integer> result = computingApi.compute();

		assertEquals(Arrays.asList(1, 2, 3), result);
	}

	@Test
	public void testWriteResult() {
		ComputingApiImpl computingApi = new ComputingApiImpl();
		String result = computingApi.writeResult("test data", ",");

		assertEquals("Processed: test data with delimiters: ,", result);
	}
}