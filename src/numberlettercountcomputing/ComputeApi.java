package numberlettercountcomputing;

import java.util.Arrays;
import java.util.List;

//import project.annotations.ConceptualAPI;

//@ConceptualAPI its wrong because it shouldn't be in a class instead interfaces only
public class ComputeApi {


	public void insertRequest() {
		System.out.println("ComputeApi: Starting complete workflow");

		ComputingApiImpl computingApi = new ComputingApiImpl();

		// Use extractData and process it
		Extract extract = computingApi.extractData();
		extract.setSource("ComputeApi");
		extract.setExtractedData("sample numbers: 1,2,3");
		System.out.println("1. Extracted: " + extract);

		// Process the extracted data
		ProcessData processData = computingApi.processData();
		processData.setInputData(extract.getExtractedData());
		System.out.println("2. Processing: " + processData);

		// Pass data between components
		PassData passData = computingApi.passData();
		passData.setData(processData.getInputData());
		passData.setFromComponent("ComputeApi");
		passData.setToComponent("ComputingEngine");
		System.out.println("3. Passing: " + passData);

		// Actually compute something
		List<Integer> numbers = Arrays.asList(1, 2, 3);
		String result = computingApi.initalize(numbers);
		System.out.println("4. Computation result: " + result);

		// Send the result
		SendInfo sendInfo = computingApi.sendInfo();
		sendInfo.setData(result);
		sendInfo.setDestination("Client");
		System.out.println("5. Sending: " + sendInfo);

		// Receive confirmation
		RecieveInfo recieveInfo = computingApi.recieveInfo();
		recieveInfo.setData("Result received: " + result);
		recieveInfo.setSource("Client");
		System.out.println("6. Receiving: " + recieveInfo);

		System.out.println("Complete workflow finished successfully");
	}
}
