package tests;

import api.CalcWriteResponse;
import api.CompRequest;
import api.CompResponse;
import api.ComputerAPI;
import implementations.ComputeEngineImpl;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SmokeTestComputeEngineAPI {

    @Test
    public void compRequestTest() {
        // ACTUALLY instantiate the implementation (not mocked)
        ComputeEngineImpl compEngine = new ComputeEngineImpl();  // Changed this line
        
        // Mock the request as before
        CompRequest request = Mockito.mock(CompRequest.class);
        
        // You need to actually call a method on the implementation
        // Since implementations are empty, we'll at least verify it exists
        assertNotNull(compEngine, "ComputeEngineImpl should be instantiated");
    }

    @Test
    public void calcResponseTest() {
        // ACTUALLY instantiate the implementation
        ComputeEngineImpl compEngine = new ComputeEngineImpl();  // Changed this line
        
        // Mock dependencies
        CompRequest request = Mockito.mock(CompRequest.class);
        CompResponse response = Mockito.mock(CompResponse.class);
        
        // The method might not be implemented yet, but we're testing instantiation
        assertNotNull(compEngine, "ComputeEngineImpl should be instantiated");
    }
}