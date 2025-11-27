package conceptual;

public class ComputeRequestImpl implements ComputeRequest {
    private final int input;

    public ComputeRequestImpl(int input) {
        this.input = input;
    }

    @Override
    public int getInput() {
        return input;
    }
}