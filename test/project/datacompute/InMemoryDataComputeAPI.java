package project.datacompute;

import java.util.List;

import project.integration.InMemoryInpCon;
import project.integration.InMemoryOutCon;

public class InMemoryDataComputeAPI implements DataComputeAPI{
	
	private final InMemoryInpCon input;
    private final InMemoryOutCon output;

    public InMemoryDataComputeAPI(InMemoryInpCon input, InMemoryOutCon output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public void insertRequest(DataRequest dataRequest) {
        for (Integer n : input.getInts()) {
            output.write(String.valueOf(n));
        }
    }

	@Override
	public List<Integer> readInput(String inputPath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void writeOutput(List<String> out, String outputPath) {
		// TODO Auto-generated method stub
		
	}
}
