package conceptual;

import project.annotations.ConceptualAPI;

@ConceptualAPI
public class ComputeEngineImpl implements ComputeEngineAPI {

    // Turn on when testing performance, off otherwise
    private static final boolean DEBUG_TIMING = false;

    @Override
    public ComputeResult performComputation(ComputeRequest request) {

        long totalStart = System.nanoTime();
        long loopTime = 0;
        long isPrimeTime = 0;
        int isPrimeCalls = 0;

        if (request == null) {
            return new ComputeResultImpl(-1);
        }

        try {
            int n = request.getInput();
            if (n <= 2) {
                return new ComputeResultImpl(-1);
            }

            long loopStart = System.nanoTime();

            for (int candidate = n - 1; candidate >= 2; candidate--) {

                long ipStart = System.nanoTime();
                boolean prime = isPrime(candidate);
                long ipEnd = System.nanoTime();

                isPrimeTime += (ipEnd - ipStart);
                isPrimeCalls++;

                if (prime) {
                    loopTime = System.nanoTime() - loopStart;
                    printTiming(totalStart, loopTime, isPrimeTime, isPrimeCalls);
                    return new ComputeResultImpl(candidate);
                }
            }

            loopTime = System.nanoTime() - loopStart;
            printTiming(totalStart, loopTime, isPrimeTime, isPrimeCalls);

            return new ComputeResultImpl(-1);

        } catch (Exception e) {
            return new ComputeResultImpl(-1);
        }
    }

    private void printTiming(long totalStart, long loopTime, long isPrimeTime, int isPrimeCalls) {
        if (!DEBUG_TIMING) {
        	return; // do nothing unless debugging
        }

        long totalTime = System.nanoTime() - totalStart;

        System.out.println("=== ComputeEngineImpl TIMING ===");
        System.out.printf("Total performComputation time:   %.3f ms%n", totalTime / 1_000_000.0);
        System.out.printf("Loop time:                       %.3f ms%n", loopTime / 1_000_000.0);
        System.out.printf("isPrime() total time:            %.3f ms%n", isPrimeTime / 1_000_000.0);
        System.out.println("isPrime() calls:                 " + isPrimeCalls);
        System.out.println("=================================");
    }

    private boolean isPrime(int x) {
        if (x < 2) {
        	return false;
        }
        if (x % 2 == 0) {
        	return x == 2;
        }

        int r = (int) Math.sqrt(x);
        for (int i = 3; i <= r; i += 2) {
            if (x % i == 0) {
            	return false;
            }
        }
        return true;
    }
}
