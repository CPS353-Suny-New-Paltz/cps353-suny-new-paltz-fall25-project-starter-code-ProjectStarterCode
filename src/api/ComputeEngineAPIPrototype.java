package api;

import project.annotations.ConceptualAPIPrototype;


public class ComputeEngineAPIPrototype {
    
    @ConceptualAPIPrototype    
    public void prototypeFactorialComputations(ComputeEngineAPI api) {
<<<<<<< HEAD
        int[] numbersToCompute = {1, 10, 25, 50, 100}; // Now supports much larger numbers
        
        String[] factorialResults = api.computeFactorial(numbersToCompute);
=======
        // Simulate receiving numbers from data storage
        int[] numbersToCompute = {1, 10, 25}; 
        
        // Use the API to compute factorials for all numbers at once
        int[] factorialResults = api.computeFactorial(numbersToCompute);

>>>>>>> 2ff78b6c0eb83c3cf2c08a4982d94e123e2f0c36
    }
}