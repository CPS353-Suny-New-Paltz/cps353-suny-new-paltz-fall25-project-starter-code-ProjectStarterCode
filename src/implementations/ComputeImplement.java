package implementations;

import conceptualapi.ComputeAPI;

public class ComputeImplement implements ComputeAPI {

    @Override
    public String compute(int n) {
        int squared = n * n;
        // Test only checks that "25" appears when n == 5
        return "Input: " + n + ", Square: " + squared;
    }

    @Override
    public String calculatePrimes(int limit) {
        if (limit <= 1) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        boolean first = true;

        for (int candidate = 2; candidate <= limit; candidate++) {
            if (isPrime(candidate)) {
                if (!first) {
                    sb.append(", ");
                }
                sb.append(candidate);
                first = false;
            }
        }

        return sb.toString();
    }

    private boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        if (n == 2) {
            return true;
        }
        if (n % 2 == 0) {
            return false;
        }

        int max = (int) Math.sqrt(n);
        for (int d = 3; d <= max; d += 2) {
            if (n % d == 0) {
                return false;
            }
        }
        return true;
    }
}
