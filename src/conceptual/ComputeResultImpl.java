package conceptual;

public class ComputeResultImpl implements ComputeResult {
	private final int output;
	
	public ComputeResultImpl(int output) {
		this.output = output;
	}
	
	@Override
	public int getOutput() {
		return output;
	}

}
