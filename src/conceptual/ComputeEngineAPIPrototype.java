package conceptual;

import project.annotations.ConceptualAPIPrototype;

public class ComputeEngineAPIPrototype {
	
	@ConceptualAPIPrototype
	public void prototype(ComputeEngineAPI engine) {
		ComputeRequest request = new ComputeRequest() {
			@Override
			public int getInput() {
				return 100;
			}
		
		};
		
		ComputeResult result = engine.performComputation(request);
		
		System.out.println("Largest prime smaller than input: " + result.getOutput());
		
	}
}
