package integration;

import java.util.ArrayList;
import java.util.List;

public class TestOutputCollector {
    private final List<String> output = new ArrayList<>();

    public void write(String value) {
        output.add(value);
    }

    public List<String> getOutput() {
        return output;
    }
}
