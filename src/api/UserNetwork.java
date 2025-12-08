package api;

import project.annotations.NetworkAPI;

@NetworkAPI
public interface UserNetwork {

	UserInput request(UserInput userInput);

	InputVerified check(UserInput input);

	NetworkResponse answer(InputVerified verify, UserInput input);
	
	
}
