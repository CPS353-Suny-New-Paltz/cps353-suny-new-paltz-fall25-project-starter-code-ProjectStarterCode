package conceptual;

import project.annotations.ConceptualAPI;

@ConceptualAPI
public class ComputeEngineImpl implements ComputeEngineAPI {
	
	private final ComputeEngineAPI computeEngine;
	
	public ComputeEngineImpl(ComputeEngineAPI computeEngine) {
		this.computeEngine = computeEngine;
	}
	
	@Override
	public ComputeResult performComputation(ComputeRequest request) {
		return new ComputeResultImpl(-1); // unimplemented

	}
}
