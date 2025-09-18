package api;

import project.annotations.NetworkAPI;

@NetworkAPI
public interface UserComputeAPI {
    String computeFactorial(String inputSource, String outputDestination, String delimiter);
}