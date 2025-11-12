package api.testing;

import org.junit.jupiter.api.Test;

import api.StorageComputeAPI;

import static org.mockito.Mockito.mock;

class TestStorageComputeAPI {
    
    @Test
    void smokeTest() {
        StorageComputeAPI mockApi = mock(StorageComputeAPI.class);
        
        // Call the methods 
        mockApi.readNumbers("test.txt");
        mockApi.computeFactorial(new int[]{1, 10, 25}); // Using [1,10,25] here too
        mockApi.writeResult("out.txt", new String[]{"1", "3628800", "15511210043330985984000000"}, "");
    }
}