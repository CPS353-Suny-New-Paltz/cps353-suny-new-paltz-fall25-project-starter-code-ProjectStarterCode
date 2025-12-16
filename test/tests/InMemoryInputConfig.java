package tests;

import java.util.List;

import api.InputConfig;

public class InMemoryInputConfig implements InputConfig{
	private final List<Integer> list; // test integers
	private final String location; 
	
    public InMemoryInputConfig(String location, List<Integer> list) {
    	// Location and value setters
        this.location = location; 
        this.list = list;
    }

    @Override 
    public String getLocation() { 
    	// Getter for the location
    	return location; 
    }

    public List<Integer> getList() { 
    	// test only
    	return list; 
    } 
	
}
