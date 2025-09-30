package networkapi;

import assets.UserRequest;
import project.annotations.NetworkAPI;


@NetworkAPI
public interface NetworkInterfaceAPI {

	void initalize(UserRequest request);
	void respond();
}
