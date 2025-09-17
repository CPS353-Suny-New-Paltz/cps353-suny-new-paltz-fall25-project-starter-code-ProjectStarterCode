package api;

import java.util.List;

import project.annotations.ConceptualAPI;

@ConceptualAPI
public interface ComputeEngineAPI {

	void initialize(List<Integer> inputData);

	List<Integer> compute();

	void writeResult(String destination, String delimiters);

}
