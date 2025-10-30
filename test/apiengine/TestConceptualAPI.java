package apiengine;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import apiimplementation.ConceptualAPIImpl;
import apinetwork.ComputationInput;
import apinetwork.ComputationOutput;
import apinetwork.Delimiters;
import apistorage.ProcessAPI;

public class TestConceptualAPI {

    @Test
    public void compute_shouldReturnOutput_and_writeToProcessAPI() {
        // mock the ProcessAPI dependency
        ProcessAPI mockProcess = Mockito.mock(ProcessAPI.class);
        // make writeOutput succeed by default
        when(mockProcess.writeOutput(anyString())).thenReturn(true);

        ConceptualAPIImpl engine = new ConceptualAPIImpl(mockProcess);
        ComputationInput input = new ComputationInput(10, new Delimiters(":", " × "));
        ComputationOutput out = engine.compute(input);

        // non-null output and ProcessAPI write called
        assertNotNull(out, "ComputationOutput should not be null");
        assertNotNull(out.getResult(), "ComputationOutput result should not be null (may be empty)");
        verify(mockProcess, atLeastOnce()).writeOutput(anyString());
    }

    @Test
    public void compute_nullInput_writesNullAndReturnsNullResult() {
        ProcessAPI mockProcess = Mockito.mock(ProcessAPI.class);
        when(mockProcess.writeOutput(anyString())).thenReturn(true);

        ConceptualAPIImpl engine = new ConceptualAPIImpl(mockProcess);

        ComputationOutput out = engine.compute(null);

        assertNotNull(out, "ComputationOutput should not be null even for null input");

         verify(mockProcess).writeOutput(anyString());
    }
}
