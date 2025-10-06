package project.datacompute;
import java.util.List;

import project.annotations.ProcessAPI; 
@ProcessAPI 
public interface DataComputeAPI { 
	void insertRequest(DataRequest dataRequest);
	
	List<Integer> readInput(String inputPath);
	void writeOutput(List<String> out, String outputPath);
}