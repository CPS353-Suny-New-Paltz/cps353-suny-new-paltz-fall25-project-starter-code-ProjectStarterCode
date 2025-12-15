package numberlettercountfetching;

import java.math.BigInteger;
import java.util.List;

public class SingleThreadedNetworkAPI implements FetchApi {
	private final FetchApi delegate;

	public SingleThreadedNetworkAPI() {
		this.delegate = createDelegate();
	}

	private FetchApi createDelegate() {
		numberlettercountcomputing.ComputingApi computingApi = new numberlettercountcomputing.ComputingApiImpl();
		numberlettercountdatastoring.DataStoreApi dataStoreApi = new numberlettercountdatastoring.DataStoreApiImpl();
		FetchApiImpl fetchApi = new FetchApiImpl();
		fetchApi.setDataStoreApi(dataStoreApi);
		fetchApi.setComputingApi(computingApi);
		return fetchApi;
	}

	
	public List<BigInteger> insertRequest(FetchRequest fetchRequest) {
		return delegate.insertRequest(fetchRequest);
	}

	
	public boolean validateNumber(BigInteger number) {
		return delegate.validateNumber(number);
	}

	
	public boolean processFile(String inputPath, String outputPath) {
		return delegate.processFile(inputPath, outputPath);
	}
}