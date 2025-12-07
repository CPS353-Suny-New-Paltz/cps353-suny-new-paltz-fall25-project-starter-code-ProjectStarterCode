package project.datacompute;

import java.util.ArrayList;
import java.util.List;

import project.integration.InMemoryInpCon;
import project.integration.InMemoryOutCon;

public class InMemoryDataComputeAPI implements DataComputeAPI {

    private final InMemoryInpCon input;
    private final InMemoryOutCon output;

    public InMemoryDataComputeAPI(InMemoryInpCon input, InMemoryOutCon output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public List<Integer> readInput(String inputPath) {
       
        return new ArrayList<>(input.getInts());
    }

    @Override
    public void writeOutput(List<Integer> results, String outputPath) {
    	if (results == null) {
    	    return;
    	}
        for (Integer n : results) {
            output.write(n);
        }
    }
}
