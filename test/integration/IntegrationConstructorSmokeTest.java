package integration;

import apiimplementation.ConceptualAPIImpl;
import apiimplementation.NetworkAPIImpl;
import apinetwork.Delimiters;
import apinetwork.JobRequest;
import testhelpers.TestInputConfig;
import testhelpers.TestOutputConfig;
import testhelpers.TestProcessDataStore;
import org.junit.jupiter.api.Test;

public class IntegrationConstructorSmokeTest {

    @Test
    public void testIntegrationSmoke() {
        TestInputConfig inputConfig = new TestInputConfig(java.util.Collections.emptyList());
        TestOutputConfig outputConfig = new TestOutputConfig();
        TestProcessDataStore store = new TestProcessDataStore(inputConfig, outputConfig);

        ConceptualAPIImpl conceptual = new ConceptualAPIImpl();
        NetworkAPIImpl network = new NetworkAPIImpl(store, conceptual);

        network.sendJob(new JobRequest(10, new Delimiters(":", " × ")));
    }
}
