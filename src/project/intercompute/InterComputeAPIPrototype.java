package project.intercompute;

import project.annotations.ConceptualAPIPrototype;

public class InterComputeAPIPrototype {
    @ConceptualAPIPrototype
    public void prototype(InterComputeAPI inter) {

        if (inter == null) {
            throw new IllegalArgumentException("InterComputeAPI instance cannot be null.");
        }

        
        InterComputeAPI compute = inter;

        compute.processRequest(new InterRequest(42));
        compute.processRequest(new InterRequest(97));
        compute.processRequest(new InterRequest(10));
    }
}
