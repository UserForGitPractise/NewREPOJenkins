package rest;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.restassured.internal.common.assertion.Assertion;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.*;
import pojos.CreateUserResponse;
import pojos.UpdateUserResponce;
import pojos.Users;
import utils.RestWrapper;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("rest-api-tests")
@DisplayName("Tests for API check")
@Feature("API Implementaion for users management")
public class RestTests {
    private static RestWrapper api;

    @BeforeAll
    public static void prepareClient (){
        api = RestWrapper.
                loginAs("eve.holt@reqres.in", "cityslicks");
    }

    @Test
    @DisplayName("Getting list of existing users")
    @Story("User Creation")
    public void getUsers(){
        List<Users> user = api.getUsers();

        assertThat(user)
                .extracting(Users::getEmail)
                .contains("janet.weaver@reqres.in");
    }
    @Test
    @DisplayName("Создание пользователя")
    @Story("User Creation")
    @Description("Create user and then check that user is created with passed parameters")
    public void createUser() {
        CreateUserResponse user = RestTests.api.createUser();
        assertThat(user).extracting(CreateUserResponse::getName).isEqualTo(user.getName());
        assertThat(user).extracting(CreateUserResponse::getJob).isEqualTo(user.getJob());
    }
    @Test
    @DisplayName("test1")
    @Story("Empty Test For Nothing")
    @Description("Some text for a beatiful description")
    public void test11 (){

        org.junit.jupiter.api.Assertions.assertFalse((new Users()).falseReturner());
    }

    @Test
    @DisplayName("Check list of users params implementaion")
    @Story("User Params implementaion")
    @Description("Some text for a beatiful description")
    public  void checkUsersParameters() {

        Assertions.assertThat(api.getUsers()).extracting(Users::getId).containsExactly(1, 2, 3, 4, 5, 6);
        Assertions.assertThat(api.getUsers()).extracting(Users::getfirstName).containsExactly("George", "Janet", "Emma", "Eve", "Charles", "Tracey");
        Assertions.assertThat(api.getUsers()).extracting(Users::getlastName).containsExactly("Bluth", "Weaver", "Wong", "Holt", "Morris", "Ramos");
    }

    @Test
    @DisplayName("Check update of list of users params implementaion")
    @Story("User Params implementaion")
    @Description("Some text for a beatiful description")
    public void checkUpdateUserParamaters(){
        assertThat(api.updateUser()).extracting(UpdateUserResponce::getName).isEqualTo("Nick");
        assertThat(api.updateUser()).extracting(UpdateUserResponce::getJob).isEqualTo("QA-engineer");
    }
    @Test
    @DisplayName("Check single user params implementaion")
    @Story("User Params implementaion")
    @Description("Some text for a beatiful description")
    public  void checkSingleUserParameters() {
        assertThat(api.getSingleUser()).extracting(Users::getId).isEqualTo(2);
        Assertions.assertThat(api.getSingleUser()).extracting(Users::getfirstName).isEqualTo("Janet");
        Assertions.assertThat(api.getSingleUser()).extracting(Users::getlastName).isEqualTo("Weaver");
    }
    @Test
    @Story("Some text for a beutiful story view")
    @DisplayName("Проверка обновления параметров обновленного пользователя (обновление через метод Patch)")
    @Description("Some text for a beatiful description")
    public void checkPatchUserParameters(){
        assertThat(api.patchUser()).extracting(UpdateUserResponce::getName).isEqualTo("Alex");
        assertThat(api.patchUser()).extracting(UpdateUserResponce::getJob).isEqualTo("Developer");
    }
    @Test
    @DisplayName("Удаление пользователя")
    @Story("User delete")
    @Description("Delete an existing user")
    public void deleteUser (){
        api.deleteUser();
    }


}

