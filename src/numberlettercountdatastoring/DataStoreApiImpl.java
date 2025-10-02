package numberlettercountdatastoring;

import numberlettercountcomputing.ComputingApi;

public class DataStoreApiImpl implements DataStoreApi {
    private ComputingApi computingApi;
    
    @Override
    public int insertRequest(DataRequest dataRequest) {
    	return -1; // failure value as int
    }
    
    @Override
    public Serialize serializingData() {
        return null;
    }
    
    @Override
    public FormatData formatData() {
        return null;
    }
    
    @Override
    public ResultOfCountLetter result() {
        return null;
    }
    
    @Override
    public SendInfo sentData() {
        return null;
    }
    
    @Override
    public RecieveInfo recieveData() {
        return null;
    }
}
