package network.api;

import shared.stuff.ApiStatus;

/**
 * Basic outline of a user profile caller gets after making a LoadProfileRequest
 */
public final class LoadProfileResponse {
  private final String userId;
  private final String displayName;
  private final ApiStatus status;

  public LoadProfileResponse(String userId, String displayName,
      ApiStatus status) {
    this.userId = userId;
    this.displayName = displayName;
    this.status = status;
  }

  public String getUserId() {
    return userId;
  }
  public String getDisplayName() {
    return displayName;
  }
  public ApiStatus getStatus() {
    return status;
  }
}
