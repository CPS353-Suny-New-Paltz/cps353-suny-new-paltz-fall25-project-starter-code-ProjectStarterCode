package project.intercompute;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFastInterComputeAPI {

    @Test
    void testFastCorrectnessSmallValues() {
        InterComputeAPI fast = new FastInterComputeAPIImpl();

        assertEquals(7, fast.processRequest(new InterRequest(10)));
        assertEquals(23, fast.processRequest(new InterRequest(25)));
        assertEquals(2, fast.processRequest(new InterRequest(2)));
        assertEquals(-1, fast.processRequest(new InterRequest(1)));
    }

    @Test
    void testFastCorrectnessRandomValues() {
        InterComputeAPI fast = new FastInterComputeAPIImpl();
        InterComputeAPI original = new InterComputeAPIImpl();

        for (int n : new int[]{10, 50, 100, 99991, 50000}) {
            assertEquals(
                original.processRequest(new InterRequest(n)),
                fast.processRequest(new InterRequest(n)),
                "Fast version must match original for n=" + n
            );
        }
    }
}
