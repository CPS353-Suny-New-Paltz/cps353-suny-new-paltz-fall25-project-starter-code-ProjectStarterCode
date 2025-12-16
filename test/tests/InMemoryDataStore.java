package tests;

import java.util.Collections;
import java.util.List;

import api.DataCheck;
import api.DataProcessAPI;
import api.ReadRequest;
import api.ReadResponse;
import api.WriteOutput;

public class InMemoryDataStore implements DataProcessAPI {
    
	// Input configuration
	private final String inputLocation;
	  // Integers to return for ReadIntegers
	  private final List<Integer> inputs;
	  // Output location
	  private final String outputLocation;
	  // Destination for writeLines to write to
	  private final List<String> destination;

	  // Constructor
	  public InMemoryDataStore(InMemoryInputConfig inCfg, InMemoryOutputConfig outCfg) {
	    this.inputLocation = inCfg.getLocation();
	    this.inputs = inCfg.getList();
	    this.outputLocation = outCfg.getLocation();
	    this.destination = outCfg.getDestination();
	  }

   
	    @Override
	    public WriteOutput sendData(DataCheck data) {
	        // In test implementation, we write to the in-memory list
	        if (outputLocation != null && data.getLocation() != null && 
	            outputLocation.equals(data.getLocation()) && destination != null) {
	            List<String> output = data.getOutput();
	            if (output != null && !output.isEmpty()) {
	                destination.addAll(output);
	                return new WriteOutput(true, output.size());
	            }
	        }
	        return new WriteOutput(false, 0);
	    }
	    
	    @Override
	    public ReadResponse input(ReadRequest request) {
	        // In test implementation, we read from the in-memory list
	        if (inputLocation != null && request.getLocation() != null && 
	            inputLocation.equals(request.getLocation())) {
	            List<Integer> result = inputs;
	            if (request.getFilter() != null) {
	                // Apply simple filter if provided (test implementation)
	                result = inputs.stream()
	                    .collect(java.util.stream.Collectors.toList());
	            }
	            return new ReadResponse(result, result.size());
	        }
	        return new ReadResponse(Collections.emptyList(), 0);
	    }
	    
	  
    
}