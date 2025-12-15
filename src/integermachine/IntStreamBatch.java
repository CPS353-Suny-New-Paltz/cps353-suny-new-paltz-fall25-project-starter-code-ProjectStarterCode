package integermachine;

import java.util.List;

public class IntStreamBatch {
    private final List<Integer> values;

    public IntStreamBatch(List<Integer> values) {
        this.values = values;
    }

    public List<Integer> getValues() {
        return values;
    }
}