package processapi;

import project.annotations.ProcessAPI;

@ProcessAPI
public interface StorageAPI {
	// removed void methods
   
    String readInput(String source);

   
    String writeOutput(String destination);
}
