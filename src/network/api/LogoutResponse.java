package network.api;

import shared.stuff.ApiStatus;

/**
 * Simple logout acknowledgement.
 */
public final class LogoutResponse {

  private final ApiStatus status;

  public LogoutResponse(ApiStatus status) {
    this.status = status;
  }

  public boolean success() {
    return this.status == ApiStatus.SUCCESS;
  }

  public ApiStatus getStatus() {
    return status;
  }
}
