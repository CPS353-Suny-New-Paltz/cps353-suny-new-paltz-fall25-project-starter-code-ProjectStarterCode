package project.datacompute;
import java.util.List;

import project.annotations.ProcessAPI; 

@ProcessAPI
public interface DataComputeAPI {
	
    List<Integer> readInput(String inputPath);
    
    void writeOutput(List<Integer> results, String outputPath);
}