package project.integration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import project.datacompute.DataRequest;
import project.datacompute.InMemoryDataComputeAPI;   
import project.intercompute.InterComputeAPIImpl;
import project.usercompute.UserComputeAPIImpl;
import project.usercompute.UserRequest;

public class ComputeEngineIntegrationTest {

    @Test
    void runsEndToEndAndProducesExpectedOutput() {
   
        List<Integer> input = Arrays.asList(1, 10, 25);
        List<String> sink = new ArrayList<>();
       
        InMemoryInpCon inCfg = new InMemoryInpCon(input);
        InMemoryOutCon outCfg = new InMemoryOutCon(sink);
        
        InMemoryDataComputeAPI data = new InMemoryDataComputeAPI(inCfg, outCfg);
       
        InterComputeAPIImpl inter = new InterComputeAPIImpl(data);
        UserComputeAPIImpl user = new UserComputeAPIImpl(inter, data);

        user.handleRequest(new UserRequest(new byte[]{0}));

        // expected outp
        List<String> expected = Arrays.asList("1", "10", "25");

        // prolly fail
        assertEquals(expected, sink);
    }
}