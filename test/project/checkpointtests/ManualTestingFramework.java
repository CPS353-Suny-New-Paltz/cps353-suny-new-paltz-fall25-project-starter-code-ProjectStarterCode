package project.checkpointtests;

import numberlettercountfetching.FetchApiImpl;
import numberlettercountcomputing.ComputingApi;
import numberlettercountcomputing.ComputingApiImpl;
import numberlettercountdatastoring.DataStoreApi;
import numberlettercountdatastoring.DataStoreApiImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ManualTestingFramework {

	public static final String INPUT = "manualTestInput.txt";
	public static final String OUTPUT = "manualTestOutput.txt";

	public static void main(String[] args) throws IOException {
		System.out.println("=== Starting Manual Testing Framework ===");

		// 1. Create input file with test data
		Path inputPath = Paths.get(INPUT);
		Files.write(inputPath, "1\n2\n3".getBytes(),
				StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
		System.out.println("✓ Created input file: " + INPUT);

		// 2. Create FetchApiImpl (which now includes CoordinatorAPI functionality)
		FetchApiImpl coordinator = new FetchApiImpl();

		// 3. Create the ComputingApi and DataStoreApi instances
		ComputingApi computingApi = new ComputingApiImpl();
		DataStoreApi dataStoreApi = new DataStoreApiImpl();

		// 4. Set the dependencies on FetchApiImpl
		coordinator.setComputingApi(computingApi);
		coordinator.setDataStoreApi(dataStoreApi);

		System.out.println("✓ Created FetchApiImpl with all dependencies set");
		System.out.println("✓ FetchApiImpl now coordinates between ComputingApi and DataStoreApi");

		// 5. Process the file - ALL LOGIC IS NOW INSIDE FetchApiImpl!
		System.out.println("\n=== Processing File ===");
		boolean success = coordinator.processFile(INPUT, OUTPUT);

		// 6. Report results
		if (success) {
			System.out.println("\n✓ Successfully processed input file!");
			System.out.println("Input: " + INPUT);
			System.out.println("Output: " + OUTPUT);
			System.out.println("Results: " + coordinator.getLastResults());
		} else {
			System.out.println("\n✗ Failed to process input file");
		}

		System.out.println("\n=== Manual Testing Framework Finished ===");
	}
}