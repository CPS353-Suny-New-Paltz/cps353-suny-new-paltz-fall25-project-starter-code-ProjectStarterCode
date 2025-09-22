package api.implementation;

import api.UserComputeAPI;
import api.StorageComputeAPI; // Dependency from system design

public class UserComputeImp implements UserComputeAPI {
    
    private StorageComputeAPI storageCompute;
    
    public UserComputeImp(StorageComputeAPI storageCompute) {
        this.storageCompute = storageCompute;
    }
    
    @Override
    public String computeFactorial(String inputSource, String outputDestination, String delimiter) {
        // return failure message
        if (storageCompute == null) {
            return "ERROR: StorageCompute service not available";
        }
        
        try {
            // Simulate the workflow 
            int[] numbers = storageCompute.readNumbers(inputSource);
            
            // Corrected: Compute factorials for all numbers at once
            int[] factorialResults = storageCompute.computeFactorial(numbers);
            
            storageCompute.writeResult(outputDestination, factorialResults, delimiter);
            
            return "SUCCESS: Factorial computation completed";
        } catch (Exception e) {
            return "ERROR: " + e.getMessage();
        }
    }
}