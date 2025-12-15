package integermachine;

import project.annotations.ConceptualAPIPrototype;

public class ConceptualComputeApiPrototype {

    @ConceptualAPIPrototype
    public void prototype(ConceptualComputeApi api) {
        // Prototype call — does not run in production
        api.compute(10);
    }
}
