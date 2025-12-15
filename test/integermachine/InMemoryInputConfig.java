package integermachine;

import java.util.List;


public class InMemoryInputConfig extends InputSourceRef {

    private final List<Integer> values;

    public InMemoryInputConfig(List<Integer> values) {
        super("in-memory-input");
        this.values = values;
    }

    public List<Integer> getValues() {
        return values;
    }
}
