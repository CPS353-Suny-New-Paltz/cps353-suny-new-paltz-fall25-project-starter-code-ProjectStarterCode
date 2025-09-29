package API;

import project.annotations.NetworkAPI;

@NetworkAPI
public interface UserAPI {
	   void setInputSource(String sourceUri);
	    void setOutputDestination(String sinkUri);
	    void setDelimiters(String pairDelimiter, String kvDelimiter); // e.g., ";" and ":"
	    void useDefaultDelimiters();
}
