package apiuser;

import apinetwork.JobRequest;
import apinetwork.JobSpecification;
//import project.annotations.NetworkAPI;

//@NetworkAPI
public interface UserComputeAPI {
    JobRequest createJob(JobSpecification spec);
}
