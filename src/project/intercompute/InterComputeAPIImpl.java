package project.intercompute;

import java.util.List;

import project.datacompute.DataComputeAPI;
import project.datacompute.DataRequest;

public class InterComputeAPIImpl implements InterComputeAPI {

    private final DataComputeAPI data;

    public InterComputeAPIImpl(DataComputeAPI data) {
        this.data = data;
    }

    @Override
    public void processRequest(InterRequest req) {
        // Convert raw bytes → integer
        byte[] bytes = req.getBytes();   // you’ll need a getter in InterRequest
        int n = bytes[0];                // assuming 1 integer for now

        // Compute largest prime ≤ n
        int largestPrime = LargestPrime.largestPrimeLeq(n);

        // Store result in data layer
        String result = String.valueOf(largestPrime);
        data.insertRequest(new DataRequest(result.getBytes()));
    }
    
    @Override
    public List<String> computeAll(List<Integer> ns) {
        List<String> out = new java.util.ArrayList<>();
        if (ns == null) {
            return out;
        }
        for (Integer n : ns) {
            if (n == null || n < 2) {
                out.add("none");
                continue;
            }
            int p = LargestPrime.largestPrimeLeq(n);
            out.add(String.valueOf(p));
        }
        return out;
    }
}