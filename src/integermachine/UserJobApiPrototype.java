package integermachine;

import project.annotations.NetworkAPIPrototype;

public class UserJobApiPrototype {

    @NetworkAPIPrototype
    public void prototype(UserJobApi api) {
        // Dummy prototype call — never executed, only for Checkpoint testing
        JobConfig job = new JobConfig(null, null, null);

        api.submitJob(job);
    }
}
