package project.intercompute;

import project.annotations.ConceptualAPIPrototype;

public class InterComputeAPIPrototype {
    @ConceptualAPIPrototype
    public void prototype(InterComputeAPI inter) {
        InterComputeAPI compute = inter;
        compute.processRequest(new InterRequest("Hello from InterCompute".getBytes()));
        compute.processRequest(new InterRequest(new byte[]{42, 43, 44}));
    }
}