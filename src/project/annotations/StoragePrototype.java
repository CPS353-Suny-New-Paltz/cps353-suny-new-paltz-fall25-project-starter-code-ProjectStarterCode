package project.annotations;

public class StoragePrototype {
	@ProcessAPIPrototype
	public void prototype(StorageAPI storageapi) {
		storageapi.readInput("Hello world!");
		storageapi.writeOutput(null);
	}
}