package testharness;

import java.io.File;

import project.usercompute.ComputeRequest;
import project.usercompute.UserComputeAPI;


public class TestUser {
	
	// TODO 3: change the type of this variable to the name you're using for your
	// @NetworkAPI interface; also update the parameter passed to the constructor
	private final UserComputeAPI coordinator;

	public TestUser(UserComputeAPI coordinator) {
		this.coordinator = coordinator;
	}

	public void run(String outputPath) {
	    String inputPath = "test" + File.separator + "testInputFile.test";

	    ComputeRequest request = new ComputeRequest(inputPath, outputPath);

	    coordinator.compute(request);
	}


}
