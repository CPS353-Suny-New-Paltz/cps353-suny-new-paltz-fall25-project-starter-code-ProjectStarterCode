package numberlettercountfetching;

import project.annotations.NetworkAPIPrototype;

public class FetchApiPrototype {
	
	String inputSource;
	
	String outputResult;
	
	
	
	final String defaultDelimiters = ".;|";
	
	private String delimiters;

	@NetworkAPIPrototype
	public void prototype(FetchApi fetchapi)   {
		
		fetchapi.insertRequest(new FetchRequest(123));		
	}
	
	public  String getInputSource(String inputSource) {
		return inputSource;
	}
	
	public String getOutputResult(String outputResult) {
		return outputResult;
	}
	
	public String setDelimiters(String delimiters) {
		return this.setDelimiters(delimiters);
	}
	
	public String useDefaultDelimiters(String delimiters) {
		return this.useDefaultDelimiters(delimiters);
	}
	
	public void passData(PassData data) {
		this.passData(data);
	}
	
	public void displayResult(Display display) {
		this.displayResult(display);
	}

}