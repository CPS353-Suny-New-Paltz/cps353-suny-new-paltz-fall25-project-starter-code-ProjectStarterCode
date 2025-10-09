package project.usercompute;

import project.annotations.NetworkAPIPrototype;

public class UserComputeAPIPrototype {
    @NetworkAPIPrototype
    public void prototype(UserComputeAPI user) {
        UserComputeAPI compute = user;
        compute.handleRequest(new UserRequest("Hello from UserCompute".getBytes()));
        compute.handleRequest(new UserRequest(new byte[]{10, 20, 30}));
    }
}