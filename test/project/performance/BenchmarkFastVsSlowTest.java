package project.performance;

import org.junit.jupiter.api.Test;

import project.datacompute.DataComputeAPIImpl;
import project.intercompute.InterComputeAPIImpl;
import project.intercompute.FastInterComputeAPIImpl;
import project.usercompute.ComputeRequest;
import project.usercompute.ComputeResponse;
import project.usercompute.UserComputeAPI;
import project.usercompute.UserComputeAPIImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BenchmarkFastVsSlowTest {

    @Test
    void benchmarkFastVsSlow() {

        DataComputeAPIImpl data = new DataComputeAPIImpl();

        UserComputeAPI slow = new UserComputeAPIImpl(
                new InterComputeAPIImpl(),
                data
        );

        UserComputeAPI fast = new UserComputeAPIImpl(
                new FastInterComputeAPIImpl(),
                data
        );

        List<Integer> numbers = List.of(
                10_000_000,
                12_000_000,
                15_000_000
        );

        ComputeRequest req = new ComputeRequest(numbers, null);

        int warmupRuns = 5;
        int timedRuns = 20;
        int innerLoops = 5000;

        long totalFast = 0;
        long totalSlow = 0;

        for (int i = 0; i < warmupRuns; i++) {
            slow.compute(req);
            fast.compute(req);
        }

        for (int i = 0; i < timedRuns; i++) {

            long startSlow = System.nanoTime();
            for (int k = 0; k < innerLoops; k++) {
                slow.compute(req);
            }
            long endSlow = System.nanoTime();
            totalSlow += (endSlow - startSlow);

            long startFast = System.nanoTime();
            for (int k = 0; k < innerLoops; k++) {
                fast.compute(req);
            }
            long endFast = System.nanoTime();
            totalFast += (endFast - startFast);
        }

        long avgSlow = (totalSlow / timedRuns);
        long avgFast = (totalFast / timedRuns);

        System.out.println("Average SLOW time (ns) = " + avgSlow);
        System.out.println("Average FAST time (ns) = " + avgFast);

        assertTrue(
                avgFast < avgSlow,
                "Fast should be faster. avgFast=" + avgFast +
                        " ns, avgSlow=" + avgSlow + " ns"
        );
    }
}
