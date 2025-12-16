package integrationtests;
import java.util.List;

 //Holds a list of integers representing input data.
public class InMemoryInput {
    private final List<Integer> inputNumbers;

    public InMemoryInput(List<Integer> inputNumbers) {
        this.inputNumbers = inputNumbers;
    }

    public List<Integer> getInputNumbers() {
        return inputNumbers;
    }
}
