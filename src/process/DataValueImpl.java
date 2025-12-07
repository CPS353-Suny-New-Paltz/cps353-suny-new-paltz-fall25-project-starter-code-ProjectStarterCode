package process;

public class DataValueImpl implements DataValue{
	private final int value;

	public DataValueImpl(int value) {
		this.value = value;
	}

	@Override
	public int getValue() {
		return value;
	}
}