package testhelpers;

import java.util.Collections;
import java.util.List;

public class TestInputConfig {
    private final List<Integer> inputs;

    public TestInputConfig(List<Integer> inputs) {
        // store an unmodifiable copy to avoid accidental test mutation
        this.inputs = inputs == null ? Collections.emptyList() : Collections.unmodifiableList(inputs);
    }


     // Tests data stores that can read this list to simulate input sources. 

    public List<Integer> getInputs() {
        return inputs;
    }
}
