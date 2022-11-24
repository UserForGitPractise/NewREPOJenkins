package rest.utils;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.specification.RequestSpecification;
import rest.pojos.*;
import rest.pojos.*;
import rest.utils.UserOperations.UserCreateOperations;
import rest.utils.UserOperations.UserGetOperations;
import rest.utils.UserOperations.UserUpdateOperations;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestWrapper extends BaseTest {
    public UserCreateOperations createUser;
    public UserUpdateOperations updateUser;
    public UserGetOperations getUser;
    @Override
    public String getPath() {
        return "";
    }
    private RestWrapper(String token) {
        super(token);
        createUser = new UserCreateOperations(token);
        updateUser = new UserUpdateOperations(token);
        getUser = new UserGetOperations(token);
    }
    public static RestWrapper loginAs(String login, String password) {
        return new RestWrapper(
                given().baseUri(BASE_URL).basePath("login/")
                .contentType(ContentType.JSON)
                .body(new UserLogin(login, password))
                .post()
                .then()
                .extract().jsonPath().get("token"));
    }
}
