package project.networkapi;

public class OutputSource {
    private final String destination;

    public OutputSource(String destination) {
        this.destination = destination;
    }

    public String getSourceName() {
        return destination;
    }
}