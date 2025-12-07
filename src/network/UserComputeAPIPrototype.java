package network;

import project.annotations.NetworkAPIPrototype;

public class UserComputeAPIPrototype {
	
	@NetworkAPIPrototype
	public void prototype(UserComputeAPI api) {
		JobRequest request = new JobRequest() {
			public String getInputSource() { 
				return "input.txt"; 
			}
			public String getOutputDestination() { 
				return "output.txt"; 
			}
			public String getDelimiter() { 
				return ":"; 
			}
		};
		
		JobResponse response = api.submitJob(request);
		
		if (response.isSuccess()) {
			System.out.println("Job submitted successfully: " + response.getMessage());
		} else {
			System.out.println("Job submission failed: " + response.getMessage());
		}

	}
}
