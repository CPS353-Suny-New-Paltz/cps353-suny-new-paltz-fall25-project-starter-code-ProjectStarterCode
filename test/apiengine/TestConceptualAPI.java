package apiengine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import apiimplementation.ConceptualAPIImpl;
import apinetwork.ComputationInput;
import apinetwork.ComputationOutput;
import apinetwork.Delimiters;

public class TestConceptualAPI {

    @Test
    public void compute_shouldReturnCorrectFactorization() {
        ConceptualAPIImpl engine = new ConceptualAPIImpl();
        ComputationInput input = new ComputationInput(10, new Delimiters(":", " × "));
        ComputationOutput out = engine.compute(input);

        assertNotNull(out, "ComputationOutput should not be null");
        assertNotNull(out.getResult(), "ComputationOutput result should not be null");
        assertEquals("2 × 5", out.getResult(), "10 should factor to \"2 × 5\"");
    }

    @Test
    public void compute_nullInput_returnsNullString() {
        ConceptualAPIImpl engine = new ConceptualAPIImpl();
        ComputationOutput out = engine.compute(null);

        assertNotNull(out, "ComputationOutput should not be null for null input");
        assertEquals("null", out.getResult(), "Null input should produce \"null\" result");
    }
}
