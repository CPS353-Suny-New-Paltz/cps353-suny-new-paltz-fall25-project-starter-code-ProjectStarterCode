package apiengine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import apiimplementation.ConceptualAPIImpl;
import apiimplementation.NetworkAPIImpl;
import testhelpers.TestInputConfig;
import testhelpers.TestOutputConfig;
import testhelpers.TestProcessDataStore;

public class TestNetworkAPINullJob {

    @Test
    public void sendJob_nullJob_writesNetworkNullMarkerAndReturnsInvalid() {
        TestInputConfig in = new TestInputConfig(Collections.emptyList());
        TestOutputConfig outConfig = new TestOutputConfig();
        TestProcessDataStore store = new TestProcessDataStore(in, outConfig);

        ConceptualAPIImpl conceptual = new ConceptualAPIImpl();
        NetworkAPIImpl network = new NetworkAPIImpl(store, conceptual);

        apinetwork.ComputationOutput result = network.sendJob(null);

        assertNotNull(result, "Result should not be null for null job");
        List<String> outputs = outConfig.getOutputs();
        assertEquals("network:null-job", outputs.get(0), "Expected 'network:null-job' marker written");
    }
}
