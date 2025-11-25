package apiimplementation;

import apiengine.ConceptualAPI;
import apinetwork.ComputationInput;
import apinetwork.ComputationOutput;
import apinetwork.Delimiters;

import java.util.ArrayList;
import java.util.List;

/**
 * Conceptual compute engine (prime factorization).
 * - Validates inputs (null preserved, negative -> error sentinel).
 * - Catches unexpected exceptions and returns ComputationOutput with "error:..." result.
 */
public class ConceptualAPIImpl implements ConceptualAPI {

    public ConceptualAPIImpl() {
    }

    @Override
    public ComputationOutput compute(ComputationInput input) {
        try {
            // Validation: keep existing behavior for null input (tests expect "null").
            if (input == null) {
                return new ComputationOutput("null");
            }

            // Validate numeric input: negative values are considered invalid for this API.
            int n = input.getInputNumber();
            if (n < 0) {
                return new ComputationOutput("error:negative-input");
            }

            // Determine pair delimiter (Delimiters null-check)
            String pairDelim = " × ";
            Delimiters d = input.getDelimiters();
            if (d != null && d.getPairDelimiter() != null) {
                pairDelim = d.getPairDelimiter();
            }

            // Base cases
            if (n <= 1) {
                return new ComputationOutput(String.valueOf(n));
            }

            List<Integer> factors = primeFactors(n);
            return new ComputationOutput(joinFactors(factors, pairDelim));
        } catch (Exception ex) {
            String msg = ex.getMessage() == null ? "unknown" : ex.getMessage();
            return new ComputationOutput("error:compute-exception:" + msg);
        }
    }

    // Standard prime factorization
    private List<Integer> primeFactors(int n) {
        List<Integer> factors = new ArrayList<>();
        while (n % 2 == 0) {
            factors.add(2);
            n /= 2;
        }
        int f = 3;
        while (f * f <= n) {
            while (n % f == 0) {
                factors.add(f);
                n /= f;
            }
            f += 2;
        }
        if (n > 1) {
            factors.add(n);
        }
        return factors;
    }

    private String joinFactors(List<Integer> factors, String delim) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < factors.size(); i++) {
            if (i > 0) {
                sb.append(delim);
            }
            sb.append(factors.get(i));
        }
        return sb.toString();
    }
}

