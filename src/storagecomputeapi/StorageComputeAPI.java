package storagecomputeapi;
import project.annotations.ProcessAPI;
import java.util.List;

@ProcessAPI
public interface StorageComputeAPI {
	
//	reads integers from storage
	List<Integer> readInput();
	
	
//	writes output format to storage
	void writeOutput(List<Integer> data);
}
