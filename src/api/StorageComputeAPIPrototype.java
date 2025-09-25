package api;

import project.annotations.ProcessAPIPrototype;

public class StorageComputeAPIPrototype {
    
    @ProcessAPIPrototype
    public void prototypeDataOperations(StorageComputeAPI api) {
        String source = "numbers.txt";
        String destination = "factorials.txt";
        String delimiter = "|";
        
        int[] inputNumbers = api.readNumbers(source);
        String[] factorialResults = api.computeFactorial(inputNumbers); // Now returns String[]
        api.writeResult(destination, factorialResults, delimiter); // Now accepts String[]
    }
}