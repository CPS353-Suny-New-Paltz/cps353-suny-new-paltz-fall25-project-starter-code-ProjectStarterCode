package apistorage;

import java.util.Arrays;
import java.util.List;

public class ProcessAPIPrototype {

	@project.annotations.ProcessAPIPrototype
    public void demo(ProcessAPI api) {
       //example input
		List<String> input= Arrays.asList("1", "10");
		System.out.println("input:  " +input);
		
		//example output
		boolean successfulOutput = api.writeOutput("result");
		System.out.println("output:  " + successfulOutput);
		
		
    }
}
