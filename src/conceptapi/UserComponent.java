package conceptapi;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import assets.UserInputHandler;


public class UserComponent implements UserInterface{
	@Override
	public HashMap<Integer, String> getValues(int jobID) throws IOException {
		File file= new File("Output/valueA.txt");
		Scanner input = new Scanner(file);

		int valueA = 0;
		while(input.hasNext()) {
			String values = input.next();
			valueA = Integer.parseInt(values);
		}
		UserInputHandler handler = new UserInputHandler();

		return handler.setInputMap(valueA, jobID);


	}

}