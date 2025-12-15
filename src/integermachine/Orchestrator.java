package integermachine;

import java.util.ArrayList;
import java.util.List;

public class Orchestrator {
    private final StorageProcessApi storage;
    private final ConceptualComputeApi compute;

    public Orchestrator(StorageProcessApi storage, ConceptualComputeApi compute) {
        this.storage = storage;
        this.compute = compute;
    }

    public void runJob(JobConfig config) {
        Delimiters d = (config.getDelimiters() == null)
                ? Delimiters.defaults()
                : config.getDelimiters();

        IntStreamBatch batch = storage.readInputs(config.getInputSource());
        List<KeyValueResult> results = new ArrayList<>();

        for (int n : batch.getValues()) {
            ComputeResult r = compute.compute(n);
            results.add(new KeyValueResult(r.getInput(), r.asText()));
        }

        storage.writeOutputs(config.getOutputSink(), results, d);
    }
}
