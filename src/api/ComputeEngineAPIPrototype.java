package api;

import project.annotations.ConceptualAPIPrototype;

public class ComputeEngineAPIPrototype {
    
    @ConceptualAPIPrototype    
    public void prototypeFactorialComputations(ComputeEngineAPI api) {
        int[] numbersToCompute = {1, 10, 25};
        api.computeFactorial(numbersToCompute);
    }
}