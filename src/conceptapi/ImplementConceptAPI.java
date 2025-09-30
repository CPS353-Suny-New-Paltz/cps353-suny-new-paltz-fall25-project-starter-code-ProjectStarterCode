package conceptapi;

public class ImplementConceptAPI implements ConceptAPI {

	@Override
	public ComputeComponent compute(UserComponent user) {
        return new ComputeValues(); // or any other real ComputeComponent implementation
    }
}
