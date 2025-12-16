package implementations;

import api.DataCheck;
import api.DataProcessAPI;
import api.ReadRequest;
import api.ReadResponse;
import api.WriteOutput;

public class DataProcessImpl implements DataProcessAPI{
	
	@Override
	public ReadResponse input(ReadRequest request) {
		return null;
	}
	
	@Override
	public WriteOutput sendData(DataCheck data) {
		return null;
	}
}