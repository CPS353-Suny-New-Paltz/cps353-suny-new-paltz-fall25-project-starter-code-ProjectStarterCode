package apiimplementation;

import apiengine.ConceptualAPI;
import apinetwork.ComputationInput;
import apinetwork.ComputationOutput;
import apistorage.ProcessAPI;

import java.util.ArrayList;
import java.util.List;

public class ConceptualAPIImpl implements ConceptualAPI {
	private final ProcessAPI processAPI;

	public ConceptualAPIImpl(ProcessAPI processAPI) {
		this.processAPI = processAPI;
	}

	@Override
	public ComputationOutput compute(ComputationInput input) {
		testingHook(); // allows tests to inject behavior

		if (input == null) {
			processAPI.writeOutput("null");
			return new ComputationOutput("null");
		}

		int n = input.getInputNumber();
		// Use delimiters when formatting output if provided (fallback to default)
		String pairDelim = " × ";
		if (input.getDelimiters() != null && input.getDelimiters().getPairDelimiter() != null) {
			pairDelim = input.getDelimiters().getPairDelimiter();
		}

		if (n <= 1) {
			String s = String.valueOf(n);
			processAPI.writeOutput(s);
			return new ComputationOutput(s);
		}

		List<Integer> factors = primeFactors(n);
		String result = joinFactors(factors, pairDelim);

		processAPI.writeOutput(result);
		return new ComputationOutput(result);
	}

	// Standard prime factorization algorithm
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

	private String joinFactors(List<Integer> factors, String pairDelimiter) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < factors.size(); i++) {
			if (i > 0) {
				sb.append(pairDelimiter);
			}
			sb.append(factors.get(i));
		}
		return sb.toString();
	}

	protected void testingHook() {
		// no-op
	}
}
