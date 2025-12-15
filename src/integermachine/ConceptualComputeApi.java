package integermachine;

import project.annotations.ConceptualAPI;

@ConceptualAPI
public interface ConceptualComputeApi {
    /**
     * Compute the largest prime strictly smaller than n.
     */
    ComputeResult compute(int n);
}
