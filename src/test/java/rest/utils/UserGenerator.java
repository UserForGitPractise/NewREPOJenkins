package rest.utils;

import rest.pojos.CreateUserRequest;
import rest.pojos.UpdateUserRequest;

public class UserGenerator {
    public static CreateUserRequest createSimpleUser (){
        CreateUserRequest user = new CreateUserRequest();
        user.setName("Ivan");
        user.setJob("Developer");
        return user;
    }
    public static CreateUserRequest createComplexUser(){
        CreateUserRequest user = new CreateUserRequest();
        user.setName("Nick");
        user.setJob("QA");
        return user;
    }
    public static UpdateUserRequest updateSimpleUser(){
        UpdateUserRequest updatedSimpleUser = new UpdateUserRequest();
        updatedSimpleUser.setName("Nick");
        updatedSimpleUser.setJob("QA-engineer");
        return updatedSimpleUser;
    }
    public static UpdateUserRequest updateComplexUser(){
        UpdateUserRequest updatedComplexUser = new UpdateUserRequest();
        updatedComplexUser.setName("Alex");
        updatedComplexUser.setJob("ComplexDeveloper");
        return updatedComplexUser;
    }
    public static UpdateUserRequest patchSimpleUser(){
        UpdateUserRequest patchdUser = new UpdateUserRequest();
        patchdUser.setName("Alex");
        patchdUser.setJob("Developer");
        return patchdUser;
    }
    public static UpdateUserRequest patchComplexUser(){
        UpdateUserRequest patchUser = new UpdateUserRequest();
        patchUser.setName("Mike");
        patchUser.setJob("Manager");
        return patchUser;
    }
}
