package conceptualapi;

import project.annotations.ConceptualAPI;

@ConceptualAPI
public interface ComputeAPI {
    // removed void methods
	String compute(int n);
	String calculatePrimes(int limit);
}
