package numberlettercountfetching;

import java.util.List;

public class FetchRequest {

	private List<Integer> data;
	public FetchRequest() { } // Default constructor
	public FetchRequest(List<Integer> numbers) {
		this.data = numbers; 
	} // List constructor
	public List<Integer> getData() {
		return this.data;
	}


	//	public FetchRequest(List<Integer> i) {
	//		this.number = i;
	//	}
	//
	//	public List<Integer> getNumber() {
	//		return number;
	//	}
	//
	//	public List<Integer> getData() {
	//		
	//		return this.number;
	//	}







}
