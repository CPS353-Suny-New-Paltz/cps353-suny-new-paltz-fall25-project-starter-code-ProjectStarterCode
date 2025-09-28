package test.integration;

import api.StorageComputeAPI;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Arrays;

public class StorageComputeIntegration {
    
    @Test
    void integrationTest() {
        List<Integer> inputNumbers = Arrays.asList(1, 5, 10);
        TestInputConfig inputConfig = new TestInputConfig(inputNumbers);
        TestOutputConfig outputConfig = new TestOutputConfig();
        StorageComputeAPI storageCompute = new TestStorageCompute();
        
        String[] results = storageCompute.computeFactorial(inputConfig, outputConfig);
        
        assertNotNull(results);
        assertEquals(3, results.length);
        
        List<String> writtenResults = outputConfig.getResults();
        assertEquals(3, writtenResults.size());
        assertEquals("1", writtenResults.get(0));
        assertEquals("120", writtenResults.get(1)); // 5! = 120
        assertEquals("3628800", writtenResults.get(2)); // 10! = 3628800
    }
}