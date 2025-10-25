package project.conceptualapi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PowerDigitSumControllerTest {

    @Test
    void testComputeTwoToFifteenDigitSum() {
        PowerDigitSumController controller = new PowerDigitSumController();
        ComputeResponse response = controller.compute(new ComputeRequest(2, 15));
        assertEquals(ComputeStatus.SUCCESS, response.getStatus());
        assertEquals("26", response.getResult());
    }
}
