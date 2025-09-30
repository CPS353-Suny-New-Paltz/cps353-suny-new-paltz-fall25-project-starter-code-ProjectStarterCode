package processapi;

import java.util.HashMap;
import java.util.List;

import assets.UserInputHandler;



public class WriteData {
	public static void writeData(HashMap<Integer, String>map) {
		String path = "Output/answers.txt";
		UserInputHandler handler= new UserInputHandler();
		handler.writeInputToFile(path);
	}
	
	public static void writeData(List<Integer>map) {
		String path = "Output/answers.txt";
		UserInputHandler handler= new UserInputHandler();
		handler.writeInputToFile(path);
	}

}
