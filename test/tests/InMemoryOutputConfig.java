package tests;

import java.util.List;

import api.OutputConfig;

public class InMemoryOutputConfig implements OutputConfig {
	private final String location;      // defines the location of the values
    private final List<String> output;    // writable list

    public InMemoryOutputConfig(String location, List<String> output) {
        this.location = location; 
        this.output = output;
    }

    @Override 
    public String getLocation() { 
    	// Getter for the location
    	return location; 
    	}

    public List<String> getDestination() { 
    	return output; 
    	} // test-only

}
