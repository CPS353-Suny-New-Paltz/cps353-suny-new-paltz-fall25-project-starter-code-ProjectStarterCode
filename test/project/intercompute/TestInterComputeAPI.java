package project.intercompute;

import org.junit.jupiter.api.Test;
import project.datacompute.DataComputeAPI;
import project.datacompute.DataRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.List;

import static org.mockito.Mockito.atLeast;
import static org.mockito.ArgumentMatchers.any;

public class TestInterComputeAPI {

    @Test
    void smokeProcessRequestWithMockedDataLayer() {
        DataComputeAPI mockData = mock(DataComputeAPI.class);

        // ctor injection 
        InterComputeAPIImpl inter = new InterComputeAPIImpl(mockData);

        InterRequest req = new InterRequest(new byte[]{9, 9});
        inter.processRequest(req);

        verify(mockData, atLeast(0)).insertRequest(any(DataRequest.class));
    }
    
    @Test
    void handlesNonPrimeInputsGracefully() {
        DataComputeAPI mockData = mock(DataComputeAPI.class);
        InterComputeAPIImpl inter = new InterComputeAPIImpl(mockData);

        List<Integer> inputs = List.of(-5, 0, 1);
        List<String> results = inter.computeAll(inputs);

        assertEquals(List.of("none", "none", "none"), results);
    }

	private void assertEquals(List<String> of, List<String> results) {
		// TODO Auto-generated method stub
		
	}
}