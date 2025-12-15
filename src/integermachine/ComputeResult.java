package integermachine;

public class ComputeResult {
    // input N and the largest prime strictly smaller than N
    private final int input;
    private final int largestPrimeBelow;

    public ComputeResult(int input, int largestPrimeBelow) {
        this.input = input;
        this.largestPrimeBelow = largestPrimeBelow;
    }

    public int getInput() {
        return input;
    }

    public int getLargestPrimeBelow() {
        return largestPrimeBelow;
    }

    public String asText() {
        return Integer.toString(largestPrimeBelow);
    }
}
