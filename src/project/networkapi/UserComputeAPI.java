package project.networkapi;

import project.annotations.NetworkAPI;

@NetworkAPI
public interface UserComputeAPI {
    UserSubResponse submit(UserSubmission submission);
}