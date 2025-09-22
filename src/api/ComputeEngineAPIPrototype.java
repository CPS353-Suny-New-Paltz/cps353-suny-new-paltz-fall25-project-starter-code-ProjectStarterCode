package api;

import project.annotations.ConceptualAPIPrototype;

public class ComputeEngineAPIPrototype {
    
    @ConceptualAPIPrototype    
    public void prototypeFactorialComputations(ComputeEngineAPI api) {
        // Simulate receiving numbers from data storage
        int[] numbersToCompute = {1, 10, 25}; 
        
        // Use the API to compute factorials for all numbers at once
        int[] factorialResults = api.computeFactorial(numbersToCompute);
        
    }
}