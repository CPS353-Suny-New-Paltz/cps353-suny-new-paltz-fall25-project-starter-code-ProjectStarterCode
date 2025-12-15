package integermachine;

import java.util.List;

import project.annotations.ProcessAPI;

@ProcessAPI
public interface StorageProcessApi {

    IntStreamBatch readInputs(InputSourceRef input);

    void writeOutputs(OutputSinkRef output,
                      List<KeyValueResult> results,
                      Delimiters delimiters);
}
