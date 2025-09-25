package api;

import project.annotations.ConceptualAPIPrototype;

public class ComputeEngineAPIPrototype {
    
    @ConceptualAPIPrototype    
    public void prototypeFactorialComputations(ComputeEngineAPI api) {
        int[] numbersToCompute = {1, 10, 25, 50, 100}; // Now supports much larger numbers
        
        String[] factorialResults = api.computeFactorial(numbersToCompute);
    }
}