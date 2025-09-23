package network;


import project.annotations.NetworkAPI;

public interface UserComputeAPI {

	JobResponse submitJob(JobRequest request);

}
