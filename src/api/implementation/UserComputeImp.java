package api.implementation;

import api.UserComputeAPI;
import api.StorageComputeAPI;

public class UserComputeImp implements UserComputeAPI {
    
    private StorageComputeAPI storageCompute;
    
    public UserComputeImp(StorageComputeAPI storageCompute) {
        this.storageCompute = storageCompute;
    }
    
    @Override
    public String computeFactorial(String inputSource, String outputDestination, String delimiter) {
        if (storageCompute == null) {
            return "ERROR: StorageCompute service not available";
        }
        
        try {
            int[] numbers = storageCompute.readNumbers(inputSource);
            String[] factorialResults = storageCompute.computeFactorial(numbers); // Now String[]
            storageCompute.writeResult(outputDestination, factorialResults, delimiter); // Now String[]
            
            return "SUCCESS: Factorial computation completed";
        } catch (Exception e) {
            return "ERROR: " + e.getMessage();
        }
    }
}