package rest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;
import pojos.CreateUserRequest;
import pojos.CreateUserResponse;
import pojos.UpdateUserResponce;
import pojos.Users;
import steps.UserSteps;
import utils.RestWrapper;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static steps.UserSteps.*;



public class RestTest {

    private static RestWrapper api;

    @BeforeAll
    public static void prepareClient (){
        api = RestWrapper.
                loginAs("eve.holt@reqres.in", "cityslicks");
    }

    @Test
    public void getUsers(){
        List<Users> user = api.getUsers();
        assertThat(user)
                .extracting(Users::getEmail)
                .contains("janet.weaver@reqres.in");
    }
    @Test
    public void createUser() {
        CreateUserResponse user = RestTest.api.createUser();
        assertThat(user).extracting(CreateUserResponse::getName).isEqualTo(user.getName());
        Assertions.assertThat(user).extracting(CreateUserResponse::getJob).isEqualTo(user.getJob());
    }


    @Test
    public  void checkUsersParameters() {

        Assertions.assertThat(api.getUsers()).extracting(Users::getId).containsExactly(1, 2, 3, 4, 5, 6);
        Assertions.assertThat(api.getUsers()).extracting(Users::getfirstName).containsExactly("George", "Janet", "Emma", "Eve", "Charles", "Tracey");
        Assertions.assertThat(api.getUsers()).extracting(Users::getlastName).containsExactly("Bluth", "Weaver", "Wong", "Holt", "Morris", "Ramos");
    }

    @Test
    public  void checkSingleUserParameters() {
        assertThat(api.getSingleUser()).extracting(Users::getId).isEqualTo(2);
        Assertions.assertThat(api.getSingleUser()).extracting(Users::getfirstName).isEqualTo("Janet");
        Assertions.assertThat(api.getSingleUser()).extracting(Users::getlastName).isEqualTo("Weaver");
    }
    @Test
    public void checkUpdateUserParamaters(){
        assertThat(api.updateUser()).extracting(UpdateUserResponce::getName).isEqualTo("Nick");
        assertThat(api.updateUser()).extracting(UpdateUserResponce::getJob).isEqualTo("QA-engineer");
    }
    @Test
    public void checkPatchUserParameters(){
        assertThat(api.patchUser()).extracting(UpdateUserResponce::getName).isEqualTo("Nick");
        assertThat(api.patchUser()).extracting(UpdateUserResponce::getJob).isEqualTo("QA-engineer");
    }
    @Test
    public void deleteUser (){
        api.deleteUser();
    }
    @Test
    public void delayedResponse (){
        assertThat(api.getDelayedUsersResponse()).extracting(Users::getEmail).containsExactly("george.bluth@reqres.in", "janet.weaver@reqres.in", "emma.wong@reqres.in","eve.holt@reqres.in","charles.morris@reqres.in","tracey.ramos@reqres.in");
        assertThat(api.getDelayedUsersResponse()).extracting(Users::getfirstName).containsExactly("George", "Janet", "Emma", "Eve", "Charles", "Tracey");

    }


}

