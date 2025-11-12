package integration;

import api.StorageComputeAPI;
import api.ComputeEngineAPI;
import api.implementation.ComputeEngineImp;
import java.util.HashMap;
import java.util.Map;

/**
 * Test-only data store implementation for integration tests.
 */
public class StorageComputeIntegration implements StorageComputeAPI {
    private ComputeEngineAPI computeEngine;
    private Map<String, int[]> fileContents;
    private Map<String, String[]> writtenResults;
    
    public StorageComputeIntegration() {
        this.computeEngine = new ComputeEngineImp();
        this.fileContents = new HashMap<>();
        this.writtenResults = new HashMap<>();
    }
    
    public StorageComputeIntegration(ComputeEngineAPI computeEngine) {
        this.computeEngine = computeEngine;
        this.fileContents = new HashMap<>();
        this.writtenResults = new HashMap<>();
    }
    
    @Override
    public int[] readNumbers(String source) {
        return fileContents.getOrDefault(source, new int[0]);
    }
    
    @Override
    public String[] computeFactorial(int[] numbers) {
        return computeEngine.computeFactorial(numbers);
    }
    
    @Override
    public void writeResult(String destination, String[] results, String delimiter) {
        writtenResults.put(destination, results);
    }
    
    // Test helper methods
    public String[] getWrittenResults(String destination) {
        return writtenResults.get(destination);
    }
    
    public void addTestFile(String filename, int[] numbers) {
        fileContents.put(filename, numbers);
    }
    
    public void clearTestData() {
        fileContents.clear();
        writtenResults.clear();
    }
}