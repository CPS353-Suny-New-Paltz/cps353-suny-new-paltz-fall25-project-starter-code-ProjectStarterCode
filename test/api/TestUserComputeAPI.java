package api;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;

public class TestUserComputeAPI {
    
    @Test
    void smokeTest() {
        UserComputeAPI mockApi = mock(UserComputeAPI.class);
        
        mockApi.computeFactorial("input.txt", "output.txt", "|");
    }
}