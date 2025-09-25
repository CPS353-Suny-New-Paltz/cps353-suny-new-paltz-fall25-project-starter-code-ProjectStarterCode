package api.implementation;

import api.ComputeEngineAPI;

public class ComputeEngineImp implements ComputeEngineAPI {
    
    @Override
    public String[] computeFactorial(int[] numbers) {
        String[] results = new String[numbers.length];
        
        for (int i = 0; i < numbers.length; i++) {
            results[i] = computeSingleFactorial(numbers[i]);
        }
        
        return results;
    }
    
    private String computeSingleFactorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial not defined for negative numbers: " + n);
        }
        if (n == 0 || n == 1) {
            return "1";
        }
        
        // Simple implementation for smoke test - just return placeholder
        return "factorial-of-" + n;
    }
}