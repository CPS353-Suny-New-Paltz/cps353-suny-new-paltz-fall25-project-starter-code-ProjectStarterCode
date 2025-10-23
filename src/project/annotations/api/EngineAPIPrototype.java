package project.annotations.api;

import project.annotations.ConceptualAPIPrototype;

public class EngineAPIPrototype implements EngineAPI {

    @Override
    @ConceptualAPIPrototype
    public int[] runComputation(int input) {
        System.out.println("[Prototype] Running computation on input: " + input);
        return new int[] { input, input + 1, input + 2 };
    }
}