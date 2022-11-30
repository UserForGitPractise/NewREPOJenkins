package rest.utils.UserOperations;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import rest.pojos.Users;
import rest.utils.BaseTest;

import java.util.List;

public class UserGetOperations extends BaseTest {
    public UserGetOperations(String token) {
        super(token);
    }

    @Override
    public String getPath() {
        return "/users";
    }

    @Step("Send get request to get all existing users and verify their params")
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
