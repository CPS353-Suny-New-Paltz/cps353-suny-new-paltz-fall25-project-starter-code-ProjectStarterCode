package integermachine;

import project.annotations.ProcessAPIPrototype;

public class StorageProcessApiPrototype {

    @ProcessAPIPrototype
    public void prototype(StorageProcessApi api) {
        // Dummy prototype calls — not executed, only here for Checkpoint 2
        api.readInputs(null);            // InputSourceRef, null is fine for prototype
        api.writeOutputs(null, null, null); // OutputSinkRef, List<KeyValueResult>, Delimiters
    }
}
