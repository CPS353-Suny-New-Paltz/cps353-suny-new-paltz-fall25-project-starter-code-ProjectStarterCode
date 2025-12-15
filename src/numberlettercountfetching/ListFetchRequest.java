package numberlettercountfetching;

import java.math.BigInteger;
import java.util.List;

public class ListFetchRequest extends FetchRequest {
	public ListFetchRequest(List<BigInteger> list) {
		super(list);
	}

	public List<BigInteger> getListData() {
		return getData();
	}
}