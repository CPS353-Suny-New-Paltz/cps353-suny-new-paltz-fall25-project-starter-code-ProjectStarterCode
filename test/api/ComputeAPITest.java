package api;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test; 

import conceptualapi.ComputeAPI;
import implementations.ComputeImplement;

public class ComputeAPITest {

    @Test
    // not working needs implementation checkpoint 4
    void computationTest() {
        ComputeAPI compute = new ComputeImplement();
        String result = compute.compute(5);

        assertNotNull(result, "Result should not be null");
        assertTrue(result.contains("25"), "Expected squared result to appear in output");
    }
}
