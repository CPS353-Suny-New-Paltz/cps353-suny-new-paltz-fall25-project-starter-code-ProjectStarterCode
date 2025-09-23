package process;

import project.annotations.ProcessAPI;

@ProcessAPI
public interface StorageComputeAPI {
	DataValue readInput(String source);
	void writeOutput(String destination, DataValue data);
}
