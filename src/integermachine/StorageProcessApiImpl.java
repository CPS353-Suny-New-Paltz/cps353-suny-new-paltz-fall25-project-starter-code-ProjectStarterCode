package integermachine;

import java.util.Collections;
import java.util.List;

public class StorageProcessApiImpl implements StorageProcessApi {

    @Override
    public IntStreamBatch readInputs(InputSourceRef input) {
        // Checkpoint 3: empty implementation, no real IO yet.
        return new IntStreamBatch(Collections.emptyList());
    }

    @Override
    public void writeOutputs(OutputSinkRef output,
                             List<KeyValueResult> results,
                             Delimiters delimiters) {
        // Checkpoint 3: empty implementation, do nothing for now.
    }
}
