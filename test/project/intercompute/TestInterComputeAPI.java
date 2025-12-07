package project.intercompute;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestInterComputeAPI {

    @Test
    void smokeTestInterCompute() {
        InterComputeAPI engine = new InterComputeAPIImpl();
        int result = engine.processRequest(new InterRequest(10));
        
        assertEquals(7, result);
       
    }

    @Test
    void testInvalidInput() {
        InterComputeAPI engine = new InterComputeAPIImpl();
        int result = engine.processRequest(new InterRequest(-1));
        assertEquals(-1, result, "Invalid inputs should return sentinel -1");
    }
}
