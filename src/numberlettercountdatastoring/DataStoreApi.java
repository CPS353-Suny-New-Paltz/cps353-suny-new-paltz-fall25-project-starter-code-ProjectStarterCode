package numberlettercountdatastoring;
import project.annotations.ProcessAPI;

@ProcessAPI
public interface DataStoreApi {
	int insertRequest(DataRequest dataRequest);    
	boolean validateNumber(int number);
}
