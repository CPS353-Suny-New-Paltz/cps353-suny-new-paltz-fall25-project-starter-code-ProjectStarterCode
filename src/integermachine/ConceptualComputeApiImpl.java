package integermachine;

public class ConceptualComputeApiImpl implements ConceptualComputeApi {

    @Override
    public ComputeResult compute(int n) {
        int largestPrime = 1;

        for (int i = n - 1; i >= 2; i--) {
            if (isPrime(i)) {
                largestPrime = i;
                break;
            }
        }

        return new ComputeResult(n, largestPrime);
    }

    private boolean isPrime(int x) {
        if (x < 2) {
            return false;
        }
        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }
}
