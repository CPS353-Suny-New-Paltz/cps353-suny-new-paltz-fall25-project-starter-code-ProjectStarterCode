package api.implementation;

import api.StorageComputeAPI;
import api.ComputeEngineAPI;

public class StorageComputeImp implements StorageComputeAPI {
    
    private ComputeEngineAPI computeEngine;
    
    public StorageComputeImp(ComputeEngineAPI computeEngine) {
        this.computeEngine = computeEngine;
    }
    
    @Override
    public int[] readNumbers(String source) {
        // Simple implementation - just return test data
        return new int[]{1, 10, 25};
    }
    
    @Override
    public String[] computeFactorial(int[] numbers) {
        if (computeEngine == null) {
            throw new IllegalStateException("ComputeEngine not available");
        }
        return computeEngine.computeFactorial(numbers);
    }
    
    @Override
    public void writeResult(String destination, String[] results, String delimiter) {
        // Simple implementation - just print to console
        System.out.println("Writing results to: " + destination);
        for (String result : results) {
            System.out.println(result);
        }
    }
}