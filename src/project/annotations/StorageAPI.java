package project.annotations;

@ProcessAPI
public interface StorageAPI {
	void readInput(String source);

	void writeOutput(String destination);
}