package integermachine;

import java.util.List;


public class InMemoryStorageProcessApi implements StorageProcessApi {

    @Override
    public IntStreamBatch readInputs(InputSourceRef input) {
        InMemoryInputConfig cfg = (InMemoryInputConfig) input;
        return new IntStreamBatch(cfg.getValues());
    }

    @Override
    public void writeOutputs(OutputSinkRef output,
                             List<KeyValueResult> results,
                             Delimiters delimiters) {
        InMemoryOutputConfig cfg = (InMemoryOutputConfig) output;
        for (KeyValueResult kv : results) {
            cfg.getOutputs().add(kv.getInput() + ":" + kv.getResult());
        }
    }
}
