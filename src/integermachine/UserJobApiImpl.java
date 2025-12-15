package integermachine;

public class UserJobApiImpl implements UserJobApi {

    private final Orchestrator orchestrator;

    public UserJobApiImpl(Orchestrator orchestrator) {
        this.orchestrator = orchestrator;
    }

    @Override
    public String submitJob(JobConfig config) {
        orchestrator.runJob(config);
        return "OK";
    }
}
