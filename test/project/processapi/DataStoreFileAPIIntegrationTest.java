package project.processapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DataStoreFileAPIIntegrationTest {

    @Test
    void testLoadInputNoFile() {
        DataStoreComputeAPI dataStore = new DataStoreInMemory();
        List<Integer> values = dataStore.loadInput("ignored", ",");

        assertNotNull(values);
        assertEquals(List.of(), values);
    }

    @Test
    void testSaveOutputReturnsSuccess() {
        DataStoreComputeAPI dataStore = new DataStoreInMemory();
        StorageResponse response = dataStore.saveOutput("ignored", "result");

        assertEquals(StoreStatus.SUCCESS, response.getStatus());
    }
}