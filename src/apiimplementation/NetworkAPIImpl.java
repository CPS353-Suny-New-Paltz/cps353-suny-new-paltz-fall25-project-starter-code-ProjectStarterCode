package apiimplementation;

import apiengine.NetworkAPI;
import apinetwork.JobRequest;
import apinetwork.ComputationOutput;
import apistorage.ProcessAPI;

public class NetworkAPIImpl implements NetworkAPI {
    private final ProcessAPI processAPI;

    public NetworkAPIImpl(ProcessAPI processAPI) {
        this.processAPI = processAPI;
    }

    @Override
    public ComputationOutput sendJob(JobRequest job) {
        if (job == null) {
            processAPI.writeOutput("network:null-job");
            return new ComputationOutput("invalid-job");
        }

        String result = "network:queued:" + job.getInputNumber();
        processAPI.writeOutput(result);

        // Returning placeholder
        return new ComputationOutput(result);
    }
}
