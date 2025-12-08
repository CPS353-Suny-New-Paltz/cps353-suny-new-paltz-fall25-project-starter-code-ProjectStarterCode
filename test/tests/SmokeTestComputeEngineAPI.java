package tests;

import api.CalcWriteResponse;
import api.CompRequest;
import api.CompResponse;
import api.ComputerAPI;
import implementations.ComputeEngineImpl;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SmokeTestComputeEngineAPI {

	@Test
	public void compRequestTest() {
		// create implementation
		ComputerAPI compEngine = new ComputeEngineImpl();

		//Create CalcReadRequest Object
		CompRequest request  = Mockito.mock(CompRequest.class);

		CompResponse response = new CompResponse(request);

		assertNotNull(response, "Can Calculate");
	}

	@Test
	public void calcResponseTest() {
		// create implementation
		ComputerAPI compEngine = new ComputeEngineImpl();
		CompRequest request  = Mockito.mock(CompRequest.class);
		CompResponse response = Mockito.mock(CompResponse.class);

		CalcWriteResponse calculated = compEngine.write(response, request);

		assertNotNull(calculated, "big numbers");
	}
}
