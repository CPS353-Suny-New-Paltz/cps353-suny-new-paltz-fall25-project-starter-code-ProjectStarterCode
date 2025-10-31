package apiuser;

import apinetwork.JobRequest;
import apinetwork.JobSpecification;
//import project.annotations.NetworkAPIPrototype;

public class UserComputeAPIPrototype {
//    @NetworkAPIPrototype
    public UserComputeAPI prototype(UserComputeAPI api) {
        return new UserComputeAPI() {
            @Override
            public JobRequest createJob(JobSpecification spec) {
                return new JobRequest(spec.getInputNumber(), spec.getDelimiters());
            }
        };
    }
}
