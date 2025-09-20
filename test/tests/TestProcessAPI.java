package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import apiImplementations.ProcessAPI;
import process.api.LoadRequest;
import process.api.LoadResponse;
import process.api.StoreRequest;
import process.api.StoreResponse;
import shared.stuff.ApiStatus;
import shared.stuff.DataBatch;
import shared.stuff.Resource;
import shared.stuff.ResourceType;

/**
 * Test suite for our current ProcessAPI implementation
 */
public class TestProcessAPI {

  private ProcessAPI<?> processApi;
  private Resource dummyResource;

  @BeforeEach
  void setup() {
    // create dummy resource for ProcessAPI
    dummyResource = new Resource(ResourceType.DATABASE, "db://mydb");
    processApi = new ProcessAPI<>(dummyResource);
  }

  @Test
  void testLoad() {
    // tests the ProcessAPI.load() method
    LoadRequest request = new LoadRequest(processApi.getResource());

    LoadResponse<?> response = processApi.load(request);

    assertNotNull(response);
    assertEquals(ApiStatus.ERROR, response.getStatus());
    assertEquals("Not Implemented", response.getMessage());
    assertNotNull(response.getData()); // getData() should just return our
                                       // buffer
  }

  @Test
  void testStore() {
    // tsets the ProcessAPI.store() method
    DataBatch<String> batch = new DataBatch<>("test data");
    StoreRequest<DataBatch<String>> request = new StoreRequest(
        processApi.getResource(), batch);

    StoreResponse response = processApi.store(request);

    assertNotNull(response);
    assertEquals(ApiStatus.ERROR, response.getStatus());
    assertEquals("Not Implemented", response.getMessage());
  }

  @Test
  void testProcessApiResource() {

    // test ProcessAPI constructor works properly
    assertNotNull(processApi.getResource());
    assertEquals("db://mydb", dummyResource.getUri());

    // test setResource is working
    processApi
        .setResource(new Resource(ResourceType.FILE, "file://myfile.txt"));
    assertEquals(ResourceType.FILE, processApi.getResource().getType());
  }

}
