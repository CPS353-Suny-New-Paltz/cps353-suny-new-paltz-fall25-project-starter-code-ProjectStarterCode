package project.performance;

import project.intercompute.InterComputeAPI;
import project.intercompute.InterComputeAPIImpl;
import project.intercompute.InterRequest;

public class ComputeEngineProfiler {

    public static void main(String[] args) {

        InterComputeAPI engine = new InterComputeAPIImpl();

        // test inputs to find where performance slows down
        int[] testInputs = {1000, 5000, 10000, 50000, 100000};

        for (int n : testInputs) {

            InterRequest req = new InterRequest(n);

            long start = System.nanoTime();
            int result = engine.processRequest(req);
            long end = System.nanoTime();

            System.out.println(
                "largest prime ≤ " + n + " = " + result +
                " | time = " + ((end - start) / 1_000_000.0) + " ms"
            );
        }
    }
}
