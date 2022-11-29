package rest;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import rest.pojos.*;
import rest.utils.RestWrapper;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static rest.utils.RestWrapper.loginAs;
import static rest.utils.UserGenerator.*;
import static rest.utils.UserOperations.GetAllUsersList.*;

@Tag("йцу")
@DisplayName("Tests for API check")
@Feature("API Implementaion for users management")
public class RestTests {
    private static RestWrapper api;

    public static Stream<CreateUserRequest> userCreateProvider() {
        return Stream.of(createDeveloper(), createQA(), createDevOps(), createManager());
    }

    public static Stream<UpdateUserRequest> userUpdateProvider() {
        return Stream.of(updateToDeveloper(), updateToQA(), updateToDevOps(), updateToManager());
    }

    public static Stream<UpdateUserRequest> userPatchProvider() {
        return Stream.of(addSkillDeveloper(), addSkillQA(), addSkillDevOps(), addSkillManager());
    }

    @BeforeAll
    public static void prepareUser() {
        api = loginAs("eve.holt@reqres.in", "cityslicks");
    }

    @ParameterizedTest(name = "Get single user and verify params. {0}")
//    @DisplayName("Get single user and verify params")
    @MethodSource("rest.utils.UserOperations.GetAllUsersList#allUsersStream")
    @Description("Send get-single request and verify ID, Email, First Name and Last Name Parameters")
    @Story("Get user")
    public void getSingleUser(Users user) {
        assertThat(api.singleUserOperations.getSingleUser(user.getId())).extracting(Users::getId).isEqualTo(user.getId());
        assertThat(api.singleUserOperations.getSingleUser(user.getId())).extracting(Users::getEmail).isEqualTo(user.getEmail());
        assertThat(api.singleUserOperations.getSingleUser(user.getId())).extracting(Users::getfirstName).isEqualTo(user.getfirstName());
        assertThat(api.singleUserOperations.getSingleUser(user.getId())).extracting(Users::getlastName).isEqualTo(user.getlastName());
    }

    @Test
    @DisplayName("Get all existing users and verify params")
    @Description("Send get-all-existing-users request and verify each user ID, Email, First Name and Last Name parameters")
    @Story("Get user")
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

    @ParameterizedTest(name = "Create user and verify params. {0}")
    @MethodSource("userCreateProvider")
    //  @DisplayName("Create user and verify params")
    @Description("Send user create request and verify Name, Job, CreatedAt and ID parameters of created user")
    @Story("Create SINGLE user")
    public void checkCreatedDeveloperParameters(CreateUserRequest rq) {
        CreateUserResponse user = api.createUser.createUser(rq);
        assertThat(user).isNotNull().extracting(CreateUserResponse::getName).isEqualTo(rq.getName());
        assertThat(user).isNotNull().extracting(CreateUserResponse::getJob).isEqualTo(rq.getJob());
        assertThat(user).isNotNull().extracting(CreateUserResponse::getCreatedAt).isEqualTo(user.getCreatedAt());
        assertThat(user.getId()).isNotNull();
        assertTrue(IntStream.range(1, 1000).anyMatch(i -> i == user.getId()));
    }

    @ParameterizedTest(name = "Update user and verify params. {0}")
    @MethodSource("userUpdateProvider")
    // @DisplayName("Update user and verify params")
    @Description("Send update-request and verify Name and Job of updated user")
    @Story("Update SINGLE user")
    public void checkUpdatedUserParamaters(UpdateUserRequest urq) {
        UpdateOrPatchUserResponse urs = api.singleUserOperations.updateUser(urq);
        assertThat(urs).extracting(UpdateOrPatchUserResponse::getName).isEqualTo(urq.getName());
        assertThat(urs).extracting(UpdateOrPatchUserResponse::getJob).isEqualTo(urq.getJob());
    }

    @ParameterizedTest(name = "Patch user and verify params. {0}")
    @MethodSource("userPatchProvider")
    //@DisplayName("Patch user and verify params")
    @Description("Send patch-request and verify Name and Job of patched user")
    @Story("Update SINGLE user")
    public void checkPatchUserParameters(UpdateUserRequest urq) {
        UpdateOrPatchUserResponse urs = api.singleUserOperations.patchUser(urq);
        assertThat(urs).extracting(UpdateOrPatchUserResponse::getName).isEqualTo(urq.getName());
        assertThat(urs).extracting(UpdateOrPatchUserResponse::getJob).isEqualTo(urq.getJob());
    }

    @Test
    @DisplayName("Delete user")
    @Description("Send delete-request and verify status code")
    @Story("Delete SIGNLE user")
    public void deleteUser() {
        api.singleUserOperations.deleteUser();
    }
}

