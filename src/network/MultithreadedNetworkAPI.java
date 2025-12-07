package network;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import conceptual.ComputeEngineImpl;
import conceptual.ComputeRequest;
import conceptual.ComputeResult;
import process.MultiDataValue;
import process.StorageComputeImpl;

public class MultithreadedNetworkAPI implements UserComputeAPI {

	private static final int MAX_THREADS = 4;
	
	private final UserComputeImpl delegate;
	private final ExecutorService executor;
	
	public MultithreadedNetworkAPI() {
		this.delegate = new UserComputeImpl(new ComputeEngineImpl(), new StorageComputeImpl());
		this.executor = Executors.newFixedThreadPool(MAX_THREADS);
	}
	
	public MultithreadedNetworkAPI(UserComputeImpl userCompute) {
	    if (userCompute == null) {
	        throw new IllegalArgumentException("UserComputeImpl must not be null");
	    }
	    this.delegate = userCompute;
	    this.executor = Executors.newFixedThreadPool(MAX_THREADS);
	}

	@Override
	public JobResponse submitJob(JobRequest request) {
		if (request == null) {
			return new JobResponseImpl(false, "Invalid job request: null");
		}
		
		try {
	        MultiDataValue inputValues = delegate.getStorage().readAllInputs(request.getInputSource());
	        List<Integer> values = inputValues.getValues();

	        if (values.isEmpty()) {
	            return new JobResponseImpl(false, "No input values to process");
	        }
	        
	        List<Callable<Integer>> tasks = new ArrayList<>();
	        for (int v : values) {
	            tasks.add(() -> {
	                ComputeRequest compReq = () -> v;
	                ComputeResult compRes = delegate.getEngine().performComputation(compReq);
	                return (compRes != null) ? compRes.getOutput() : -1;
	            });
	        }
	        
	        List<Future<Integer>> futures = executor.invokeAll(tasks);
	        List<Integer> results = new ArrayList<>();
	        for (Future<Integer> f : futures) {
	            try {
	                results.add(f.get());
	            } catch (ExecutionException e) {
	                results.add(-1);
	            }
	        }
	        
	        boolean success = delegate.getStorage().writeAllOutputs(request.getOutputDestination(), results);
	        return new JobResponseImpl(success, success ? "Job completed (multithreaded)" : "Output write failed");

	    } catch (Exception e) {
	        return new JobResponseImpl(false, "Multithreaded job failed: " + e.getMessage());
	    }
	}
	
	// code for review
	public List<String> processRequests(List<String> requests) {
		if (requests == null) {
			return new ArrayList<>();		// return empty list if null
		}
		
		// prepare a list of callables to process requests concurrently
		List<Callable<String>> callables = new ArrayList<>();
		for (String req : requests) {
			callables.add(() -> {
				return "processed:" + req;	// simulate request processing
			});
		}
		
		try {
			// submit tasks to executor
			List<Future<String>> futures = executor.invokeAll(callables);
			List<String> results = new ArrayList<>(futures.size());		// collect results
			for(Future<String> f : futures) {
				try {
					results.add(f.get());		// normal case
				} catch (ExecutionException ee) {
					results.add("error:" + ee.getCause().getMessage());
				}
			}
			return results;
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
			List<String> empty = new ArrayList<>();
			return empty;
		}
	}
	
	public void shutdown() {
		executor.shutdown();
		try {
			if(!executor.awaitTermination(2, TimeUnit.SECONDS)) {
				executor.shutdownNow();
			}
		} catch (InterruptedException e) {
			executor.shutdownNow();
			Thread.currentThread().interrupt();
		}
	}
}
