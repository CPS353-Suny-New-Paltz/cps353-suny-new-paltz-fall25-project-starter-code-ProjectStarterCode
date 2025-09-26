package project.usercompute;

import project.annotations.NetworkAPI;


@NetworkAPI

public interface UserComputeAPI {

	void handleRequest(UserRequest userRequest);

}