package project.processapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestDataStoreComputeAPI {

    @Test
    void testLoadInputFromFile() throws Exception {
        // Create a temp input file
        Path tempInput = Files.createTempFile("datastore-input", ".txt");
        Files.writeString(tempInput, "5, 7, 11");

        DataStoreComputeAPI dataStore = new DataStoreImplementation();
        List<Integer> values = dataStore.loadInput(tempInput.toString(), ",");

        assertNotNull(values);
        assertEquals(List.of(5, 7, 11), values);
    }

    @Test
    void testSaveOutputWritesContent() throws Exception {
        // Create a temp output
        Path tempOutput = Files.createTempFile("datastore-output", ".txt");

        DataStoreComputeAPI dataStore = new DataStoreImplementation();
        StorageResponse response = dataStore.saveOutput(tempOutput.toString(), "result");

        assertEquals(StoreStatus.SUCCESS, response.getStatus());
        assertEquals("result", Files.readString(tempOutput));
    }
}