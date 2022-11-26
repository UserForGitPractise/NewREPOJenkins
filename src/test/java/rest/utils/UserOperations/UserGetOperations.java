package rest.utils.UserOperations;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import rest.pojos.Users;
import rest.utils.BaseTest;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserGetOperations extends BaseTest {
    public UserGetOperations(String token) {
        super(token);
    }
    @Override
    public String getPath() {
        return "/users";
    }
    @Step("Get user list")
    public List<Users> getUsers() {
        return RestAssured.given().spec(REQ_SPEC)
                .log()
                .all()
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("data", Users.class);
    }
}
