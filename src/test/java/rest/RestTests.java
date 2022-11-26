package rest;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import rest.pojos.*;
import rest.utils.RestWrapper;
import rest.utils.UserGenerator;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static rest.utils.RestWrapper.loginAs;
import static rest.utils.UserGenerator.createSimpleUser;
import static rest.utils.UserOperations.GetAllUsersList.*;

@Tag("rest-api-tests")
@DisplayName("Tests for API check")
@Feature("API Implementaion for users management")
public class RestTests {
    private static RestWrapper api;

    @BeforeAll
    public static void prepareClient() {
        api = loginAs("eve.holt@reqres.in", "cityslicks");
    }
    @ParameterizedTest
    @MethodSource("rest.utils.UserOperations.GetAllUsersList#allUsersStream")
    public void getUsers(Users user) {
        assertThat(api.singleUserOperations.getSingleUser(user.getId())).extracting(Users::getId).isEqualTo(user.getId());
        assertThat(api.singleUserOperations.getSingleUser(user.getId())).extracting(Users::getEmail).isEqualTo(user.getEmail());
        assertThat(api.singleUserOperations.getSingleUser(user.getId())).extracting(Users::getfirstName).isEqualTo(user.getfirstName());
        assertThat(api.singleUserOperations.getSingleUser(user.getId())).extracting(Users::getlastName).isEqualTo(user.getlastName());
    }

    @Test
    @DisplayName("Getting list of existing users")
    @Story("User Creation")
    public void getUsers() {
        List<Users> usersList = api.getUser.getUsers();
        int index = 0;
        for (Users user :
                usersList) {
            assertThat(api.getUser.getUsers()).extracting(Users::getId).containsExactly(1, 2, 3, 4, 5, 6);
            assertThat(user).isNotNull().extracting(Users::getEmail).isEqualTo(getAllUsersEmail(index));
            assertThat(user).isNotNull().extracting(Users::getfirstName).isEqualTo(getAllUsersName(index));
            assertThat(user).isNotNull().extracting(Users::getlastName).isEqualTo(getAllUsersLastname(index));
            index++;
        }
    }

    @Test
    @DisplayName("User Creation")
    @Story("User Creation")
    @Description("Create user and check that user is created with required parameters")
    public void checkCreatedUserParameters(int id) {
        CreateUserRequest rq = createSimpleUser();
        CreateUserResponse user = api.createUser.createUser(rq);
        assertThat(user).isNotNull().extracting(CreateUserResponse::getName).isEqualTo(rq.getName());
        assertThat(user).isNotNull().extracting(CreateUserResponse::getJob).isEqualTo(rq.getJob());
        assertThat(user).isNotNull().extracting(CreateUserResponse::getCreatedAt).isEqualTo(user.getCreatedAt());
        assertThat(user.getId()).isNotNull();
        assertTrue(IntStream.range(1, 1000).anyMatch(i -> i == user.getId()));
    }
 /*   @Deprecated
    //@Test
    @DisplayName("Check list of users params implementaion")
    @Story("User Params implementaion")
    @Description("Some text for a beatiful description")
    public void checkUsersParameters() {
        assertThat(api.getUser.getUsers()).extracting(Users::getId).containsExactly(1, 2, 3, 4, 5, 6);
        assertThat(api.getUser.getUsers()).extracting(Users::getfirstName).containsExactly("George", "Janet", "Emma", "Eve", "Charles", "Tracey");
        assertThat(api.getUser.getUsers()).extracting(Users::getlastName).containsExactly("Bluth", "Weaver", "Wong", "Holt", "Morris", "Ramos");
    }*/

    @Test
    @DisplayName("Check update of list of users params implementaion")
    @Story("User Params implementaion")
    @Description("Some text for a beatiful description")
    public void checkUpdatedUserParamaters() {
        UpdateUserRequest urq = UserGenerator.updateSimpleUser();
        UpdateUserResponce urs = api.singleUserOperations.updateUser(urq);
        assertThat(urs).extracting(UpdateUserResponce::getName).isEqualTo(urq.getName());
        assertThat(urs).extracting(UpdateUserResponce::getJob).isEqualTo(urq.getJob());
    }

    @Test
    @Story("Some text for a beutiful story view")
    @DisplayName("Check pactched user parameters")
    @Description("Some text for a beatiful description")
    public void checkPatchUserParameters() {
        UpdateUserRequest ur = UserGenerator.patchSimpleUser();
        assertThat(api.singleUserOperations.patchUser(ur)).extracting(UpdateUserResponce::getName).isEqualTo(ur.getName());
        assertThat(api.singleUserOperations.patchUser(ur)).extracting(UpdateUserResponce::getJob).isEqualTo(ur.getJob());
    }

    @Test
    @DisplayName("User deletion")
    @Story("User delete")
    @Description("Delete an existing user")
    public void deleteUser() {
        api.singleUserOperations.deleteUser();
    }
}

