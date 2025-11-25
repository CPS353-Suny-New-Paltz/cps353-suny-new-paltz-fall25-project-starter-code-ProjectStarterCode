package apiimplementation;

import apiengine.ConceptualAPI;
import apiengine.NetworkAPI;
import apinetwork.ComputationInput;
import apinetwork.ComputationOutput;
import apinetwork.JobRequest;
import apistorage.ProcessAPI;

import java.util.List;

public class NetworkAPIImpl implements NetworkAPI {
    private final ProcessAPI processAPI;
    private final ConceptualAPI conceptual;

    public NetworkAPIImpl(ProcessAPI processAPI, ConceptualAPI conceptual) {
        if (processAPI == null || conceptual == null) {
            throw new IllegalArgumentException("processAPI and conceptual must not be null");
        }
        this.processAPI = processAPI;
        this.conceptual = conceptual;
    }

    @Override
    public ComputationOutput sendJob(JobRequest job) {
        // Validate job param
        if (job == null) {
            try {
                processAPI.writeOutput("network:null-job");
            } catch (Throwable t) {
                System.err.println("[NetworkAPIImpl] failed to write null-job marker: " + t.getMessage());
            }
            return new ComputationOutput("invalid-job");
        }

        try {
            // Batch mode
            if (job.getInputNumber() == -1) {
                List<Integer> inputs;
                try {
                    inputs = processAPI.readInputs();
                } catch (Throwable t) {
                    try {
                        processAPI.writeOutput("batch:read-error");
                    } catch (Throwable ignored) {}
                    return new ComputationOutput("batch:error");
                }

                if (inputs == null || inputs.isEmpty()) {
                    try { processAPI.writeOutput("batch:no-inputs"); } catch (Throwable ignored) {}
                    return new ComputationOutput("batch:empty");
                }

                int processed = 0;
                for (Integer v : inputs) {
                    ComputationInput ci = new ComputationInput(v, job.getDelimiters());
                    ComputationOutput out = conceptual.compute(ci);
                    String s = (out == null || out.getResult() == null) ? "null" : out.getResult();
                    try {
                        processAPI.writeOutput(s);
                    } catch (Throwable t) {
                        try { processAPI.writeOutput("batch:write-failure"); } catch (Throwable ignored) {}
                        return new ComputationOutput("batch:error");
                    }
                    processed++;
                }
                try { processAPI.writeOutput("batch:completed:" + processed); } catch (Throwable ignored) {}
                return new ComputationOutput("batch:success");
            }

            // Single job validation
            if (job.getInputNumber() < 0) {
                try { processAPI.writeOutput("network:invalid-input-number"); } catch (Throwable ignored) {}
                return new ComputationOutput("invalid-job");
            }

            ComputationInput ci = new ComputationInput(job.getInputNumber(), job.getDelimiters());
            ComputationOutput out = conceptual.compute(ci);
            String s = (out == null || out.getResult() == null) ? "null" : out.getResult();
            try {
                processAPI.writeOutput(s);
            } catch (Throwable t) {
                try { processAPI.writeOutput("network:write-failure"); } catch (Throwable ignored) {}
                return new ComputationOutput("error:write-failed");
            }
            return out;
        } catch (Exception ex) {
            String msg = ex.getMessage() == null ? "unknown" : ex.getMessage();
            try { processAPI.writeOutput("network:error:" + msg); } catch (Throwable ignored) {}
            return new ComputationOutput("error:network-exception:" + msg);
        }
    }
}
