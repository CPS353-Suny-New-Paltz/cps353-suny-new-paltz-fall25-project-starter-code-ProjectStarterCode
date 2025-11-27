package process;

import java.util.List;

import project.annotations.ProcessAPI;

@ProcessAPI
public interface StorageComputeAPI {
	DataValue readInput(String filePath);
	boolean writeOutput(String filePath, DataValue value);
	
	MultiDataValue readAllInputs(String filePath);
	boolean writeAllOutputs(String filePath, List<Integer> values);
}
