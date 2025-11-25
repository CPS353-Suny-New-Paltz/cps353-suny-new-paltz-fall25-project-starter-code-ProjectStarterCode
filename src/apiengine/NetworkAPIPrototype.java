package apiengine;

import apinetwork.JobRequest;
import apinetwork.Delimiters;
import apinetwork.ComputationOutput;

public class NetworkAPIPrototype {
	@project.annotations.NetworkAPIPrototype
    public void runPrototype(NetworkAPI api) {
        // Create a job request
        JobRequest job = new JobRequest(10, new Delimiters(":", " × "));

        // Call the API
        ComputationOutput result = api.sendJob(job);

        // Print placeholder output for prototype verification
        System.out.println("[NetworkAPIPrototype] Sent job: " + job.getInputNumber());
        System.out.println("[NetworkAPIPrototype] Received output: " + result.getResult());
    }
}
