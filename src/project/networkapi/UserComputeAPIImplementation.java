package project.networkapi;

public class UserComputeAPIImplementation implements UserComputeAPI {
    @Override
    public UserSubResponse submit(UserSubmission submission) {
        // Default success until implemented
        return new UserSubResponse("sub-1", SubmissionStatus.SUCCESS);
    }
}
