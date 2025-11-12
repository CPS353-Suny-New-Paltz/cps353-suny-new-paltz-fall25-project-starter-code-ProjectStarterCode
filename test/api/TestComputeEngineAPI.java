package api;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;

public class TestComputeEngineAPI {
    
    @Test
    void smokeTest() {
        ComputeEngineAPI mockApi = mock(ComputeEngineAPI.class);
        
        mockApi.computeFactorial(new int[]{1, 10, 25});
    }
}