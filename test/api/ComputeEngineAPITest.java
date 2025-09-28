package test.api;

import api.ComputeEngineAPI;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class ComputeEngineAPITest {
    
    @Test
    void smokeTest() {
        ComputeEngineAPI mockApi = mock(ComputeEngineAPI.class);
        
        mockApi.computeFactorial(new int[]{1, 10, 25});
    }
}