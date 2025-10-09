package project.integration;

import java.util.List;

public class InMemoryOutCon {

    private final List<String> out;

    public InMemoryOutCon(List<String> out) {
        this.out = out;
    }

    public void write(String s) {
        out.add(s);
    }

    public List<String> getOut() {
        return out;
    }
}