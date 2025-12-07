package project.intercompute;

public class InterComputeAPIImpl implements InterComputeAPI {

    public InterComputeAPIImpl() {
        
    }

    @Override
    public int processRequest(InterRequest req) {
        if (req == null) {
            throw new IllegalArgumentException("InterRequest cannot be null.");
        }
        int n = req.getNumber();
        if (n < 2) {
            return -1;
        }
        return LargestPrime.largestPrimeLeq(n);
    }

}
