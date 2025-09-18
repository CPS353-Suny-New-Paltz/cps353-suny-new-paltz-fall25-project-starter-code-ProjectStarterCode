package api;

public class ComputeEngineAPIPrototype {
    
    @ConceptualAPIPrototype
    public int prototypeComputeFactorial(int number) {
        int result = 1;
        for (int i = 2; i <= number; i++) {
            result *= i;
        }
        return result;
    }
}