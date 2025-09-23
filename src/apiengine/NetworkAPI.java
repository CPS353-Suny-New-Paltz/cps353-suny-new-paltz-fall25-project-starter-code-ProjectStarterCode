package apiengine;

@project.annotations.NetworkAPI
public interface NetworkAPI {
	void sendJob(String job);
}
