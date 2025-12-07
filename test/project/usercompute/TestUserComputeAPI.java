package project.usercompute;

import org.junit.jupiter.api.Test;
import project.datacompute.DataComputeAPI;
import project.intercompute.InterComputeAPI;
import project.intercompute.InterRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import java.util.Collections;
import java.util.List;

public class TestUserComputeAPI {

    private static UserComputeAPI sut(InterComputeAPI inter, DataComputeAPI data) {
        return new UserComputeAPIImpl(inter, data);
    }

    @Test
    void compute_ok_writesOutput() {
     
        InterComputeAPI inter = req -> 23;

        class CapturingData implements DataComputeAPI {
            List<Integer> written;
            String path;

            public void writeOutput(List<Integer> numbers, String outputPath) {
                this.written = numbers;
                this.path = outputPath;
            }

            public List<Integer> readInput(String inputPath) {
                return List.of();
            }
        }

        CapturingData data = new CapturingData();
        UserComputeAPI user = new UserComputeAPIImpl(inter, data);

        ComputeRequest request = new ComputeRequest(List.of(25), "out.csv");
        ComputeResponse resp = user.compute(request);

        assertTrue(resp.isSuccess());
        assertEquals(23, resp.getResult());
        assertEquals(List.of(23), data.written);
        assertEquals("out.csv", data.path);
    }


    @Test
    void compute_badInput_fails() {
      
        InterComputeAPI inter = req -> { 
        	throw new AssertionError("Should not be called"); };

        class FailOnWrite implements DataComputeAPI {
            public void writeOutput(List<Integer> numbers, String path) {
                throw new AssertionError("writeOutput should not be called");
            }

            public List<Integer> readInput(String path) {
                throw new AssertionError("readInput should not be called");
            }
        }

        UserComputeAPI user = new UserComputeAPIImpl(inter, new FailOnWrite());

        ComputeResponse r1 = user.compute(null);
        assertFalse(r1.isSuccess());

    }

    @Test
    void computeOk_doesNotWrite() {
        InterComputeAPI inter = req -> -1;  

        class CapturingData implements DataComputeAPI {
            boolean writeCalled = false;

            public void writeOutput(List<Integer> numbers, String path) {
                writeCalled = true; 
            }

            public List<Integer> readInput(String inputPath) {
                return List.of(); 
            }
        }

        CapturingData data = new CapturingData();
        UserComputeAPI user = new UserComputeAPIImpl(inter, data);

        ComputeRequest req = new ComputeRequest(List.of(1), null);
        ComputeResponse resp = user.compute(req);

        assertTrue(resp.isSuccess());
        assertEquals(-1, resp.getResult());
        assertFalse(data.writeCalled); 
    }

}
