package api;

import project.annotations.ConceptualAPI;

@ConceptualAPI
public interface ComputeEngineAPI {
    String[] computeFactorial(int[] numbers);
}