package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import apiImplementations.ConceptualAPI;
import apiImplementations.NetworkAPI;
import apiImplementations.ProcessAPI;
import network.api.LoadDataRequest;
import network.api.LoadDataResponse;
import network.api.LoadProfileRequest;
import network.api.LoadProfileResponse;
import network.api.LoginRequest;
import network.api.LoginResponse;
import network.api.LogoutRequest;
import network.api.LogoutResponse;
import network.api.StoreDataRequest;
import network.api.StoreDataResponse;
import process.api.LoadRequest;
import process.api.LoadResponse;
import process.api.StoreRequest;
import process.api.StoreResponse;
import shared.stuff.ApiStatus;
import shared.stuff.DataBatch;
import shared.stuff.Resource;
import shared.stuff.ResourceType;

public class TestNetworkAPI {
  private NetworkAPI networkApi;

  @Mock
  private ProcessAPI mockProcess;

  @Mock
  private ConceptualAPI mockCompute;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this); // initialize mocks
    networkApi = new NetworkAPI();

    // initialize other API fields
    networkApi.setReadWrite(mockProcess);
    networkApi.setCompute(mockCompute);

    Resource resource = new Resource(ResourceType.DATABASE, "db://mydb");
    networkApi.setResource(resource);
  }
  @Test
  public void testDependency() {
    assertSame(mockProcess, networkApi.getReadWrite());
    assertSame(mockCompute, networkApi.getCompute());
  }

  @Test
  public void testLogin() {
    LoginRequest req = new LoginRequest("testUser", "hashedPassword");
    LoginResponse resp = networkApi.login(req);

    assertNotNull(resp);
    assertNotNull(resp.getUserId());
    assertNotNull(resp.getSessionToken());
    assertEquals(ApiStatus.ERROR, resp.getStatus());
  }

  @Test
  public void testLogout() {
    LogoutRequest req = new LogoutRequest(UUID.randomUUID().toString());
    LogoutResponse resp = networkApi.logout(req);

    assertNotNull(resp);
    assertEquals(false, resp.success());
    assertEquals(ApiStatus.ERROR, resp.getStatus());
  }

  @Test
  public void testLoadProfile() {
    LoadProfileRequest req = new LoadProfileRequest(
        UUID.randomUUID().toString());
    assertNotNull(req.getSessionToken());

    LoadProfileResponse resp = networkApi.loadProfile(req);

    assertNotNull(resp);
    assertNotNull(resp.getUserId());
    assertEquals("testUser", resp.getDisplayName());
    assertEquals(ApiStatus.ERROR, resp.getStatus());
  }

  @Test
  void testLoadProfileWithNull() {
    // expect NullPointerException when passing null
    assertThrows(NullPointerException.class, () -> {
      LoadProfileRequest req = new LoadProfileRequest(null);

    });
  }

  @Test
  public void testStoreData() {
    StoreDataRequest req = new StoreDataRequest(UUID.randomUUID().toString(),
        networkApi.getResource(), "test".getBytes());

    assertEquals(ResourceType.DATABASE, req.getDestination().getType());

    StoreDataResponse resp = networkApi.storeData(req);

    assertNotNull(resp);
    assertEquals(resp.getLocation().getUri(), req.getDestination().getUri());
    assertEquals("Not Implemented", resp.getErrorMessage());
    assertEquals(ApiStatus.ERROR, resp.getStatus());
  }

  @Test
  public void testLoadData() {
    LoadDataRequest req = new LoadDataRequest(UUID.randomUUID().toString(),
        networkApi.getResource());

    LoadDataResponse resp = networkApi.loadData(req);

    assertNotNull(resp);
    assertEquals(false, resp.success());
    assertEquals(ApiStatus.ERROR, resp.getStatus());
  }

  @Test
  void testStoreDataDelegatesToProcessApi() {
    ProcessAPI mockProcess = networkApi.getReadWrite();
    StoreRequest req = new StoreRequest(networkApi.getResource(),
        new DataBatch<>("testData".getBytes()));
    StoreResponse resp = mockProcess.store(req);

    when(mockProcess.store(req)).thenReturn(resp);

    NetworkAPI networkApi = new NetworkAPI();
    networkApi.setReadWrite(mockProcess);

    // We will eventually implement a feature that translates the users
    // StoreDataRequest into the ProcessAPI StoreRequest format and how the
    // networkAPI translates the Process API StoreResponse into a
    // StoreDataResponse for the user
    StoreDataRequest networkReq = new StoreDataRequest(
        UUID.randomUUID().toString(), networkApi.getResource(),
        "test".getBytes());
    StoreDataResponse networkResp = networkApi.storeData(networkReq);

    // this will fail now
    assertEquals(ApiStatus.SUCCESS, resp.getStatus());
  }

  @Test
  void testLoadDataDelegatesToProcessApi() {
    // this method essentially demonstrates the same thing as the prior method,
    // just for LoadData

    ProcessAPI mockProcess = networkApi.getReadWrite();

    LoadRequest req = new LoadRequest(networkApi.getResource());
    LoadResponse resp = mockProcess.load(req);

    when(mockProcess.load(req)).thenReturn(resp);

    // User request
    LoadDataRequest userReq = new LoadDataRequest(UUID.randomUUID().toString(),
        networkApi.getResource());

    // Response sent to the user
    LoadDataResponse userResp = networkApi.loadData(userReq);

    // this will fail, not implmented yet
    assertEquals(ApiStatus.SUCCESS, userResp.getStatus());

  }

}
