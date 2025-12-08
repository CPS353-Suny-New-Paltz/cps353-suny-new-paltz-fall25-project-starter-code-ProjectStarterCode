package tests;

import api.DataCheck;
import api.ReadInput;
import api.WriteOutput;
import implementations.DataProcessImpl;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class SmokeTestDataProcessAPI {

	private static final ArrayList<Integer> NUM = (ArrayList<Integer>) Arrays.asList(1, 10, 25); 
	

	@Test
	public void readInputTest() {
		DataProcessImpl api = new DataProcessImpl();


		// Takes in the file and integers
		ReadInput input = api.input(NUM);

		// Assert
		assertNotNull(input, "1,10, 25");

	}


	@Test
	public void writeDataTest() {
		DataProcessImpl api = new DataProcessImpl();
		DataCheck dataCheck = Mockito.mock(DataCheck.class);

		WriteOutput data = api.sendData(dataCheck.sendList());

		assertNotNull(data, "The Right Output");	
	}
}
