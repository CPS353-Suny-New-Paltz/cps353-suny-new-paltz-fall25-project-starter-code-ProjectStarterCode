package conceptual;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class SieveCache {
	private static volatile boolean[] isPrime = new boolean[0];
	private static volatile int[] prevPrime = new int[0];
	private static final AtomicInteger currentMax = new AtomicInteger(1);
	private static final ReentrantLock buildLock = new ReentrantLock();

	public static void ensureSieveSize(int max) {
		if (max <= currentMax.get()) {
			return;
		}
		buildLock.lock();
		try {
			if (max <= currentMax.get()) {
				return;
			}
			int newMax = Math.max(max, currentMax.get() * 2);
			buildSieve(newMax);
			currentMax.set(newMax);
		} finally {
			buildLock.unlock();
		}
	}

	private static void buildSieve(int max) {
		boolean[] localIsPrime = new boolean[max + 1];
		for (int i = 2; i <= max; i++) {
			localIsPrime[i] = true;
		}
		int r = (int)Math.sqrt(max);
		for (int p = 2; p <= r; p++) {
			if (localIsPrime[p]) {
				for (int mult = p* p; mult <= max; mult += p) {
					localIsPrime[mult] = false;
				}
			}
		}
		int[] localPrev = new int [max + 1];
		int last = -1;
		for (int i = 0; i <= max; i++) {
			if (localIsPrime[i]) {
				last = i;
			}
			localPrev[i] = last;
		}
		isPrime = localIsPrime;
		prevPrime = localPrev;
	}
	
	public static int previousPrimeBelow(int n) {
		if (n <= 2) {
			return -1;
		}
		int idx = n - 1;
		if(idx >= prevPrime.length) {
			idx = prevPrime.length -1;
		}
		int v = prevPrime[idx];
		return (v >= 2) ? v : -1;
	}
	
	public static int getCachedSize() {
		return currentMax.get();
	}
}
