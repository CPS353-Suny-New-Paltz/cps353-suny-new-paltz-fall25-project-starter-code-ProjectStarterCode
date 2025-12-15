package numberlettercountcomputing;

import java.util.ArrayList;
import java.util.List;

import project.annotations.ConceptualAPIPrototype;

public class ComputingApiPrototype {
	private List<Integer> data;
	private int currentData;

	@ConceptualAPIPrototype
	public void prototype(ComputingApi computingApi) {
		System.out.println("=== Starting Computing API Prototype Demo ===");

		// Test passData method
		PassData passData1 = computingApi.passData(5);
		System.out.println("1. Created PassData: " + passData1);

		// Test processPassData method
		List<Integer> results1 = computingApi.processPassData(passData1);
		System.out.println("2. Processed PassData results: " + results1);

		// Test with another number
		PassData passData2 = computingApi.passData(12);
		System.out.println("3. Created PassData: " + passData2);

		List<Integer> results2 = computingApi.processPassData(passData2);
		System.out.println("4. Processed PassData results: " + results2);

		System.out.println("=== Computing API Prototype Demo Complete ===");
	}

	public void initialize(int inputData) {
		this.currentData = inputData;
		if (this.data == null) {
			this.data = new ArrayList<>();
		}
		this.data.add(inputData);
		System.out.println("Initialized with data: " + inputData);
	}

	public List<Integer> compute(){
		List<Integer> result = new ArrayList<>();
		if (data != null) {
			result.addAll(data);
		}
		if (currentData != 0 && !result.contains(currentData)) {
			result.add(currentData);
		}
		System.out.println("Computed result: " + result);
		return result;
	}

	public int getCurrentData() {
		return currentData;
	}
}