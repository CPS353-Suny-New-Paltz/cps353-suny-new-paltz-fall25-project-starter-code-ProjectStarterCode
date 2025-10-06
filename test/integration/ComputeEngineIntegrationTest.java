package integration;

import org.junit.jupiter.api.Test;

import configuration.TestInputConfiguration;
import configuration.TestOutputConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.util.Arrays;
import java.util.List;

import numberlettercountcomputing.ComputingApiImpl;

import inmemory.InMemoryDataStore;

public class ComputeEngineIntegrationTest {

	@Test
	public void testIntegrationWithInputData() {
		// Setup test configuration
		List<Integer> inputNumbers = Arrays.asList(1, 10, 25);
		TestInputConfiguration inputConfig = new TestInputConfiguration(inputNumbers);
		TestOutputConfiguration outputConfig = new TestOutputConfiguration();

		// Create in-memory data store
		InMemoryDataStore dataStore = new InMemoryDataStore(inputConfig, outputConfig);

		// Create computing API implementation
		ComputingApiImpl computingApi = new ComputingApiImpl();
		// Note: We'll need to configure the dataStoreApi dependency

		// For now, directly use the data store to simulate the integration
		// This test will fail because we're not actually computing number-to-letter conversion
		dataStore.insertRequest(null);

		// Validate output - this will fail because we're just converting numbers to strings
		List<String> output = outputConfig.getOutputStrings();

		// Expected: ["one", "ten", "twenty-five"] but currently getting ["1", "10", "25"]
		assertFalse(output.isEmpty(), "Output should not be empty");
		assertEquals(3, output.size(), "Should have 3 output entries");

		// This assertion will fail until proper number-to-letter conversion is implemented as show in line 36 explaining how it should be.
		assertEquals("one", output.get(0));
		assertEquals("ten", output.get(1)); 
		assertEquals("twenty-five", output.get(2));
	}
}
