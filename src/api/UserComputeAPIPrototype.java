package api;

public class UserComputeAPIPrototype {
    
    @NetworkAPIPrototype
    public String prototypeComputeFactorial(String inputSource, String outputDestination, String delimiter) {
        return "Computation started for source: " + inputSource;
    }
}