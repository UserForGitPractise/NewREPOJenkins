package utils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.specification.RequestSpecification;
import pojos.*;
import steps.UserSteps;

import java.util.List;

import static io.restassured.RestAssured.given;
import static utils.UserGenerator.createSimpleUser;

public class RestWrapper {
    private static final String BASE_URL = "https://reqres.in/api/";
    private static RequestSpecification REQ_SPEC;
    private Cookies cookie;

    private RestWrapper(Cookies cookie) {
        this.cookie = cookie;
        REQ_SPEC = new RequestSpecBuilder().setBaseUri("https://reqres.in/api/")
                .setContentType(ContentType.JSON)

                .addCookies(cookie)
                .build();
    }

    public static RestWrapper loginAs(String login, String password) {
        Cookies cookies = given().baseUri("https://reqres.in/api/").basePath("login/").contentType(ContentType.JSON)
                .body(new UserLogin(login, password))
                .when().post()
                .getDetailedCookies();

        return new RestWrapper(cookies);
    }

    public CreateUserResponse createUser() {
        CreateUserRequest rq = createSimpleUser();

        CreateUserResponse rs = RestAssured.given().spec(REQ_SPEC)
                .basePath("users/")
                .body(rq)
                .when()
                .post()
                .then()
                .log()
                .all()
                .statusCode(201)
                .extract()
                .body()
                .as(CreateUserResponse.class);
        return rs;
    }

    public List<Users> getUsers() {
        return RestAssured.given().log().body().spec(REQ_SPEC)
                .basePath("users")
                .when()
                .get()

                .then()
                //.log().body()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("data", Users.class);
    }

    public Users getSingleUser(){
        return given().spec(REQ_SPEC).basePath("users/2")
                .when()
                .get()
                .then()
                .extract()
                .jsonPath()
                .getObject("data", Users.class);
    }
    public UpdateUserResponce updateUser(){
        UpdateUserRequest ur = UserGenerator.updateSimpleUser();
        return  given().spec(REQ_SPEC)
                .basePath("users/2")
                .body(ur)
                .when().put().then().extract().as(UpdateUserResponce.class);
    }

    public UpdateUserResponce patchUser(){
        UpdateUserRequest ur = UserGenerator.patchSimpleUser();
        return  given().spec(REQ_SPEC)
                .basePath("users/2")
                .body(ur)
                .when().patch().then().extract().as(UpdateUserResponce.class);
    }
    public void deleteUser(){
        given().spec(REQ_SPEC).basePath("users/2").when().delete().then().statusCode(204);
    }
}
