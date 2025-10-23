package project.annotations.api;

import project.annotations.ConceptualAPI;

@ConceptualAPI
public interface EngineAPI {
    int[] runComputation(int input);
}