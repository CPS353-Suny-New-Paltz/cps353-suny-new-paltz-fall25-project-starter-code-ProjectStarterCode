package api;

import project.annotations.ConceptualAPIPrototype;

public class ComputeEnginePrototypeAPI {
	@ConceptualAPIPrototype 
	public void computeEnginePrototype(ComputerAPI components) {
		
		// read component requests Collatz sequence of input to be calculated
		CompRequest compRequest = components.request(new CompRequest());
		
		//Computing component will return back the output to the reader/writer component
		CompResponse compResponse = components.response(compRequest);
		
		// write component response Collatz sequence calculated
		CalcWriteResponse calcWriteResponse = components.write(compResponse, compRequest);
	}
}
