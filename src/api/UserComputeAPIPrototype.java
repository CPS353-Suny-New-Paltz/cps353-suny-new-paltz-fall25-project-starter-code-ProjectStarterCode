package api;

import project.annotations.NetworkAPIPrototype;

public class UserComputeAPIPrototype {
    
    @NetworkAPIPrototype
    public String prototypeComputeFactorial(UserComputeAPI api) {
        String inputSource = "numbers.txt";
        String outputDestination = "factorials.txt";
        String delimiter = "|";
        
        return api.computeFactorial(inputSource, outputDestination, delimiter);
    }
}