package testhelpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestOutputConfig {
    private final List<String> outputs = new ArrayList<>();

    public void write(String s) {
        outputs.add(s);
    }

    public List<String> getOutputs() {
        return Collections.unmodifiableList(outputs);
    }

    public void clear() {
        outputs.clear();
    }
}
