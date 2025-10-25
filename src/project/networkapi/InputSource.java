package project.networkapi;

public class InputSource {
    private final String type;
    private final String location;
    public InputSource(String type, String location) {
        // And store the values
        this.type = type;
        this.location = location;
    }
    public String getSourceName() {
        return location;
    }
    public String getType() {
        return type;
    }
}