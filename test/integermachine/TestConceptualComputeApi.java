package integermachine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class TestConceptualComputeApi {

    @Test
    void compute_forTen_returnsSeven() {
        ConceptualComputeApi api = new ConceptualComputeApiImpl();

        ComputeResult result = api.compute(10);

        // For CP3, this will fail; this is the desired future behavior.
        assertEquals(10, result.getInput());
        assertEquals(7, result.getLargestPrimeBelow());
    }

    @Test
    void compute_forTwenty_returnsNineteen() {
        ConceptualComputeApi api = new ConceptualComputeApiImpl();

        ComputeResult result = api.compute(20);

        assertEquals(19, result.getLargestPrimeBelow());
    }
}
