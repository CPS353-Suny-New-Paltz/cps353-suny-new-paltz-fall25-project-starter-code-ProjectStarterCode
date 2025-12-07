package process;

import java.util.List;

public class MultiDataValueImpl implements MultiDataValue {
	private final List<Integer> values;
	
	public MultiDataValueImpl(List<Integer> values) {
		this.values = values;
	}
	
	@Override
	public List<Integer> getValues() {
		return values;
	}

}
