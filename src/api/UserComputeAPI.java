package api;

@NetworkAPI
public interface UserComputeAPI {
    String computeFactorial(String inputSource, String outputDestination, String delimiter);
}