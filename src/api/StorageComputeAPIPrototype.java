package api;

import project.annotations.ProcessAPIPrototype;

public class StorageComputeAPIPrototype {
    
    @ProcessAPIPrototype
    public void prototypeDataOperations(StorageComputeAPI api) {
        String source = "numbers.txt";
        String destination = "factorials.txt";
        String delimiter = "|";
        
        int[] inputNumbers = api.readNumbers(source);
<<<<<<< HEAD
        String[] factorialResults = api.computeFactorial(inputNumbers); // Now returns String[]
        api.writeResult(destination, factorialResults, delimiter); // Now accepts String[]
=======
        

        // Compute factorials for all numbers at once
        int[] factorialResults = api.computeFactorial(inputNumbers);

        
        // Write the factorial results
        api.writeResult(destination, factorialResults, delimiter);
>>>>>>> 2ff78b6c0eb83c3cf2c08a4982d94e123e2f0c36
    }

}