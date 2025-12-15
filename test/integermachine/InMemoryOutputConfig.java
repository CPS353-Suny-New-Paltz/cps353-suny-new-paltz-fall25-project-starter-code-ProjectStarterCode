package integermachine;

import java.util.ArrayList;
import java.util.List;


public class InMemoryOutputConfig extends OutputSinkRef {

    private final List<String> outputs = new ArrayList<>();

    public InMemoryOutputConfig() {
        super("in-memory-output");
    }

    public List<String> getOutputs() {
        return outputs;
    }
}
