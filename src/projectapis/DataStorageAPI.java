// process api
package projectapis;
import java.util.List;

import project.annotations.ProcessAPI;

@ProcessAPI
public interface DataStorageAPI {
	
	//load integers
	List<Integer> loadIntegers(String inputSource, String delimiter);
	
	//saving the result
	void storeResults(String outputSource, List<Long> results);

    //fetching the result 
    long fetchComputation();
    
    //check to see if it even exists (way to track)
    ComputationStatus getComputationStatus();
    
}
