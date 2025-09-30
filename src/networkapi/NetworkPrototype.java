package networkapi;


import assets.ResponseCode;
import assets.UserRequest;
import project.annotations.NetworkAPIPrototype;

	public class NetworkPrototype {
	@NetworkAPIPrototype
		public void prototype(NetworkInterfaceAPI api) {
			// Step 1: Build the user request

			UserRequest request = new UserRequest.Builder()
					.inputSource("user") // placeholder
					.outputDestination("src/Output/output.txt").delimiter(",") // or prompt user later
					.useDefaultDelimiter(true).build();



			// Step 2: Respond to user using enum
			//simulate response
			if (request.isValid()) {
				System.out.println("Request completed successfully.");
				ResponseCode response = ResponseCode.SUCCESS;
				//if request successful we will call process api
			} else {
				System.out.println("Request failed.");
				ResponseCode response = ResponseCode.FAILURE;
			}

			//Step 3: start initialization of process
			//this is where the processAPI will be implemented and called to start the processing of the request
		}

	}

