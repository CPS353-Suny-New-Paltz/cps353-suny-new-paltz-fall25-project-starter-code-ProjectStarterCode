package testharness;

import java.io.File;
import java.util.Objects;

import network.UserComputeAPI;
import network.JobRequest;
import network.JobRequestImpl;


public class TestUser {
	
	// TODO 3: change the type of this variable to the name you're using for your
	// @NetworkAPI interface; also update the parameter passed to the constructor
	private final UserComputeAPI coordinator;

	public TestUser(UserComputeAPI coordinator) {
		this.coordinator = coordinator;
	}

	public void run(String outputPath) {
		char delimiter = ';';
		String inputPath = "test" + File.separatorChar + "testInputFile.test";
		
		// TODO 4: Call the appropriate method(s) on the coordinator to get it to 
		// run the compute job specified by inputPath, outputPath, and delimiter
		JobRequest request = new JobRequestImpl(inputPath, outputPath, String.valueOf(delimiter));
		coordinator.submitJob(request);
	}

}
