package api;
import project.annotations.NetworkAPIPrototype;

public class UserAPIPrototype {
	 @NetworkAPIPrototype
    public void prototype(UserAPI userapi) {
    	 userapi.setInputSource("file://inputs.txt");
         userapi.setOutputDestination("file://outputs.txt");
         userapi.useDefaultDelimiters(); // or user.setDelimiters(";", ":");
    }
}
