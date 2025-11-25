package apiengine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import apiimplementation.ConceptualAPIImpl;
import apiimplementation.NetworkAPIImpl;
import apiimplementation.ConceptualAPIImpl;
import apinetwork.Delimiters;
import apinetwork.JobRequest;
import testhelpers.TestInputConfig;
import testhelpers.TestOutputConfig;
import testhelpers.TestProcessDataStore;

import java.util.Arrays;
import java.util.List;

/**
 * Tests for NetworkAPIImpl that assert observable outputs (no Mockito.verify).
 */
public class TestNetworkAPI {

    @Test
    public void testSendJob_ValidRequest_writesResultToStore() {
        TestInputConfig in = new TestInputConfig(Arrays.asList());
        TestOutputConfig outConfig = new TestOutputConfig();
        TestProcessDataStore store = new TestProcessDataStore(in, outConfig);

        ConceptualAPIImpl conceptual = new ConceptualAPIImpl();
        //The constructor ConceptualAPIImpl() is undefined
        NetworkAPIImpl network = new NetworkAPIImpl(store, conceptual);
        //The constructor NetworkAPIImpl(TestProcessDataStore, ConceptualAPIImpl) is undefined

        JobRequest req = new JobRequest(5, new Delimiters(":", " × "));
        apinetwork.ComputationOutput out = network.sendJob(req);

        assertNotNull(out);
        List<String> outputs = outConfig.getOutputs();
        assertEquals(1, outputs.size());
        assertEquals("5", outputs.get(0));
    }

    @Test
    public void testSendJob_BatchMode_readsStoreAndWritesResults() {
        TestInputConfig in = new TestInputConfig(Arrays.asList(1, 10, 25));
        TestOutputConfig outConfig = new TestOutputConfig();
        TestProcessDataStore store = new TestProcessDataStore(in, outConfig);

        ConceptualAPIImpl conceptual = new ConceptualAPIImpl();
        //The constructor ConceptualAPIImpl() is undefined
        NetworkAPIImpl network = new NetworkAPIImpl(store, conceptual);
        //The constructor ConceptualAPIImpl() is undefined

        JobRequest batchJob = new JobRequest(-1, new Delimiters(":", " × "));
        apinetwork.ComputationOutput summary = network.sendJob(batchJob);

        assertNotNull(summary);
        List<String> outputs = outConfig.getOutputs();
        assertEquals(4, outputs.size()); // 3 results + summary
        assertEquals("1", outputs.get(0));
        assertEquals("2 × 5", outputs.get(1));
        assertEquals("5 × 5", outputs.get(2));
        assertEquals("batch:completed:3", outputs.get(3));
    }
}
