package api;
import project.annotations.NetworkAPIPrototype;

public class UserPrototypeAPI {
	@NetworkAPIPrototype
	public void userPrototypeAPI(UserNetwork network) {
		
		//User input is requested
		UserInput input = network.request(new UserInput(null, null));
		
		// The user input works with the program a
		InputVerified verify = network.check(input);
		
		// After computation, the user receives the answer
		NetworkResponse response = network.answer(verify, input);
		
				
	}
}
