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
        
       
        int[] factorialResults = new int[inputNumbers.length];
        for (int i = 0; i < inputNumbers.length; i++) {
            factorialResults[i] = computeFactorial(inputNumbers[i]);
        }
        
        // Write the factorial results
        api.writeResult(destination, factorialResults, delimiter);
    }
    
    // Helper method to compute factorial 
    private int computeFactorial(int number) {
        if (number <= 1) {
        	return 1;
        }
        int result = 1;
        for (int i = 2; i <= number; i++) {
            result *= i;
        }
        return result;
    }
}