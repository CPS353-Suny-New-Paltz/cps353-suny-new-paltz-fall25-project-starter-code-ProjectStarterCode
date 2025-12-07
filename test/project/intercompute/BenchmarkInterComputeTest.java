package project.intercompute;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BenchmarkInterComputeTest {

    // Benchmark utility
	private long benchmark(InterComputeAPI engine, int n) {
	    long start = System.nanoTime();

	    // Run the computation MANY times to simulate real load
	    for (int i = 0; i < 500; i++) {
	        engine.processRequest(new InterRequest(n));
	    }

	    long end = System.nanoTime();
	    return end - start;
	}


	@Test
	void fastVersionIsAtLeast10PercentFaster() {
	    InterComputeAPI original = new InterComputeAPIImpl();
	    InterComputeAPI fast = new FastInterComputeAPIImpl();

	    int testInput = 100_000;

	    long originalMin = Long.MAX_VALUE;
	    long fastMin = Long.MAX_VALUE;

	    for (int i = 0; i < 10; i++) {
	        originalMin = Math.min(originalMin, benchmark(original, testInput));
	        fastMin = Math.min(fastMin, benchmark(fast, testInput));
	    }

	    System.out.println("Original BEST ns: " + originalMin);
	    System.out.println("Fast BEST ns: " + fastMin);

	    assertTrue(
	        fastMin <= originalMin * 0.90,
	        "Fast version must be >=10% faster.\nOriginal: "
	            + originalMin + " ns\nFast: " + fastMin + " ns"
	    );
	}

}
