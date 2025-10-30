package apistorage;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ProcessAPIWrapper implements ProcessAPI {
	private final ProcessAPI inner;
	private final AtomicInteger failRemaining = new AtomicInteger(0);
	private boolean log = false;

	public ProcessAPIWrapper(ProcessAPI inner) {
		this.inner = inner;
	}

	public void setLog(boolean log) {
		this.log = log;
	}

	public void simulateFailures(int n) {
		failRemaining.set(n);
	}

	@Override
	public List<Integer> readInputs() {
		if (log) {
			System.out.println("[wrapper] readInputs()");
		}
		return inner.readInputs();
	}

	@Override
	public boolean writeOutput(String data) {
		if (log) {
			System.out.println("[wrapper] writeOutput: " + data);
		}
		if (failRemaining.get() > 0) {
			failRemaining.decrementAndGet();
			throw new RuntimeException("Simulated failure (ProcessAPIWrapper)");
		}
		return inner.writeOutput(data);
	}
}
