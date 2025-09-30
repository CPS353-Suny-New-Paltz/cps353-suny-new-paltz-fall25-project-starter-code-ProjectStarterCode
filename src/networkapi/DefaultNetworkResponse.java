package networkapi;

import assets.ResponseCode;

public class DefaultNetworkResponse implements NetworkResponse {

	private final ResponseCode result;

	@Override
	public ResponseCode getResult() {
		return result;
	}

	public DefaultNetworkResponse(ResponseCode result) {
		super();
		this.result = result;
	}

	public boolean isSuccess() {
		return result == ResponseCode.SUCCESS;
	}

	public boolean isFailure() {
		return result == ResponseCode.FAILURE;
	}

}
