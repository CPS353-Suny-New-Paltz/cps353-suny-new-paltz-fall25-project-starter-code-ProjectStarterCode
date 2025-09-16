package API;

import project.annotations.ConceptualAPIPrototype;
import java.util.List;
import java.util.ArrayList;

public class ComputeEngineAPIPrototype implements ComputeEngineAPI {
    
    private List<Integer> currentData;
    
    @ConceptualAPIPrototype
    public static ComputeEngineAPI create() {
        return new ComputeEngineAPIPrototype();
    }
    
    @Override
    public void initialize(List<Integer> inputData) {
        this.currentData = new ArrayList<>(inputData);
    }
    
    @Override
    public List<Integer> compute() {
        // Simple computation placeholder - returns the same data
        return new ArrayList<>(currentData);
    }
    
    @Override
    public void writeResult(String destination, String delimiters) {
        // Placeholder for writing results
    }
}