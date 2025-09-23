package conceptual;

import project.annotations.ConceptualAPI;

@ConceptualAPI

public interface ComputeEngineAPI {
	ComputeResult performComputation(ComputeRequest request);
}
