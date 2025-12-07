package conceptual;

/**
 * How the bottleneck was diagnosed:
 * - Instrumented performComputation calls with System.nanoTime() in order to
 * 	measure how long each major part of the method takes.
 * 
 * - It was found that most CPU consumption stems from repeated primality checks
 * 	and repeated looping.
 * 
 * - Fix: 
 * 	1) Precompute primes using a Sieve of Eratosthenes for batch-style workloads
 * 	2) Use a Miller-Rabin test for 32-bit ints where sieving would be too large, or for
 * 		single requests. 
 * 			- eliminate repeated primality tests
 * 			- eliminate repeated looping
 * 			- cache primes
 * 
 */

public class FastComputeEngineImpl implements ComputeEngineAPI {
	private static final int SIEVE_THRESHOLD = 100_000;

	@Override
	public ComputeResult performComputation(ComputeRequest request) {
		if (request == null) {
			return new ComputeResultImpl(-1);
		}
		int n = request.getInput();
		if (n <= 2) {
			return new ComputeResultImpl(-1);
		}
		
		// use sieve cache for small values of n
		if (n <= SIEVE_THRESHOLD) {
			SieveCache.ensureSieveSize(n);
			int prev = SieveCache.previousPrimeBelow(n);
			return new ComputeResultImpl(prev);
		}
		
		// use Miller-Rabin primality test for larger inputs
		for (int candidate = n - 1;candidate >= 2; candidate--) {
			if (millerRabinIsPrime(candidate)) {
				return new ComputeResultImpl(candidate);
			}
		}
		return new ComputeResultImpl(-1);
	}

	private boolean millerRabinIsPrime(int n) {
		if (n < 2) {
			return false;
		}
		int[] smallPrimes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31};
		for (int p : smallPrimes) {
			if (n == p) {
				return true;
			}
			if (n % p == 0) {
				return false;
			}
		}
		int d = n - 1;
		int s = 0;
		while ((d & 1) == 0) {
			d >>= 1;
			s++;
		}
		
		int[] bases = {2,7,61};
		for (int a : bases) {
			if (a % n == 0) {
				continue;
			}
			long x = modPow(a, d, n);
			if (x == 1 || x == n - 1) {
				continue;			
			}
			boolean cont = false;
			for (int r = 1; r < s; r++) {
				x = (x * x) % n;
				if (x == n - 1) {
					cont = true;
					break;
				}
			}
			if (!cont) {
				return false;
			}
		}
		return true;
	}

	private long modPow(long base, long exp, long mod) {
		long result = 1;
		base %= mod;
		while (exp > 0) {
			if ((exp & 1) == 1) {
				result = (result * base) % mod;
			}
			base = (base * base) % mod;
			exp >>= 1;
		}
		return result;
	}
}
