package apiengine;
import apinetwork.ComputationInput;
import apinetwork.ComputationOutput;

@project.annotations.ConceptualAPI
public interface ConceptualAPI {
	ComputationOutput compute(ComputationInput input);
}
