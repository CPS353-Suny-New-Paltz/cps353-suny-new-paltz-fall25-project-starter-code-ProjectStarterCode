package test.api;

import api.UserComputeAPI;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class UserComputeAPITest {
    
    @Test
    void smokeTest() {
        UserComputeAPI mockApi = mock(UserComputeAPI.class);
        
        mockApi.computeFactorial("input.txt", "output.txt", "|");
    }
}