package numberlettercountcomputing;

import java.util.ArrayList;
import java.util.List;
import project.annotations.ConceptualAPIPrototype;

public class ComputingApiPrototype {
	private List<Integer> data;
	private int currentData;

	@ConceptualAPIPrototype
	public void prototype(ComputeApi computeapi) {
		System.out.println("=== Starting Prototype Demo ===");
		computeapi.insertRequest();
		System.out.println("=== Prototype Demo Complete ===");
	}

	public void initialize(int inputData) {
		this.currentData = inputData;
		if (this.data == null) {
			this.data = new ArrayList<>();
		}
		this.data.add(inputData);

		// Use objects during initialization
		Extract extract = new Extract();
		extract.setSource("initialization");
		extract.setExtractedData("Number: " + inputData);
		System.out.println("Initialization extract: " + extract);
	}

	public List<Integer> compute(){
		// Use processData during computation
		ProcessData processData = new ProcessData();

		// USE currentData: Include it in the processing
		String inputData = data != null ? data.toString() : "empty";
		if (currentData != 0) {
			inputData += " [current: " + currentData + "]";
		}
		processData.setInputData(inputData);
		processData.setProcessingType("prototype computation");

		System.out.println("Computing with process: " + processData);

		if (data == null) {
			return new ArrayList<>();
		}

		// USE currentData: Include it in the result
		List<Integer> result = new ArrayList<>(data);
		if (currentData != 0 && !result.contains(currentData)) {
			result.add(currentData);
		}
		return result;
	}

	public void sentData(SendInfo sendData) {
		// USE currentData: Include it in the sent data
		if (sendData != null && currentData != 0) {
			String currentDataInfo = "Current data: " + currentData;
			if (sendData.getData() != null) {
				sendData.setData(sendData.getData() + " | " + currentDataInfo);
			} else {
				sendData.setData(currentDataInfo);
			}
		}

		// Actually use the sendData object
		System.out.println("Prototype sending data: " + sendData);
		if (sendData != null) {
			System.out.println("Data: " + sendData.getData() + " to: " 
					+ sendData.getDestination());
		}
	}

	public void recieveData(RecieveInfo recieveData) {
		// USE currentData: Update currentData based on received data
		if (recieveData != null && recieveData.getData() != null) {
			try {
				// Try to parse received data as integer to update currentData
				int receivedValue = Integer.parseInt(recieveData.getData());
				this.currentData = receivedValue;
				System.out.println("Updated currentData to: " + currentData);
			} catch (NumberFormatException e) {
				// If not a number, keep currentData as is
				System.out.println("Received data is not a number, currentData remains: " + currentData);
			}
		}

		// Actually use the recieveData object
		System.out.println("Prototype receiving data: " + recieveData);
		if (recieveData != null) {
			System.out.println("Data: " + recieveData.getData() + 
					" from: " + recieveData.getSource());
		}
	}

	public void extractData(Extract extractData) {
		// USE currentData: Include it in extraction
		if (extractData != null && currentData != 0) {
			extractData.setExtractedData("Current value: " + currentData + 
					(extractData.getExtractedData() != null ? 
							" | " + extractData.getExtractedData() : ""));
		}

		// Actually use the extractData object
		System.out.println("Prototype extracting data: " + extractData);
		if (extractData != null) {
			System.out.println("From: " + extractData.getSource() + " method: "
					+ extractData.getExtractionMethod());
		}
	}

	public void pass(PassData passData) {
		// USE currentData: Include it in passed data
		if (passData != null && currentData != 0) {
			passData.setData("Current: " + currentData + 
					(passData.getData() != null ? " | " + passData.getData() : ""));
		}

		// Actually use the passData object
		System.out.println("Prototype passing data: " + passData);
		if (passData != null) {
			System.out.println("From: " + passData.getFromComponent() + " to: " 
					+ passData.getToComponent());
		}
	}

	public void process(ProcessData processData) {
		// USE currentData: Include it in processing
		if (processData != null && currentData != 0) {
			String currentOutput = "Processed current data: " + currentData;
			if (processData.getOutputData() != null) {
				processData.setOutputData(processData.getOutputData() + " | " + currentOutput);
			} else {
				processData.setOutputData(currentOutput);
			}
		}

		// Actually use the processData object
		System.out.println("Prototype processing data: " + processData);
		if (processData != null) {
			System.out.println("Input: " + processData.getInputData() + " output: " 
					+ processData.getOutputData());
		}
	}

	// ADDED: Getter method to access currentData
	public int getCurrentData() {
		return currentData;
	}

	// ADDED: Method to demonstrate currentData usage
	public void displayCurrentData() {
		System.out.println("Current data value: " + currentData);
	}
}