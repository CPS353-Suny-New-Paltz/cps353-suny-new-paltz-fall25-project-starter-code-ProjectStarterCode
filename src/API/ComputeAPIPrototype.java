package API;

public class ComputeAPIPrototype {
    public void prototype(ComputeAPI computeapi) {
        // Call the compute process
    	 String result = computeapi.compute(42);
         System.out.println("Prototype compute(42) -> " + result);
    }
}
