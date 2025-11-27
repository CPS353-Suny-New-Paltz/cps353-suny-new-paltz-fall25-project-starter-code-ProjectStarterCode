package network;

import org.junit.jupiter.api.Test;

import conceptual.ComputeEngineAPI;
import conceptual.ComputeRequest;
import conceptual.ComputeResult;
import process.DataValueImpl;
import process.StorageComputeAPI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestUserCompute {

	@Test
	void testSubmitJobWithMock() {
	    StorageComputeAPI mockStorage = mock(StorageComputeAPI.class);
	    ComputeEngineAPI mockEngine = mock(ComputeEngineAPI.class);
	    JobRequest mockRequest = mock(JobRequest.class);

	    when(mockRequest.getInputSource()).thenReturn("input.txt");
	    when(mockRequest.getOutputDestination()).thenReturn("output.txt");

	    when(mockStorage.readAllInputs("input.txt"))
	        .thenReturn(() -> java.util.List.of(5));

	    when(mockEngine.performComputation(any(ComputeRequest.class)))
	        .thenReturn(() -> 3);

	    when(mockStorage.writeAllOutputs(eq("output.txt"), any())).thenReturn(true);

	    UserComputeAPI api = new UserComputeImpl(mockEngine, mockStorage);
	    JobResponse response = api.submitJob(mockRequest);

	    assertNotNull(response);
	    assertTrue(response.isSuccess());
	    assertEquals("Job completed successfully", response.getMessage());
	}


}