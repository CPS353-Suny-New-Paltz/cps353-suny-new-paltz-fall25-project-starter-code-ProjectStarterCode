package api.implementation;

import api.ComputeEngineAPI;
import java.math.BigInteger;

public class ComputeEngineImp implements ComputeEngineAPI {
    
    @Override
    public String[] computeFactorial(int[] numbers) {
        String[] results = new String[numbers.length];
        
        for (int i = 0; i < numbers.length; i++) {
            results[i] = computeSingleFactorial(numbers[i]).toString();
        }
        
        return results;
    }
    
    private BigInteger computeSingleFactorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial not defined for negative numbers: " + n);
        }
        
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}