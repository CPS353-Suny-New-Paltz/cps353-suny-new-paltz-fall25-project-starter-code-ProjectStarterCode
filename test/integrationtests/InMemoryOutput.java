package integrationtests;
import java.util.ArrayList;
import java.util.List;

//Collects written strings in memory. 
public class InMemoryOutput {
    private final List<String> outputResults = new ArrayList<>();

    public void write(String data) {
        outputResults.add(data);
    }

    public List<String> getOutputResults() {
        return outputResults;
    }
}
