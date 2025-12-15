package project.checkpointtests;

import numberlettercountfetching.FetchApiImpl;
import numberlettercountfetching.FetchRequest;
import numberlettercountcomputing.ComputingApi;
import numberlettercountcomputing.ComputingApiImpl;
import numberlettercountdatastoring.DataStoreApi;
import numberlettercountdatastoring.DataStoreApiImpl;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ManualTestingFramework {

	public static final String INPUT = "manualTestInput.txt";
	public static final String OUTPUT = "manualTestOutput.txt";

	public static void main(String[] args) throws Exception {
		System.out.println("=== Starting Manual Testing Framework ===");

		// 1. Create FetchApiImpl
		FetchApiImpl fetchApi = new FetchApiImpl();

		// 2. Create the ComputingApi and DataStoreApi instances
		ComputingApi computingApi = new ComputingApiImpl();
		DataStoreApi dataStoreApi = new DataStoreApiImpl();

		// 3. Set the dependencies on FetchApiImpl (no other logic allowed here)
		fetchApi.setComputingApi(computingApi);
		fetchApi.setDataStoreApi(dataStoreApi);
		// (Leave computing <-> fetch wiring to implementations; keep test minimal)

		// 4. Call FetchApi.processFile() as the single end-to-end entry point
		boolean success = fetchApi.processFile(INPUT, OUTPUT);
		System.out.println("✓ Created FetchApiImpl with all dependencies set");
		System.out.println("✓ APIs are now ready to coordinate");
		System.out.println("✓ File processing result: " + (success ? "SUCCESS" : "FAILED"));

		System.out.println("\n=== Manual Testing Framework Finished ===");
	}
}