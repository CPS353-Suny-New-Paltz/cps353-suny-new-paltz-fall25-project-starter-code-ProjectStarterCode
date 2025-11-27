package conceptual;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestComputeEngine {

	@Test
	void testPerformComputationWithMock() {
		ComputeRequest mockRequest = mock(ComputeRequest.class);
		when(mockRequest.getInput()).thenReturn(5);

		ComputeEngineAPI engine = new ComputeEngineImpl();
		ComputeResult result = engine.performComputation(mockRequest);

		assertNotNull(result);
		assertEquals(3, result.getOutput());
	}
	
	@Test
	void testPrimeComputationEdgeCases() {
		ComputeEngineAPI engine = new ComputeEngineImpl();
		
		ComputeRequest req1 = new ComputeRequestImpl(10);
		assertEquals(7, engine.performComputation(req1).getOutput());
		
		ComputeRequest req2 = new ComputeRequestImpl(3);
		assertEquals(2, engine.performComputation(req2).getOutput());
		
		ComputeRequest req3 = new ComputeRequestImpl(2);
	    assertEquals(-1, engine.performComputation(req3).getOutput());
	}

}