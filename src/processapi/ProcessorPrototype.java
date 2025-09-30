package processapi;



import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import assets.ResponseCode;
import assets.UserInputHandler;
import project.annotations.ProcessAPIPrototype;


public class ProcessorPrototype {
	@ProcessAPIPrototype
			public HashMap<Integer, String> processAPI(ProcessorAPI process) {
		//Runs conversion from txt document to hashMap
		//ProcessAPI needs to have a method that prompts the user to input data
		//and also needs to write a method that creates a file
		//Step 1: processor calls userInputHandler and prompts user for input;
		UserInputHandler handler = new UserInputHandler();
		System.out.println("Please enter an integer: ");
		try {
		int valueA = handler.promptValueA();
		}catch(IOException e){
			System.out.println("Input could not be entered");
		}
		HashMap<Integer, String>newMap = new HashMap<>();// supposed to return fully processed hashmap with answers from computation
		//Create a file that holds valueA to be fully processed later
		//Step 2: ConceptualAPI takes valueA file and creates hashMap with new data
		 //hashmap is meant to store the value of A, create a value for b, and assign a jobID to the recent job
		//
		//TBA
		//Step 4" conceptual api sends data back and processor places it into storage file
		HashMap<Integer, String>answerKey = new HashMap<>();
		WriteData.writeData(answerKey);



		//Lastly Check if file exists
		ResponseCode code = ResponseCode.FAILURE;
		File file = new File("Output/answers.txt");
	    if (!file.exists()) {
	        System.out.println("File write failed: No file was created.");

	        System.out.println(code);

	    } else {
	        System.out.println("Conversion successful: " + newMap.size() + " entries added.");
	        code.isSuccess();
	    }




			 return newMap;
		}
}
