package integration;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import apiimplementation.ConceptualAPIImpl;
import apiimplementation.NetworkAPIImpl;
import apinetwork.ComputationOutput;
import apinetwork.Delimiters;
import apinetwork.JobRequest;
import apistorage.ProcessAPIWrapper;
import apiimplementation.ProcessAPIImpl;
import org.junit.jupiter.api.Test;

public class IntegrationExceptionHandlingTest {

    @Test
    public void whenStorageThrows_networkReturnsErrorResultNotException() {
        ProcessAPIImpl base = new ProcessAPIImpl();
        ProcessAPIWrapper wrapper = new ProcessAPIWrapper(base);
        wrapper.setLog(false);

        // simulate one failure on next writeOutput()
        wrapper.simulateFailures(1);

        ConceptualAPIImpl conceptual = new ConceptualAPIImpl();
        NetworkAPIImpl network = new NetworkAPIImpl(wrapper, conceptual);

        JobRequest req = new JobRequest(5, new Delimiters(":", " × "));

        ComputationOutput out = null;
        try {
            out = network.sendJob(req);
        } catch (Throwable t) {
            throw new AssertionError("Network threw an exception instead of returning an error ComputationOutput", t);
        }

        assertNotNull(out, "Network returned null rather than an error ComputationOutput");
        String r = out.getResult();
        assertTrue(r != null && (r.startsWith("error:") || r.equals("invalid-job")),
                "Expected an error sentinel result starting with 'error:' but got: " + r);
    }
}
