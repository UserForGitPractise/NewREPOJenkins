package utils;

import pojos.CreateUserRequest;
import pojos.UpdateUserRequest;

public class UserGenerator {
    public static CreateUserRequest createSimpleUser (){
        CreateUserRequest user = new CreateUserRequest();
        user.setJob("simple");
        user.setJob("automation");
        return user;
    }
    public static UpdateUserRequest updateUser(){
        UpdateUserRequest updatedUser = new UpdateUserRequest();
        updatedUser.setName("Nick");
        updatedUser.setJob("QA-engineer");
        return updatedUser;
    }
}
