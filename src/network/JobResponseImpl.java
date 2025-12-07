package network;

public class JobResponseImpl implements JobResponse {
	private final boolean success;
	private final String message;

	public JobResponseImpl(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	@Override
	public boolean isSuccess() {
		return success;
	}

	@Override
	public String getMessage() {
		return message;
	}

}