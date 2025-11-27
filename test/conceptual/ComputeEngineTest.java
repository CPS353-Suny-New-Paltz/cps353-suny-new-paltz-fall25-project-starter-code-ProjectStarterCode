package conceptual;

import org.junit.jupiter.api.Test;
import process.DataValue;
import process.DataValueImpl;
import integrationinfra.InMemoryInputConfig;
import integrationinfra.InMemoryOutputConfig;
import network.UserComputeAPI;
import network.UserComputeImpl;
import integrationinfra.InMemoryDataStore;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComputeEngineTest {

	@Test
	void testCompute() {
		List<Integer> input = new ArrayList<>(Arrays.asList(1, 10, 25));
		List<String> output = new ArrayList<>();

		InMemoryInputConfig inputConfig = new InMemoryInputConfig(input);
		InMemoryOutputConfig outputConfig = new InMemoryOutputConfig(output);

		InMemoryDataStore store = new InMemoryDataStore(inputConfig, outputConfig);

		ComputeEngineAPI engine = new ComputeEngineImpl();
		UserComputeAPI user = new UserComputeImpl(engine, store);
		
		int maxIterations = 1000;
		int count = 0;

		while (!inputConfig.getInput().isEmpty() && count++ < maxIterations) {
			DataValue val = store.readInput("input");
			if (val == null || val.getValue() == -1) {
				break;
			}
			
			ComputeResult result = engine.performComputation(() -> val.getValue());
			DataValue outputVal = new DataValueImpl(result.getOutput());
			store.writeOutput("output", outputVal);
		}
		assertFalse(output.isEmpty(), "Output should not be empty");
		assertTrue(output.stream().anyMatch(s -> s.contains("Value")), "Output should contain formatted values");
	}

}