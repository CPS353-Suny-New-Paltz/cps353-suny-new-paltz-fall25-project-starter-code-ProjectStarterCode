package networkapi;

import project.annotations.NetworkAPI;


@NetworkAPI
public interface UserAPI {
	// removed void methods
    
    String setInput(String sourceUri);

    String setOutput(String sinkUri);

    
    String[] setDelimiters(String pairDelimiter, String kvDelimiter);

  
    String[] useDefaultDelimiters();
}
