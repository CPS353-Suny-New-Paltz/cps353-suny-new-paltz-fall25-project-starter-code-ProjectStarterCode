package integration;

import apiimplementation.ConceptualAPIImpl;
import apinetwork.ComputationInput;
import apinetwork.Delimiters;
import org.junit.jupiter.api.Test;
import testhelpers.TestInputConfig;
import testhelpers.TestOutputConfig;
import testhelpers.TestProcessDataStore;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComputeEngineIntegrationTest {

    @Test
    public void integration_computeEngine_writesExpectedFactorizations() {
        TestInputConfig inputConfig = new TestInputConfig(Arrays.asList(1, 10, 25));
        TestOutputConfig outputConfig = new TestOutputConfig();
        TestProcessDataStore testStore = new TestProcessDataStore(inputConfig, outputConfig);

        //Instantiate your Conceptual API implementation and inject the test store
        ConceptualAPIImpl conceptual = new ConceptualAPIImpl(testStore);
        //The constructor ConceptualAPIImpl(TestProcessDataStore) is undefined
        
        for (Integer n : testStore.readInputs()) {
            conceptual.compute(new ComputationInput(n, (Delimiters) null));
        }

        // Assert: validate what was written to the test output matches expected factorization strings
        List<String> outputs = outputConfig.getOutputs();

        List<String> expected = Arrays.asList(
                "1",
                "2 × 5",
                "5 × 5"
        );

        // Verify size first
        assertEquals(expected.size(), outputs.size(),
                "Number of outputs written should match number of inputs");

        // Verify content (order matters: same order as inputs)
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), outputs.get(i),
                    "Output mismatch at index " + i + " (input=" + inputConfig.getInputs().get(i) + ")");
        }
    }
}
