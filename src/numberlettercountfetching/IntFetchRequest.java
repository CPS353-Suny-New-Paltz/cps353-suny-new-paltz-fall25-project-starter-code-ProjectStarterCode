package numberlettercountfetching;
import java.util.Arrays;


public class IntFetchRequest extends FetchRequest {
	private int data;

	public IntFetchRequest(int number) {
		super(Arrays.asList(number)); // Pass as List
		this.data = number;
	}

	public int getIntData() {
		return (Integer) data;
	}

	//    public FetchRequest(int data) {
	//    	return this.data;
	//    }
}