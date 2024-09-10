package rest;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.RestAssured;

import io.restassured.config.LogConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.internal.multipart.MultiPartSpecificationImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import rest.pojos.*;
import rest.utils.RestWrapper;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static io.restassured.config.LogConfig.logConfig;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static rest.utils.RestWrapper.loginAs;
import static rest.utils.RestWrapper.loginAsDefaultUser;
import static rest.utils.UserGenerator.*;
import static rest.utils.UserOperations.GetAllUsersList.*;

@Tag("rest")
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
        api = loginAsDefaultUser();
//                api =loginAs("eve.holt@reqres.in", "cityslicks");
    }

//    @ParameterizedTest(name = "Get single user {0}")
    @MethodSource("rest.utils.UserOperations.GetAllUsersList#allUsersStream")
    @Description("Send get-single request and verify ID, Email, First Name and Last Name Parameters")
    @Story("Get user")
    public void getSingleUser(Users user) {
        int [] asd = new int[]{1,2,4};
        List <Integer> asdd = Arrays.stream(asd).boxed().sorted().collect(Collectors.toList());
        int asdasd;
        while(asdd.iterator().hasNext()){
            asdasd  = (Arrays.stream(asd).filter(x->x+1!=asdd.get(asdd.indexOf(x)+1)).findFirst().getAsInt());
        }
        System.out.println("asd");
//        Users rs = api.singleUserOperations.getSingleUser(user.getId());
//        assertThat(rs).extracting(Users::getId).isEqualTo(user.getId());
//        assertThat(rs).extracting(Users::getEmail).isEqualTo(user.getEmail());
//        assertThat(rs).extracting(Users::getfirstName).isEqualTo(user.getfirstName());
//        assertThat(rs).extracting(Users::getlastName).isEqualTo(user.getlastName());
    }

    //TODO: CHANGE REGEX TO CAPTURE BOTH GET REQUESTS (SINGLE AND GET)
    @Test
    @DisplayName("Get all existing users")
    @Description("Send get-all-existing-users request and verify each user ID, Email, First Name and Last Name parameters")
    @Story("Get user")
    public void getUsers() {
        List<Users> usersList = api.getUser.getUsers();
        int index = 0;
        for (Users user :
                usersList) {
            assertThat(usersList).extracting(Users::getId).containsExactly(1, 2, 3, 4, 5, 6);
            assertThat(user).isNotNull().extracting(Users::getEmail).isEqualTo(getAllUsersEmail(index));
            assertThat(user).isNotNull().extracting(Users::getfirstName).isEqualTo(getAllUsersName(index));
            assertThat(user).isNotNull().extracting(Users::getlastName).isEqualTo(getAllUsersLastname(index));
            index++;
        }
    }

//    @ParameterizedTest(name = "Create user {0}")
    @MethodSource("userCreateProvider")
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

//    @ParameterizedTest(name = "Update user {0}")
    @MethodSource("userUpdateProvider")
    @Description("Send update-request and verify Name and Job of updated user")
    @Story("Update SINGLE user")
    public void checkUpdatedUserParamaters(UpdateUserRequest urq) {
        UpdateOrPatchUserResponse urs = api.singleUserOperations.updateUser(urq);
        assertThat(urs).extracting(UpdateOrPatchUserResponse::getName).isEqualTo(urq.getName());
        assertThat(urs).extracting(UpdateOrPatchUserResponse::getJob).isEqualTo(urq.getJob());
    }

//    @ParameterizedTest(name = "Patch user {0}")
    @MethodSource("userPatchProvider")
    @Description("Send patch-request and verify Name and Job of patched user")
    @Story("Update SINGLE user")
    public void checkPatchUserParameters(UpdateUserRequest urq) {
        UpdateOrPatchUserResponse urs = api.singleUserOperations.patchUser(urq);
        assertThat(urs).extracting(UpdateOrPatchUserResponse::getName).isEqualTo(urq.getName());
        assertThat(urs).extracting(UpdateOrPatchUserResponse::getJob).isEqualTo(urq.getJob());
    }

//    @Test
    @DisplayName("Delete user")
    @Description("Send delete-request and verify status code")
    @Story("Delete SIGNLE user")
    public void deleteUser() {
        api.singleUserOperations.deleteUser();
    }

    public void test1() {
        List<Users> users = api.getUser.getUsers();
        assertThat(users).extracting(Users::getEmail).isEqualTo(users);
    }
}

