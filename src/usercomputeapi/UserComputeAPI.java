package usercomputeapi;
import project.annotations.NetworkAPI;
@NetworkAPI
public interface UserComputeAPI {
//	request to compute sum of primes
	ComputeResponse computeSumOfPrimes(ComputeRequest request);
	
}
