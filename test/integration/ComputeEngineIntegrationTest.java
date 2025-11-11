package integration;

import org.junit.jupiter.api.Test;

import configuration.TestInputConfiguration;
import configuration.TestOutputConfiguration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import numberlettercountfetching.Delimiters;
import numberlettercountfetching.Display;
import numberlettercountfetching.FetchApiImpl;
import numberlettercountfetching.FetchRequest;
import numberlettercountfetching.InputSource;
import numberlettercountfetching.ListFetchRequest;
import numberlettercountfetching.OutputResult;
import numberlettercountfetching.PassData;
import numberlettercountfetching.StoreFetch;
import numberlettercountfetching.StringFetchRequest;
import numberlettercountfetching.IntFetchRequest;
import inmemory.InMemoryDataStore;

public class ComputeEngineIntegrationTest {

	@Test
	public void testIntegrationWithFetchApiOnly() {
		// Setup test configuration with empty list
		List<Integer> inputNumbers = Arrays.asList(); // Empty list - no values
		TestInputConfiguration inputConfig = new TestInputConfiguration(inputNumbers);
		TestOutputConfiguration outputConfig = new TestOutputConfiguration();

		// Create in-memory data store
		InMemoryDataStore dataStore = new InMemoryDataStore(inputConfig, outputConfig);

		// Create FetchApi only
		FetchApiImpl networkingApi = new FetchApiImpl();
		networkingApi.setDataStoreApi(dataStore);

		// Test all types of fetch requests with values that won't be stored
		FetchRequest intRequest = new IntFetchRequest(5);
		FetchRequest listRequest = new ListFetchRequest(inputNumbers); // Empty list
		FetchRequest stringRequest = new StringFetchRequest("hello");

		// Insert all types of requests - these should return empty or failure results
		List<Integer> intResult = networkingApi.insertRequest(intRequest);
		List<Integer> listResult = networkingApi.insertRequest(listRequest);
		List<Integer> stringResult = networkingApi.insertRequest(stringRequest);

		// These assertions will FAIL because there are no values
		assertFalse(intResult.isEmpty(), "Integer request should return results - BUT WILL FAIL");
		assertFalse(listResult.isEmpty(), "List request should return results - BUT WILL FAIL"); 
		assertFalse(stringResult.isEmpty(), "String request should return results - BUT WILL FAIL");

		// Store the fetched data (which should be empty)
		StoreFetch storeFetch = new StoreFetch(networkingApi.getStoredData());

		// Use all FetchApi methods only
		InputSource source = networkingApi.getInputSource();
		OutputResult output = networkingApi.getOutputResult();
		Delimiters delims = networkingApi.getDelimiters();
		Display display = networkingApi.displayIt();
		PassData passDataFromFetchApi = networkingApi.inputSource();

		// Verify all FetchApi components are available
		assertNotNull(source, "InputSource should not be null");
		assertNotNull(output, "OutputResult should not be null");
		assertNotNull(delims, "Delimiters should not be null");
		assertNotNull(display, "Display should not be null");
		assertNotNull(passDataFromFetchApi, "PassData from FetchApi should not be null");

		// Verify data was stored properly through FetchApi - THIS WILL FAIL of now before checkpoint 4!
		List<Integer> storedValues = storeFetch.getStoredValues();
		assertFalse(storedValues.isEmpty(), "Should have stored values from all requests - BUT WILL FAIL");
		assertTrue(storedValues.size() >= 3, "Should have stored all inserted data - BUT WILL FAIL");

		// Verify the integration works with FetchApi only - THESE WILL FAIL
		assertTrue(storedValues.contains(5), "Should contain the integer request value - BUT WILL FAIL");
		assertTrue(storedValues.containsAll(inputNumbers), "Should contain all list values - BUT WILL FAIL");

		// This loop won't execute because storedValues is empty
		for (Integer number : storedValues) {
			System.out.println("FetchApi stored number: " + number);
		}

		// Use all FetchApi components
		System.out.println("InputSource available: " + source.getClass().getSimpleName());
		System.out.println("OutputResult available: " + output.getClass().getSimpleName());
		System.out.println("Delimiters available: " + delims.getClass().getSimpleName());
		System.out.println("Display available: " + display.getClass().getSimpleName());
		System.out.println("PassData available: " + passDataFromFetchApi.getClass().getSimpleName());

		// Final verification that will FAIL
		assertTrue(storedValues.size() >= 3, "FetchApi should handle multiple request types - BUT WILL FAIL");
		System.out.println("FetchApi integration test completed successfully with " + storedValues.size() + " stored values");
	}
}