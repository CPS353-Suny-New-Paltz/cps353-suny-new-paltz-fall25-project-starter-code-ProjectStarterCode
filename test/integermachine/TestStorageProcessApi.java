package integermachine;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collections;

import org.junit.jupiter.api.Test;


public class TestStorageProcessApi {

    @Test
    void readInputs_returnsNonNullBatch() {
        StorageProcessApi api = new StorageProcessApiImpl();
        IntStreamBatch batch = api.readInputs(new InputSourceRef("dummy"));

        assertNotNull(batch);
        assertNotNull(batch.getValues());
    }

    @Test
    void writeOutputs_acceptsEmptyResults() {
        StorageProcessApi api = new StorageProcessApiImpl();
        api.writeOutputs(
                new OutputSinkRef("dummy"),
                Collections.emptyList(),
                null
        );
        // no exception = pass
    }
}
