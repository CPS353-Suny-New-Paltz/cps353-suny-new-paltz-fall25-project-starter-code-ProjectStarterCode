package apiImplementations;

import java.util.ArrayList;

import process.api.DataBatch;
import process.api.LoadRequest;
import process.api.LoadResponse;
import process.api.ProcessApi;
import process.api.StoreRequest;
import process.api.StoreResponse;
import shared.stuff.ApiStatus;
import shared.stuff.Resource;

/**
 * Implementation of the ProcesskApi interface
 */
public class ProcessAPI implements ProcessApi {

  // need to know which resource for storing and loading data
  private final Resource resource;

  private final DataBatch buffer; // A buffer if needed

  public ProcessAPI(Resource resource) {
    this.resource = resource;

    // example buffer
    this.buffer = new DataBatch<ArrayList<Integer>>(new ArrayList<Integer>());
  }

  @Override
  public LoadResponse load(LoadRequest request) {
    // empty implementation returning failure and no data
    return new LoadResponse(ApiStatus.ERROR, buffer, null);
  }

  @Override
  public StoreResponse store(StoreRequest request) {
    return new StoreResponse(ApiStatus.ERROR);
  }

}
