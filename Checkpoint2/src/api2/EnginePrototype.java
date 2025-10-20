package api2;

public class EnginePrototype {
    public void prototype(EngineAPI computeapi) {
    	 String result = computeapi.compute(42);
         System.out.println("Prototype compute(42): " + result);
    }
}