package rest.utils.UserOperations;

import io.qameta.allure.Step;
import rest.pojos.UpdateOrPatchUserResponse;
import rest.pojos.UpdateUserRequest;
import rest.pojos.Users;
import rest.utils.BaseTest;

import static io.restassured.RestAssured.given;

public class SingleUserOperations extends BaseTest {
    public SingleUserOperations(String token) {
        super(token);
    }

    private static final int userStubId = 2;

    @Override
    public String getPath() {
        return "/users/";
    }

    @Step("Send get request to get {0} user")
    public Users getSingleUser(int userID) {
        return given().spec(REQ_SPEC)
                .basePath(getPath() + userID)
                .when()
                .get()
                .then()
                .spec(RES_SPEC)
                .extract()
                .jsonPath()
                .getObject("data", Users.class);
    }

    @Step("Update user and verify params of updated user {0}")
    public UpdateOrPatchUserResponse updateUser(UpdateUserRequest ur) {
        return given().spec(REQ_SPEC)
                .basePath(getPath() + userStubId)
                .body(ur)
                .put()
                .then()
                .spec(RES_SPEC)
                .extract()
                .as(UpdateOrPatchUserResponse.class);
    }

    @Step("Update user skills and verify params of updated user {0}")
    public UpdateOrPatchUserResponse patchUser(UpdateUserRequest ur) {
        return given().spec(REQ_SPEC)
                .basePath(getPath() + userStubId)
                .body(ur)
                .patch()
                .then()
                .spec(RES_SPEC)
                .extract()
                .as(UpdateOrPatchUserResponse.class);
    }

    @Step("Delete user and verify status code")
    public void deleteUser() {
        given()
                .spec(REQ_SPEC)
                .basePath(getPath() + userStubId)
                .delete()
                .then()
                .spec(RES_SPEC)
                .statusCode(204);
    }
}
