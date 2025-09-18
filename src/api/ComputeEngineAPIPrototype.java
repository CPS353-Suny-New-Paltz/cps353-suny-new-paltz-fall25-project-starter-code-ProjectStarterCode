package api;

import project.annotations.ConceptualAPIPrototype;

public class ComputeEngineAPIPrototype {
    
    @ConceptualAPIPrototype
    public int[] prototypeFactorialComputations(ComputeEngineAPI api) {
        // Simulate receiving numbers from data storage
        int[] numbersToCompute = {4, 5, 6}; 
        
        // Use the API to compute factorials for each number
        int[] factorialResults = new int[numbersToCompute.length];
        for (int i = 0; i < numbersToCompute.length; i++) {
            factorialResults[i] = api.computeFactorial(numbersToCompute[i]);
        }
        
        return factorialResults;
    }
}