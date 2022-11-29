package rest.utils.UserOperations;

import io.restassured.RestAssured;
import rest.pojos.CreateUserRequest;
import rest.pojos.CreateUserResponse;
import rest.utils.BaseTest;

public class UserCreateOperations extends BaseTest {
    public UserCreateOperations(String token) {
        super(token);
    }

    @Override
    public String getPath() {
        return "/users";
    }

    public CreateUserResponse createUser(CreateUserRequest rq) {
        return RestAssured.given().spec(REQ_SPEC)
                .body(rq)
                .log()
                .all()
                .when()
                .post()
                .then()
                .log()
                .all()
                .statusCode(201)
                .extract()
                .as(CreateUserResponse.class);
    }


}
