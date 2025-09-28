package test.integration;

import api.StorageComputeAPI;
import api.implementation.StorageComputeImp;
import api.implementation.ComputeEngineImp;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import java.util.Arrays;

/**
 * Integration test for compute engine components using test-only data store.
 */
public class ComputeEngineIntegration {

    @Test
    void integrationTestWithRealImplementations() {
        // Setup: Use real implementations (no mocks)
        ComputeEngineImp computeEngine = new ComputeEngineImp();
        StorageComputeIntegration testDataStore = new StorageComputeIntegration(computeEngine);
        
        // Configure test data store with input [1,10,25]
        testDataStore.addTestFile("input.txt", new int[]{1, 10, 25});
        
        // Execute: Use StorageComputeImp with test data store
        StorageComputeImp storageCompute = new StorageComputeImp(testDataStore);
        
        // Perform the complete workflow
        int[] numbers = storageCompute.readNumbers("input.txt");
        String[] results = storageCompute.computeFactorial(numbers);
        storageCompute.writeResult("output.txt", results, ""); // No delimiter specified
        
        // Validation: Check what was written to output
        String[] writtenResults = testDataStore.getWrittenResults("output.txt");
        
        // Verify results are consistent with expected compute engine behavior
        assertNotNull(writtenResults, "Results should not be null");
        assertEquals(3, writtenResults.length, "Should have 3 results for input [1,10,25]");
        
        // These assertions will fail until compute engine is implemented
        // but they document the expected behavior
        assertEquals("1", writtenResults[0], "1! should equal 1");
        assertEquals("3628800", writtenResults[1], "10! should equal 3628800"); 
        assertEquals("15511210043330985984000000", writtenResults[2], "25! should equal 15511210043330985984000000");
    }
}