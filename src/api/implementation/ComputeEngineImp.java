package api.implementation;

import api.ComputeEngineAPI;
import project.annotations.ConceptualAPI;
import java.math.BigInteger;


@ConceptualAPI
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
        
        
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result.toString();
    }
}
