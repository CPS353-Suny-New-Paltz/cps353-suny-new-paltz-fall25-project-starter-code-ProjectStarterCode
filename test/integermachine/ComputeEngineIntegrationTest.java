package integermachine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class ComputeEngineIntegrationTest {

    @Test
    void integration_largestPrimeBelow_forSampleInput() {
        InMemoryInputConfig inputConfig =
                new InMemoryInputConfig(List.of(1, 10, 25));
        InMemoryOutputConfig outputConfig =
                new InMemoryOutputConfig();

        StorageProcessApi storage =
                new InMemoryStorageProcessApi();

        ConceptualComputeApi compute =
                new ConceptualComputeApiImpl();

        Orchestrator orchestrator =
                new Orchestrator(storage, compute);

        UserJobApi networkApi =
                new UserJobApiImpl(orchestrator);

        JobConfig config =
                new JobConfig(inputConfig, outputConfig, null);


        networkApi.submitJob(config);


        assertEquals(
                List.of("1:1", "10:7", "25:23"),
                outputConfig.getOutputs()
        );
    }
}

