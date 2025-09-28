package test.api;

import api.StorageComputeAPI;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class StorageComputeAPITest {
    
    @Test
    void smokeTest() {
        StorageComputeAPI mockApi = mock(StorageComputeAPI.class);
        mockApi.computeFactorial(null, null);
    }
}