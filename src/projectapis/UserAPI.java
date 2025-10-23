//network api
package projectapis;
import project.annotations.NetworkAPI;

@NetworkAPI
public interface UserAPI {
	
	//set input and output sources
	void setInput(String input);
	void setOutput(String output);
	
	//not necessary in my computation
	void setDelimiter(String delimiter);
	
	void executeComputation();
}
