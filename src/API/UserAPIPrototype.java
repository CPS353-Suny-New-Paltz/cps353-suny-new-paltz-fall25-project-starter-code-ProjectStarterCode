package API;

public class UserAPIPrototype {
    public void prototype(UserAPI userapi) {
    	 userapi.setInputSource("file://inputs.txt");
         userapi.setOutputDestination("file://outputs.txt");
         userapi.useDefaultDelimiters(); // or user.setDelimiters(";", ":");
    }
}
