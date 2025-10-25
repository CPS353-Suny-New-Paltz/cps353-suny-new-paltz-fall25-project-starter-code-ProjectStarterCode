import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import project.conceptualapi.ComputeControllerAPI;
import project.conceptualapi.PowerDigitSumController;
import project.conceptualapi.ComputeRequest;
import project.conceptualapi.ComputeResponse;
import project.conceptualapi.ComputeStatus;

public class TestComputeControllerAPI {
    @Test
    public void testPowerDigitSum() {
        // Instantiate the implementation
        ComputeControllerAPI comp = new PowerDigitSumController();
        ComputeRequest req = new ComputeRequest(2, 15); // 2^15 = 32768, sum = 26

        // Call the method on the real object
        ComputeResponse resp = comp.compute(req);

        // Assert the results
        assertEquals("26", resp.getResult());
    }
}