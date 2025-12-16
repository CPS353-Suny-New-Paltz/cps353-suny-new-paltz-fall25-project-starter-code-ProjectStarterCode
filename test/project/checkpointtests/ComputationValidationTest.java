package project.checkpointtests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.util.List;
import org.junit.jupiter.api.Test;
import project.checkpoint4.ComputationComponent;

public class ComputationValidationTest {

    @Test
    public void testComputeHandlesInvalidInput() {
        ComputationComponent comp = new ComputationComponent();

        List<Integer> neg = comp.compute(-10);
        assertTrue(neg.isEmpty());

        List<Integer> zero = comp.compute(0);
        assertTrue(zero.isEmpty());

        List<Integer> one = comp.compute(1);
        assertTrue(one.isEmpty());
    }

    @Test
    public void testComputeHandlesValidInput() {
        ComputationComponent comp = new ComputationComponent();

        List<Integer> result = comp.compute(10);
        assertFalse(result.isEmpty());
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
    }
}