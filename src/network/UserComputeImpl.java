package network;

import java.util.ArrayList;
import java.util.List;

import conceptual.ComputeEngineAPI;
import conceptual.ComputeEngineImpl;
import conceptual.ComputeRequest;
import conceptual.ComputeResult;
import process.MultiDataValue;
import process.StorageComputeAPI;
import process.StorageComputeImpl;


public class UserComputeImpl implements UserComputeAPI {
    private final ComputeEngineAPI engine;
    private final StorageComputeAPI storage;

    public UserComputeImpl(ComputeEngineAPI engine, StorageComputeAPI storage) {
        if (engine == null || storage == null) {
            throw new IllegalArgumentException("Engine and storage must not be null");
        }
        this.engine = engine;
        this.storage = storage;
    }

    public UserComputeImpl(MultithreadedNetworkAPI networkAPI) {
        if (networkAPI == null) {
            throw new IllegalArgumentException("Network API must not be null");
        }
        this.engine = new ComputeEngineImpl();
        this.storage = new StorageComputeImpl();
    }
    
    public ComputeEngineAPI getEngine() {
        return engine;
    }

    public StorageComputeAPI getStorage() {
        return storage;
    }


	@Override
	public JobResponse submitJob(JobRequest request) {
	    if (request == null) {
	        return new JobResponseImpl(false, "Invalid job request: null");
	    }

	    if (request.getInputSource() == null || request.getOutputDestination() == null) {
	        return new JobResponseImpl(false, "Invalid file paths in request");
	    }

	    try {
	        MultiDataValue inputValues = storage.readAllInputs(request.getInputSource());
	        List<Integer> results = new ArrayList<>();

	        for (int value : inputValues.getValues()) {
	            ComputeRequest compReq = () -> value;
	            ComputeResult compRes = engine.performComputation(compReq);
	            // If compRes is null, treat as -1
	            results.add(compRes != null ? compRes.getOutput() : -1);
	        }

	        storage.writeAllOutputs(request.getOutputDestination(), results);

	        return new JobResponseImpl(true, "Job completed successfully");

	    } catch (RuntimeException e) {
	        return new JobResponseImpl(false, "Unexpected runtime error: " + e.getMessage());
	    } catch (Exception e) {
	        return new JobResponseImpl(false, "Job failed: " + e.getMessage());
	    }
	}
}
