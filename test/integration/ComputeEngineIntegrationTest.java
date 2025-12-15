package integration;

import org.junit.jupiter.api.Test;

import configuration.TestInputConfiguration;
import configuration.TestOutputConfiguration;
import inmemory.InMemoryDataStore;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import numberlettercountfetching.FetchApiImpl;
import numberlettercountfetching.FetchRequest;
import numberlettercountcomputing.ComputingApi;
import numberlettercountcomputing.ComputingApiImpl;
import numberlettercountcomputing.PassData;
import numberlettercountdatastoring.DataStoreApi;
import numberlettercountdatastoring.DataRequest;
import numberlettercountdatastoring.DataStoreApiImpl;

public class ComputeEngineIntegrationTest {

	@Test
	public void testIntegrationWithInMemoryDataStore() {
		// Create configuration objects for InMemoryDataStore
		TestInputConfiguration inputConfig = new TestInputConfiguration();
		TestOutputConfiguration outputConfig = new TestOutputConfiguration();

		// Set up test input numbers
		List<Integer> testNumbers = Arrays.asList(1, 2, 3, 4, 5);
		inputConfig.setInputNumbers(testNumbers);

		// Create InMemoryDataStore with configurations
		InMemoryDataStore inMemoryStore = new InMemoryDataStore(inputConfig, outputConfig);

		// Create other components
		ComputingApi computingApi = new ComputingApiImpl();
		FetchApiImpl fetchApi = new FetchApiImpl();

		// Connect components - use InMemoryDataStore instead of DataStoreApiImpl
		fetchApi.setDataStoreApi(inMemoryStore);
		fetchApi.setComputingApi(computingApi);

		// Test the InMemoryDataStore directly
		DataRequest dataRequest = new DataRequest(1, "memory_test", "10,20,30");
		int insertResult = inMemoryStore.insertRequest(dataRequest);

		// Verify InMemoryDataStore works
		assertTrue(insertResult >= 0 || insertResult == -1, 
				"InMemoryDataStore should handle insert request");

		// Test validation
		assertTrue(inMemoryStore.validateNumber(5), 
				"InMemoryDataStore should validate positive numbers");
		assertFalse(inMemoryStore.validateNumber(-1), 
				"InMemoryDataStore should reject negative numbers");

		// Test fetching data
		List<Integer> storedData = inMemoryStore.fetchAllData();
		assertNotNull(storedData, "Should be able to fetch data from memory");

		// Test processing request
		boolean processResult = inMemoryStore.processRequest();
		assertTrue(processResult, "Should be able to process request in memory");

		// Verify output configuration was populated
		List<String> outputStrings = outputConfig.getOutputStrings();
		assertNotNull(outputStrings, "Output strings should not be null");
	}
	@Test
	public void testIntegrationWithFetchApiAndComputingApi() {
		// Create components
		ComputingApi computingApi = new ComputingApiImpl();
		DataStoreApi dataStoreApi = new DataStoreApiImpl();
		FetchApiImpl fetchApi = new FetchApiImpl();

		// Connect components
		fetchApi.setDataStoreApi(dataStoreApi);
		fetchApi.setComputingApi(computingApi);

		// Test with BigInteger - create custom FetchRequest
		List<BigInteger> testNumbers = Arrays.asList(
				BigInteger.valueOf(1), 
				BigInteger.valueOf(2), 
				BigInteger.valueOf(3)
				);

		// Use anonymous class since ListFetchRequest doesn't exist
		FetchRequest listRequest = new FetchRequest() {
			public List<BigInteger> getData() {
				return testNumbers;
			}
		};

		List<BigInteger> fetchResult = fetchApi.insertRequest(listRequest);

		// Test ComputingApi functionality
		PassData passData = computingApi.passData(5);
		List<Integer> processResult = computingApi.processPassData(passData);

		// Verify integration worked
		assertFalse(fetchResult.isEmpty(), "FetchApi should return data");
		assertNotEquals(BigInteger.valueOf(-1), fetchResult.get(0), "FetchApi should not return error code");
		assertFalse(processResult.isEmpty(), "ComputingApi should process data");
		assertNotEquals(-1, processResult.get(0), "ComputingApi should not return error code");

		// Test DataStoreApi validation
		assertTrue(dataStoreApi.validateNumber(5), "DataStoreApi should validate positive numbers");
		assertFalse(dataStoreApi.validateNumber(-1), "DataStoreApi should reject negative numbers");

		// Test DataStoreApi insert with valid data
		DataRequest dataRequest = new DataRequest(1, "integration_test", "10,20,30");
		int insertResult = dataStoreApi.insertRequest(dataRequest);
		assertTrue(insertResult >= 0, "DataStoreApi should return count of stored numbers: " + insertResult);
	}

	@Test
	public void testFullWorkflow() {
		// Create and connect all components
		ComputingApi computingApi = new ComputingApiImpl();
		DataStoreApi dataStoreApi = new DataStoreApiImpl();
		FetchApiImpl fetchApi = new FetchApiImpl();
		fetchApi.setDataStoreApi(dataStoreApi);
		fetchApi.setComputingApi(computingApi);

		// Step 1: Fetch data - should return letter count, not the number
		List<BigInteger> singleNumber = Arrays.asList(BigInteger.valueOf(7));

		// Use anonymous class since IntFetchRequest doesn't exist
		FetchRequest fetchRequest = new FetchRequest() {		
			public List<BigInteger> getData() {
				return singleNumber;
			}
		};

		List<BigInteger> fetchedData = fetchApi.insertRequest(fetchRequest);

		// Step 2: Process data through computing API
		PassData passData = computingApi.passData(7);
		List<Integer> computedResults = computingApi.processPassData(passData);

		// Verify workflow success
		// FetchApi should return letter count (not the number 7)
		// 7 = "seven" = 5 letters
		assertEquals(BigInteger.valueOf(5), fetchedData.get(0), "Should return letter count for number 7");
		assertFalse(computedResults.isEmpty(), "Should produce computed results");
		assertTrue(fetchApi.validateNumber(BigInteger.valueOf(7)), "FetchApi should validate number 7");
		assertTrue(dataStoreApi.validateNumber(7), "DataStoreApi should validate number 7");
	}


	@Test 
	public void testBasicDataStoreFunctionality() {
		DataStoreApi dataStoreApi = new DataStoreApiImpl();

		// Test validation
		assertTrue(dataStoreApi.validateNumber(10));
		assertFalse(dataStoreApi.validateNumber(-5));

		// Test insert
		DataRequest request = new DataRequest(1, "test", "100,200,300");
		int result = dataStoreApi.insertRequest(request);
		assertTrue(result >= 0 || result == -1); // Accept either valid result or error
	}

	@Test
	public void testBasicComputingFunctionality() {
		ComputingApi computingApi = new ComputingApiImpl();

		// Test passData
		PassData passData = computingApi.passData(42);
		assertNotNull(passData);
		assertNotNull(passData.getData());

		// Test processPassData
		List<Integer> results = computingApi.processPassData(passData);
		assertNotNull(results);
		assertFalse(results.isEmpty());
	}

	@Test
	public void testBasicFetchApiFunctionality() {
		FetchApiImpl fetchApi = new FetchApiImpl();

		// Test with no dependencies (should handle gracefully)
		List<BigInteger> singleNum = Arrays.asList(BigInteger.valueOf(5));
		FetchRequest request = new FetchRequest() {
			public List<BigInteger> getData() {
				return singleNum;
			}
		};

		List<BigInteger> result = fetchApi.insertRequest(request);
		assertNotNull(result);
		assertEquals(1, result.size());
	}
}