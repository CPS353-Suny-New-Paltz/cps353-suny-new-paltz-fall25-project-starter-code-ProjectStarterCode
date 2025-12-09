package numberlettercountcomputing;

public class ComputeApi {

	public void demonstrateWorkflow() {
		System.out.println("ComputeApi: Starting demonstration workflow");

		ComputingApi computingApi = new ComputingApiImpl();

		// Demonstrate the computing workflow
		PassData passData = computingApi.passData(7);
		System.out.println("1. PassData created: " + passData);

		// Process the pass data
		java.util.List<Integer> results = computingApi.processPassData(passData);
		System.out.println("2. Processing results: " + results);

		System.out.println("Demonstration workflow completed");
	}
}


