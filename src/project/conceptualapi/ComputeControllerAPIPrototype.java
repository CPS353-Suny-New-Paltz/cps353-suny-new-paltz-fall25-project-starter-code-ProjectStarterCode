package project.conceptualapi;
import project.annotations.ConceptualAPIPrototype;

public class ComputeControllerAPIPrototype {

    @ConceptualAPIPrototype
    public void prototype(ComputeControllerAPI n) {

        // Make a request for base=2 and exponent=15
        ComputeRequest req = new ComputeRequest(2, 15);

        // ask the core to compute
        ComputeResponse resp = n.compute(req);

        // retrieve result if Result exists
        if (resp != null) {
            String result = resp.getResult();
            System.out.println("Power digit sum result: " + result);
        } else {
            System.out.println("Computation failed!");
        }
    }
}
