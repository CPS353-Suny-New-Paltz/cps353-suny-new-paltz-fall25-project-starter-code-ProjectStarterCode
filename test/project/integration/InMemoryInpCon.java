package project.integration;

import java.util.List;


public class InMemoryInpCon {

	private final List<Integer> ints;

    public InMemoryInpCon(List<Integer> ints) {
        this.ints = ints;
    }

    public List<Integer> getInts() {
        return ints;
    }
}
