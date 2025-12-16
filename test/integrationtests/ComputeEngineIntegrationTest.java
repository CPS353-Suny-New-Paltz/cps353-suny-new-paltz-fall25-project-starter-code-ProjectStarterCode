package integrationtests;

import org.junit.jupiter.api.Test;
import project.checkpoint4.ComputationComponent;

import java.nio.file.Path;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ComputeEngineIntegrationTest {

    @Test
    public void testComputeEngineIntegration() throws Exception {

        // Step 1: Create an in-memory storage mock
        InMemoryStorageAPI storage = new InMemoryStorageAPI();

        // Step 2: Provide input to mock storage
        storage.setMockInput(List.of(10));   // input: compute primes < 10

        // Step 3: Create the computation engine
        ComputationComponent computeEngine = new ComputationComponent();

        // Step 4: Simulate coordination process
        int limit = storage.parseInput(storage.readInput(Path.of("fake.txt")));
        assertEquals(10, limit, "Limit should be parsed correctly.");

        List<Integer> results = computeEngine.compute(limit);
        assertEquals(List.of(2, 3, 5, 7), results,
                "Prime computation should return primes less than 10.");

        String formatted = storage.formatOutput(results);
        assertEquals("[2, 3, 5, 7]", formatted,
                "Formatted output should match expected prime list.");

        boolean written = storage.writeOutput(Path.of("output.txt"), formatted);
        assertTrue(written, "writeOutput should return true for mock storage.");

        // Step 5: Ensure the storage actually received the output
        assertEquals("[2, 3, 5, 7]", storage.getStoredFormattedOutput(),
                "Output should be stored correctly in mock storage.");

        System.out.println("Integration test passed: Engine + Storage pipeline works.");
    }
}