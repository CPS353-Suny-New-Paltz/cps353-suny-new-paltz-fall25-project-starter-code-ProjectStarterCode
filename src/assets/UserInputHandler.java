package assets;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class UserInputHandler {
	private final HashMap<Integer, String> inputMap;
	private final Scanner input;
	private int valueA;
	private String delimiter;
	private UserRequest request;





	public String getDelimiter() {
		return delimiter;
	}



	public UserInputHandler() {
		input = new Scanner(System.in);
		inputMap = new HashMap<>();
	}

	public HashMap<Integer, String> setInputMap(int valueA, int jobID) {

		int valueB = (int) ((Math.random() * 2001) - 1000) / 2; // creates random second value
		String input = valueA + delimiter + valueB;
		// place inputs into hashmap with a jobID assigned to it
		if(inputMap.containsKey(jobID)) {
			jobID = (int) ((Math.random() * 2000) + 1); //randomizes jobID again if same jobID is created
		}
		inputMap.put(jobID, input);

		return inputMap;
	}


	public int promptValueA() throws IOException {
		System.out.println("Select a delimiter, this has to be a punctuation mark! EX: !,/?");
		this.delimiter = request.getDelimiter(); //gets delimiter from userRequest
		System.out.println("Enter value for a: \n");
		valueA = input.nextInt();
		// For euler project 27 user cannot enter an integer value higher than 1000 or
		// lower than -1000
		if (valueA > 1000 || valueA < -1000 || valueA == 0) {
			System.out.println("Invalid input please try again!");
			System.out.println("Enter value for a: \n");
			valueA = input.nextInt();
		}

		writeValueA(valueA, "Output/valueA.txt");//creates file that just stores valueA to be used later

		return valueA;

	}

	public int getValueA(HashMap<Integer, String> map, int jobID) {

		String values = map.get(jobID);
		String[]splitter = values.split(delimiter);
		valueA = Integer.parseInt(splitter[0]);
		return valueA;

	}

	public int getValueB(HashMap<Integer, String> map, int jobID) {

		String values = map.get(jobID);
		String[]splitter = values.split(delimiter);
		int valueB = Integer.parseInt(splitter[1]);
		return valueB;
	}


public void writeInputToFile(String filePath) {
    try (FileWriter writer = new FileWriter(filePath)) {
        for (Integer jobID : inputMap.keySet()) {
            String inputLine = jobID + ": " + inputMap.get(jobID) + "\n";
            writer.write(inputLine);
        }
        System.out.println("Input data successfully written to " + filePath);
    } catch (IOException e) {
        System.out.println("Error writing to file: " + e.getMessage());
    }

}



public void writeValueA(int valueA,String filePath) throws IOException {
	try(FileWriter writer = new FileWriter(filePath)){
		writer.write(valueA);
		writer.close();
	}

}


}
