package api;

import project.annotations.NetworkAPIPrototype;

public class UserComputeAPIPrototype {
    
    @NetworkAPIPrototype
    public String prototypeComputeFactorial(UserComputeAPI api) {
        // Simulate user providing input source, output destination, and delimiter
        String inputSource = "numbers.txt";
        String outputDestination = "factorials.txt";
        String delimiter = "|";
        
        // Use the API to initiate factorial computation
        return api.computeFactorial(inputSource, outputDestination, delimiter);
    }
}