package integermachine;

import project.annotations.NetworkAPI;

@NetworkAPI
public interface UserJobApi {
    String submitJob(JobConfig config);
}
