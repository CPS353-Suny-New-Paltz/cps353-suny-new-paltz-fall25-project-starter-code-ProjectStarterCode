package project.usercompute;

import project.annotations.NetworkAPI;

@NetworkAPI
public interface UserComputeAPI {

    ComputeResponse compute(ComputeRequest request);
}
