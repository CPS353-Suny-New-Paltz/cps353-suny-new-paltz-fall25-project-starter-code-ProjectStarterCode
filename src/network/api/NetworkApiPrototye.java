package network.api;

import project.annotations.NetworkAPIPrototype;
import shared.stuff.Resource;
import shared.stuff.ResourceType;

/**
 * This class is the prototype for the functionality of our NetworkAPI
 */
public class NetworkApiPrototye {

  /**
   * This prototype method showcases how the network API is expected to be used
   * 
   * @param api
   */
  @NetworkAPIPrototype
  public void prototype(NetworkApi api) {

    // Login
    LoginResponse loginResp = api
        .login(new LoginRequest("alice", "hashedPassword123"));
    if (!loginResp.success()) {
      return;
    }

    String sessionToken = loginResp.getSessionToken();

    Resource inResource = new Resource(ResourceType.FILE, "file://file.txt");
    Resource outResource = new Resource(ResourceType.FILE, "file://file.txt");

    ComputationRequest compReq = new ComputationRequest(inResource, outResource,
        Delimiter.defaultDelimiter());

    // Logout
    LogoutResponse logoutResp = api.logout(new LogoutRequest(sessionToken));
    System.out.println("Logout success: " + logoutResp.success());
  }
}
