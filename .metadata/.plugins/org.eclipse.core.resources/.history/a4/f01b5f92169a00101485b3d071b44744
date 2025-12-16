package project.annotations.api;
import project.annotations.NetworkAPIPrototype;

public class UserComputeAPIPrototype implements UserComputeAPI {

    private String inputSource;
    private String outputDestination;
    private String delimiters;

    @Override
    @NetworkAPIPrototype
    public void configureInputSource(String source) {
        this.inputSource = (source == null ? "" : source.trim());
        System.out.println("[Prototype] Input source set to: " + this.inputSource);
    }

    @Override
    public void configureOutputDestination(String destination) {
        this.outputDestination = (destination == null ? "" : destination.trim());
        System.out.println("[Prototype] Output destination set to: " + this.outputDestination);
    }

    @Override
    public void setDelimiters(String delimiters) {
        if (delimiters == null || delimiters.isBlank()) {
            this.delimiters = ",";
            System.out.println("[Prototype] No delimiters provided; using default: \",\"");
        } else {
            this.delimiters = delimiters;
            System.out.println("[Prototype] Delimiters set to: " + this.delimiters);
        }
    }
}