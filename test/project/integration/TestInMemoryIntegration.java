package project.integration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import project.datacompute.InMemoryDataComputeAPI;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestInMemoryIntegration {

    @Test
    void pipelineTransfersAllIntegers() {
        List<Integer> input = Arrays.asList(1, 2, 3);
        List<Integer> sink = new ArrayList<>();

        InMemoryInpCon inCfg = new InMemoryInpCon(input);
        InMemoryOutCon outCfg = new InMemoryOutCon(sink);

        InMemoryDataComputeAPI data = new InMemoryDataComputeAPI(inCfg, outCfg);

        List<Integer> readValues = data.readInput("ignored");
        data.writeOutput(readValues, "ignored");

        assertEquals(Arrays.asList(1, 2, 3), sink);
    }
}
