package inmemory;

import numberlettercountdatastoring.DataStoreApi;
import numberlettercountdatastoring.DataRequest;
import numberlettercountdatastoring.Serialize;
import numberlettercountdatastoring.FormatData;
import numberlettercountdatastoring.ResultOfCountLetter;
import numberlettercountdatastoring.SendInfo;
import numberlettercountdatastoring.RecieveInfo;
import configuration.TestInputConfiguration;
import configuration.TestOutputConfiguration;

public class InMemoryDataStore implements DataStoreApi {
	private TestInputConfiguration inputConfig;
	private TestOutputConfiguration outputConfig;

	public InMemoryDataStore(TestInputConfiguration inputConfig, TestOutputConfiguration outputConfig) {
		this.inputConfig = inputConfig;
		this.outputConfig = outputConfig;
	}

	@Override
	public int insertRequest(DataRequest dataRequest) {
		// Read from input config and write to output config
		if (inputConfig != null && outputConfig != null) {
			for (Integer number : inputConfig.getInputNumbers()) {
				// For now, just convert number to string as placeholder
				// This will be replaced with actual number-to-letter conversion later
				outputConfig.addOutputString(String.valueOf(number));
			}
			return 0;  // SUCCESS as int (0 typically means success)
		}
		return -1;  // FAILED as int (-1 typically means failure)
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
