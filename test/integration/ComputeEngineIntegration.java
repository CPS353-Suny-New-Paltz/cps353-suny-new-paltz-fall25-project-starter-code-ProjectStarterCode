package test.integration;

import api.implementation.StorageComputeImp;
import api.implementation.ComputeEngineImp;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ComputeEngineIntegration {

    @Test
    void integrationTestWithRealImplementations() {
        // Setup: Use real implementations (no mocks)
        ComputeEngineImp computeEngine = new ComputeEngineImp();
        StorageComputeImp storageCompute = new StorageComputeImp(computeEngine);
        StorageComputeIntegration testDataStore = new StorageComputeIntegration();
        
        // Configure test data store with input [1,10,25]
        testDataStore.addTestFile("input.txt", new int[]{1, 10, 25});
        
        // FIX: Get numbers directly from test data store, NOT from StorageComputeImp
        int[] numbers = testDataStore.readNumbers("input.txt");
        
        // FIX: Compute factorials using the real StorageComputeImp
        String[] results = storageCompute.computeFactorial(numbers);
        
        // FIX: Write results using test data store
        testDataStore.writeResult("output.txt", results, ""); // No delimiter specified
        
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