package apiengine;
import apinetwork.ComputationInput;
import apinetwork.ComputationOutput;
import apinetwork.Delimiters;

public class ConceptualAPIPrototype {

    @project.annotations.ConceptualAPIPrototype
    public ComputationOutput prototype(ConceptualAPI api) {
    	Delimiters delimiters = new Delimiters(" ", " ");
		ComputationInput input = new ComputationInput(1, delimiters ); 
		return api.compute(input);
        
    }
}
