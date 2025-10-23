package project.annotations.api;
import project.annotations.NetworkAPI;

@NetworkAPI
public interface UserComputeAPI {
    void configureInputSource(String source);
    void configureOutputDestination(String destination);
    void setDelimiters(String delimiters);
}