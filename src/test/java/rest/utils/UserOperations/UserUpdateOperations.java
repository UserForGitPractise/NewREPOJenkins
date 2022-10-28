package rest.utils.UserOperations;

import rest.pojos.UpdateUserRequest;
import rest.pojos.UpdateUserResponce;
import rest.utils.BaseTest;
import rest.utils.UserGenerator;

import static io.restassured.RestAssured.given;

public class UserUpdateOperations extends BaseTest {
    public UserUpdateOperations(String token) {
        super(token);
    }
    @Override
    public String getPath() {
        return "/users/2";
    }
    public UpdateUserResponce updateUser(UpdateUserRequest ur) {
        return given().spec(REQ_SPEC)
                //.basePath("users/2")
                .body(ur)
                .when().put().then().extract().as(UpdateUserResponce.class);
    }
    public UpdateUserResponce patchUser(UpdateUserRequest ur) {
        return given().spec(REQ_SPEC)
                //.basePath("users/2")
                .body(ur)
                .when().patch()
                .then().extract().as(UpdateUserResponce.class);
    }
    public void deleteUser() {
        given()
                .spec(REQ_SPEC)
                .when()
                .delete()
                .then()
                .statusCode(204);
    }
}
