package rest;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.*;
import rest.pojos.*;
import rest.utils.RestWrapper;
import rest.utils.UserGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("rest-api-tests")
@DisplayName("Tests for API check")
@Feature("API Implementaion for users management")
public class RestTests {
    private static RestWrapper api;

    @BeforeAll
    public static void prepareClient() {
        api = RestWrapper.
                loginAs("eve.holt@reqres.in", "cityslicks");
    }

    @Test
    @DisplayName("Getting list of existing users")
    @Story("User Creation")
    public void getUsers() {
        List<Users> user = api.getUser.getUsers();
        assertThat(user).isNotNull().extracting(Users::getEmail).containsOnlyOnce("janet.weaver@reqres.in");
        assertThat(user).isNotNull().extracting(Users::getfirstName).containsOnlyOnce("Janet");
        assertThat(user).isNotNull().extracting(Users::getlastName).containsOnlyOnce("Weaver");
    }

    @Test
    @DisplayName("User Creation")
    @Story("User Creation")
    @Description("Create user and check that user is created with required parameters")
    public void createUser() {
        CreateUserRequest rq = UserGenerator.createSimpleUser();
        CreateUserResponse user = api.createUser.createUser(rq);
        assertThat(user).isNotNull().extracting(CreateUserResponse::getName).isEqualTo(rq.getName());
        assertThat(user).isNotNull().extracting(CreateUserResponse::getJob).isEqualTo(rq.getJob());
        assertThat(user).isNotNull().extracting(CreateUserResponse::getCreatedAt).isEqualTo(user.getCreatedAt());
        assertTrue(IntStream.range(1,1000).anyMatch(i -> i==user.getId()));
        assertThat(user.getId()).isNotNull();
    }
    @Test
    @DisplayName("Check list of users params implementaion")
    @Story("User Params implementaion")
    @Description("Some text for a beatiful description")
    public void checkUsersParameters() {
        assertThat(api.getUser.getUsers()).extracting(Users::getId).containsExactly(1, 2, 3, 4, 5, 6);
        assertThat(api.getUser.getUsers()).extracting(Users::getfirstName).containsExactly("George", "Janet", "Emma", "Eve", "Charles", "Tracey");
        assertThat(api.getUser.getUsers()).extracting(Users::getlastName).containsExactly("Bluth", "Weaver", "Wong", "Holt", "Morris", "Ramos");
    }

    @Test
    @DisplayName("Check update of list of users params implementaion")
    @Story("User Params implementaion")
    @Description("Some text for a beatiful description")
    public void checkUpdateUserParamaters() {
        UpdateUserRequest ur = UserGenerator.updateSimpleUser();
        assertThat(api.updateUser.updateUser(ur)).extracting(UpdateUserResponce::getName).isEqualTo(ur.getName());
        assertThat(api.updateUser.updateUser(ur)).extracting(UpdateUserResponce::getJob).isEqualTo(ur.getJob());
    }

    @Test
    @DisplayName("Check single user params implementaion")
    @Story("User Params implementaion")
    @Description("Some text for a beatiful description")
    public void checkGetSingleUserParameters() {
        assertThat(api.getUser.getSingleUser()).extracting(Users::getId).isEqualTo(2);
        assertThat(api.getUser.getSingleUser()).extracting(Users::getfirstName).isEqualTo("Janet");
        assertThat(api.getUser.getSingleUser()).extracting(Users::getlastName).isEqualTo("Weaver");
    }

    @Test
    @Story("Some text for a beutiful story view")
    @DisplayName("Check pactched user parameters")
    @Description("Some text for a beatiful description")
    public void checkPatchUserParameters() {
        UpdateUserRequest ur = UserGenerator.patchSimpleUser();
        assertThat(api.updateUser.patchUser(ur)).extracting(UpdateUserResponce::getName).isEqualTo(ur.getName());
        assertThat(api.updateUser.patchUser(ur)).extracting(UpdateUserResponce::getJob).isEqualTo(ur.getJob());
    }

    @Test
    @DisplayName("User deletion")
    @Story("User delete")
    @Description("Delete an existing user")
    public void deleteUser() {
        api.updateUser.deleteUser();
    }
}

