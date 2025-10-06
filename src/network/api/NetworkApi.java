package network.api;

import project.annotations.NetworkAPI;

/**
 * API between the user and the Job I/O portion of the compute engine
 * Responsible for logging the user in/out, viewing their profile, and
 * facilitating the reading and writing of data
 */
@NetworkAPI
public interface NetworkApi {

  /**
   * Handles user authentication
   * 
   * @param LoginRequest
   * @return LoginResponse
   */
  LoginResponse login(LoginRequest req);

  /**
   * Handles logging users out
   * 
   * @param LogoutRequest
   * @return LogoutResponse
   */
  LogoutResponse logout(LogoutRequest req);

  /**
   * Kicks off a computation
   * 
   * @param request
   *          - the user request for the computation
   * @return a response containing the computation output
   */
  public ComputationResponse compute(ComputationRequest request);
}
