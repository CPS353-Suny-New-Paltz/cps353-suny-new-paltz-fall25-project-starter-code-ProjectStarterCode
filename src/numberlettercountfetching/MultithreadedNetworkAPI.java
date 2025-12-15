package numberlettercountfetching;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.math.BigInteger;
import java.util.List;

public  class MultithreadedNetworkAPI implements FetchApi {
	private static final int MAX_THREADS = 4;
	private final ExecutorService executor;
	private final FetchApi delegate;

	public MultithreadedNetworkAPI() {
		this.executor = Executors.newFixedThreadPool(MAX_THREADS);
		this.delegate = createDelegate();
	}

	private FetchApi createDelegate() {
		// Create the single-threaded implementation
		numberlettercountcomputing.ComputingApi computingApi = new numberlettercountcomputing.ComputingApiImpl();
		numberlettercountdatastoring.DataStoreApi dataStoreApi = new numberlettercountdatastoring.DataStoreApiImpl();
		FetchApiImpl fetchApi = new FetchApiImpl();
		fetchApi.setDataStoreApi(dataStoreApi);
		fetchApi.setComputingApi(computingApi);
		return fetchApi;
	}

	
	public List<BigInteger> insertRequest(FetchRequest fetchRequest) {
		try {
			Future<List<BigInteger>> future = executor.submit(() -> 
			delegate.insertRequest(fetchRequest)
					);
			return future.get();
		} catch (Exception e) {
			System.err.println("Multithreaded processing error: " + e.getMessage());
			return List.of(BigInteger.valueOf(-1));
		}
	}


	public boolean validateNumber(BigInteger number) {
		try {
			Future<Boolean> future = executor.submit(() -> 
			delegate.validateNumber(number)
					);
			return future.get();
		} catch (Exception e) {
			System.err.println("Multithreaded validation error: " + e.getMessage());
			return false;
		}
	}

	public void shutdown() {
		executor.shutdown();
		try {
			if (!executor.awaitTermination(2, TimeUnit.SECONDS)) {
				executor.shutdownNow();
				executor.awaitTermination(1, TimeUnit.SECONDS);
			}
		} catch (InterruptedException e) {
			executor.shutdownNow();
			Thread.currentThread().interrupt();
		}
	}

	
	public boolean processFile(String inputPath, String outputPath) {
		try {
			Future<Boolean> future = executor.submit(() -> delegate.processFile(inputPath, outputPath));
			return future.get();
		} catch (Exception e) {
			System.err.println("Multithreaded processFile error: " + e.getMessage());
			return false;
		}
	}
}