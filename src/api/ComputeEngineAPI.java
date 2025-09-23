package api;

import project.annotations.ConceptualAPI;

@ConceptualAPI
public interface ComputeEngineAPI {

    int[] computeFactorial(int[] numbers);
}