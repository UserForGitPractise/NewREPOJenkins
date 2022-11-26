package rest.utils.UserOperations;

import rest.pojos.UpdateUserRequest;
import rest.pojos.UpdateUserResponce;
import rest.pojos.Users;
import rest.utils.BaseTest;

import static io.restassured.RestAssured.given;

public class SingleUserOperations extends BaseTest {
    public SingleUserOperations(String token) {
        super(token);
    }

    @Override
    public String getPath() {
        return "/users/";
    }

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

    public UpdateUserResponce updateUser(UpdateUserRequest ur) {
        return given().spec(REQ_SPEC)
                .basePath(getPath() + 2)
                .body(ur)
                .put()
                .then()
                .spec(RES_SPEC)
                .extract()
                .as(UpdateUserResponce.class);
    }

    public UpdateUserResponce patchUser(UpdateUserRequest ur) {
        return given().spec(REQ_SPEC)
                .basePath(getPath() + 2)
                .body(ur)
                .patch()
                .then()
                .spec(RES_SPEC)
                .extract()
                .as(UpdateUserResponce.class);
    }

    public void deleteUser() {
        given()
                .spec(REQ_SPEC)
                .basePath(getPath() + 2)
                .delete()
                .then()
                .spec(RES_SPEC)
                .statusCode(204);
    }
}
