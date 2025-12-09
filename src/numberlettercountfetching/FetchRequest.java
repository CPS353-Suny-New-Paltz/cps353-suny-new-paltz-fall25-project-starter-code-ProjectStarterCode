package numberlettercountfetching;

import java.util.ArrayList;
import java.util.List;

public class FetchRequest {
	private List<Integer> data;

	public FetchRequest() { 
		this.data = new ArrayList<>();
	}

	public FetchRequest(List<Integer> numbers) {
		this.data = numbers != null ? new ArrayList<>(numbers) : new ArrayList<>();
	}

	public List<Integer> getData() {
		return new ArrayList<>(this.data);
	}

	public void setData(List<Integer> data) {
		this.data = data != null ? new ArrayList<>(data) : new ArrayList<>();
	}

	public String toString() {
		return "FetchRequest{data=" + data + "}";
	}
}