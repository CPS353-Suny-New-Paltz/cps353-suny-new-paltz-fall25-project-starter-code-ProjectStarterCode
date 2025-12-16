package api;

import project.annotations.ConceptualAPI;

@ConceptualAPI 
public interface ComputerAPI {

	CompRequest request(CompRequest calcReadRequest);

	CompResponse response(CompRequest request);

	CalcWriteResponse write(CompResponse compResponse, CompRequest request);

}
