package project.annotations;

public class UserPrototype {
    @NetworkAPIPrototype
    public String prototype(UserAPI userApi) {
    	
        userApi.setInput("file://inputs.txt");
        userApi.setOutput("file://outputs.txt");
        userApi.useDefaultDelimiters();
        
        String result = userApi.run();
        
        return result;
    }
}
