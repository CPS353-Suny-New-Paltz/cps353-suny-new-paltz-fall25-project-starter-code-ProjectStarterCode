package api;


	import java.util.List;

import project.annotations.ProcessAPIPrototype;

import java.lang.annotation.Annotation;
import java.util.ArrayList;

	public class ProcessAPIProto implements StorageComputeAPI, ProcessAPIPrototype {
	    
	    @ProcessAPIPrototype
	    public static StorageComputeAPI create() {
	        return new ProcessAPIProto();
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

		@Override
		public Class<? extends Annotation> annotationType() {
			// TODO Auto-generated method stub
			return null;
		}
	}

