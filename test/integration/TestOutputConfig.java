package integration;

import api.OutputConfig;
import java.util.List;
import java.util.ArrayList;

public class TestOutputConfig implements OutputConfig {
    private final List<String> results;
    
    public TestOutputConfig() {
        this.results = new ArrayList<>();
    }
    
    @Override
    public void writeResults(String[] results, String delimiter) {
        this.results.clear();
        for (String result : results) {
            this.results.add(result);
        }
    }
    
    public List<String> getResults() {
        return new ArrayList<>(results);
    }
    
    public void clear() {
        results.clear();
    }
}