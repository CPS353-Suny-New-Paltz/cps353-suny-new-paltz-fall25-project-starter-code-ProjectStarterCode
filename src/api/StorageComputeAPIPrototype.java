package api;

public class StorageComputeAPIPrototype {
    
    @ProcessAPIPrototype
    public int[] prototypeReadNumbers(String source) {
        return new int[]{4};
    }
    
    @ProcessAPIPrototype
    public void prototypeWriteResult(String destination, int[] results, String delimiter) {
        System.out.println("Writing results to: " + destination);
    }
}