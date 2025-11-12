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
        // Empty implementation for now
        return new int[0];
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
        // Empty implementation for now
    }
}
