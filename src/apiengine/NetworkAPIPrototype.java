package apiengine;


public class NetworkAPIPrototype {
	
	@project.annotations.NetworkAPIPrototype
	public void runPrototype(NetworkAPI api) {
		api.sendJob("job");
	}

}
