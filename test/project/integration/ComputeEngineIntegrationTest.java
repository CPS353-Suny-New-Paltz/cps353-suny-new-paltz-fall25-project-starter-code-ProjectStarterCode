package project.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import project.datacompute.InMemoryDataComputeAPI;
import project.integration.InMemoryInpCon;
import project.integration.InMemoryOutCon;
import project.intercompute.InterComputeAPI;
import project.intercompute.InterComputeAPIImpl;
import project.usercompute.ComputeRequest;
import project.usercompute.ComputeResponse;
import project.usercompute.UserComputeAPI;
import project.usercompute.UserComputeAPIImpl;

public class ComputeEngineIntegrationTest {

	@Test
	void testIntegration_endToEnd() {
	    List<Integer> input = Arrays.asList(1, 10, 25);
	    List<Integer> sink  = new ArrayList<>();

	    UserComputeAPI user = new UserComputeAPIImpl(
	        new InterComputeAPIImpl(), // Conceptual API
	        new InMemoryDataComputeAPI( 
	            new InMemoryInpCon(input),
	            new InMemoryOutCon(sink)
	        )
	    );

	    ComputeRequest req = new ComputeRequest(input, "ignored");
	    ComputeResponse resp = user.compute(req);

	    List<Integer> expected = Arrays.asList(
	        -1, 7, 23
	    );
	    assertEquals(expected, sink);
	}

}
