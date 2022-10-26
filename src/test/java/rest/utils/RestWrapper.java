package rest.utils;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.specification.RequestSpecification;
import rest.pojos.*;
import rest.pojos.*;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestWrapper {
    private static final String BASE_URL = "https://reqres.in/api/";
    private static RequestSpecification REQ_SPEC;
    private String token;

    private RestWrapper(String token) {
        this.token = token;
        REQ_SPEC = new RequestSpecBuilder().setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + token)
                .build();
    }

    public static RestWrapper loginAs(String login, String password) {
        String token = given().baseUri(BASE_URL).basePath("login/").contentType(ContentType.JSON)
                .body(new UserLogin(login, password))
                .when().post()
                .then().extract().jsonPath().get("token");
        return new RestWrapper(token);
    }

    @Step("Create User")
    public CreateUserResponse createUser( CreateUserRequest rq) {
        return RestAssured.given().spec(REQ_SPEC)
                .basePath("users/")
                .body(rq)
                .when()
                .post()
                .then()
                .log()
                .all()
                .statusCode(201)
                .extract()
                .as(CreateUserResponse.class);
    }

    @Step("Get user list")
    public List<Users> getUsers() {
        return RestAssured.given().log().body().spec(REQ_SPEC)
                .basePath("users")
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("data", Users.class);
    }

    public Users getSingleUser() {
        return given().spec(REQ_SPEC).basePath("users/2")
                .when()
                .get()
                .then()
                .extract()
                .jsonPath()
                .getObject("data", Users.class);
    }

    public UpdateUserResponce updateUser() {
        UpdateUserRequest ur = UserGenerator.updateSimpleUser();
        return given().spec(REQ_SPEC)
                .basePath("users/2")
                .body(ur)
                .when().put().then().extract().as(UpdateUserResponce.class);
    }

    public UpdateUserResponce patchUser() {
        UpdateUserRequest ur = UserGenerator.patchSimpleUser();
        return given().spec(REQ_SPEC)
                .basePath("users/2")
                .body(ur)
                .when().patch()
                .then().extract().as(UpdateUserResponce.class);
    }

    public void deleteUser() {
        given()
                .spec(REQ_SPEC).basePath("users/2")
                .when()
                .delete()
                .then()
                .statusCode(204);
    }
}
