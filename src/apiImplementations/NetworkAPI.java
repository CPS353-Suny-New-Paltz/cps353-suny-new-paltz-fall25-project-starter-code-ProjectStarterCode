package apiImplementations;

import network.api.Delimiter;
import network.api.LoadDataRequest;
import network.api.LoadDataResponse;
import network.api.LoadProfileRequest;
import network.api.LoadProfileResponse;
import network.api.LoginRequest;
import network.api.LoginResponse;
import network.api.LogoutRequest;
import network.api.LogoutResponse;
import network.api.NetworkApi;
import network.api.StoreDataRequest;
import network.api.StoreDataResponse;
import shared.stuff.ApiStatus;

/**
 * Implementation of the NetworkApi interface
 */
public class NetworkAPI implements NetworkApi {

  // will need to communicate with the ProcessAPI to pass instructions to the
  // Data Storage System
  private ProcessAPI readWrite;

  // Will also need to talk to the computation section to perform calculations,
  // get session keys, etc
  private ConceptualAPI compute;

  // default delimiter if user does not provide one
  private Delimiter defaultDelimiter = Delimiter.COMMA;

  @Override
  public LoginResponse login(LoginRequest req) {
    // empty constructor
    return new LoginResponse(null, null, ApiStatus.ERROR);
  }

  @Override
  public LogoutResponse logout(LogoutRequest req) {
    return new LogoutResponse(ApiStatus.ERROR);
  }

  @Override
  public LoadProfileResponse loadProfile(LoadProfileRequest req) {
    return new LoadProfileResponse(null, null, ApiStatus.ERROR);
  }

  @Override
  public StoreDataResponse storeData(StoreDataRequest req) {
    return new StoreDataResponse(ApiStatus.ERROR, null, null);
  }

  @Override
  public LoadDataResponse loadData(LoadDataRequest req) {
    return new LoadDataResponse(ApiStatus.ERROR, null, null, null);
  }

}
