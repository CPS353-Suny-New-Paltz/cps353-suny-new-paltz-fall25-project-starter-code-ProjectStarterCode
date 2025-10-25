package project.conceptualapi;
import project.annotations.ConceptualAPI;

@ConceptualAPI
public interface ComputeControllerAPI {
    ComputeResponse compute(ComputeRequest request);
}