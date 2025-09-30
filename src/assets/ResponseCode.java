package assets;

public enum ResponseCode {

	   SUCCESS(true),
	    FAILURE(false);

	    private final boolean status;

	    ResponseCode(boolean status) {
	        this.status = status;
	    }

	    public boolean isSuccess() {
	        return status;
	    }

}
