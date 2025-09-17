package api;


	import project.annotations.ProcessAPIPrototype;
	import java.util.List;
	import java.util.ArrayList;

	public class StorageComputeAPIPrototype implements StorageComputeAPI {
	    
	    @ProcessAPIPrototype
	    public static StorageComputeAPI create() {
	        return new StorageComputeAPIPrototype();
	    }
	    
	    @Override
	    public List<Integer> read(String source) {
	        // Simple placeholder - returns empty list
	        return new ArrayList<>();
	    }
	    
	    @Override
	    public void write(String destination, List<Integer> data, String delimiters) {
	        // Simple placeholder - does nothing
	    }
	}

