package network;


import project.annotations.NetworkAPI;

@NetworkAPI
public interface UserComputeAPI {

	JobResponse submitJob(JobRequest request);

}
