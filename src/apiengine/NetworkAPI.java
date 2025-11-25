package apiengine;

import apinetwork.ComputationOutput;
import apinetwork.JobRequest;

@project.annotations.NetworkAPI
public interface NetworkAPI {
	ComputationOutput sendJob(JobRequest job);
}
