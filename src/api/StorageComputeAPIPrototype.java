package api;

import project.annotations.ProcessAPIPrototype;

public class StorageComputeAPIPrototype {
    
    @ProcessAPIPrototype
    public void prototypeDataOperations(StorageComputeAPI api) {
        // Demonstrate the data flow for factorial computation
        String source = "numbers.txt";
        String destination = "factorials.txt";
        String delimiter = "|";
        
        // Read input numbers that need factorial computation
        int[] inputNumbers = api.readNumbers(source);
        

        // Compute factorials for all numbers at once
        int[] factorialResults = api.computeFactorial(inputNumbers);

        
        // Write the factorial results
        api.writeResult(destination, factorialResults, delimiter);
    }

}