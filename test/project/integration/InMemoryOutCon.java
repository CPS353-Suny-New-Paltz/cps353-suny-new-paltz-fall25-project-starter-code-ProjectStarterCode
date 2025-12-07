package project.integration;

import java.util.ArrayList;
import java.util.List;

public class InMemoryOutCon {

    private final List<Integer> out;

    public InMemoryOutCon(List<Integer> out) {
        this.out = out;
    }

    public InMemoryOutCon() {
        this.out = new ArrayList<>();
    }

    public void write(Integer n) {
        out.add(n);
    }

    public List<Integer> getInts() {
        return out;
    }
}
