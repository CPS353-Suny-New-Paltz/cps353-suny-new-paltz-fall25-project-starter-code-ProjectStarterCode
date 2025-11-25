package apiengine;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import apiimplementation.NetworkAPIImpl;

public class TestNetworkAPIValidation {

    @Test
    public void constructorNullArguments_shouldThrow() {
        // minimal conceptual stub
        ConceptualAPI conceptual = new ConceptualAPI() {
            @Override
            public apinetwork.ComputationOutput compute(apinetwork.ComputationInput input) {
                return new apinetwork.ComputationOutput("0");
            }
        };

        // store null should throw
        assertThrows(IllegalArgumentException.class, () -> new NetworkAPIImpl(null, conceptual));

        // conceptual null should throw
        assertThrows(IllegalArgumentException.class, () -> new NetworkAPIImpl(new apiimplementation.ProcessAPIImpl(), null));
    }
}
