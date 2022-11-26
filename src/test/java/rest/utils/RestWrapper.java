package rest.utils;

import io.restassured.http.ContentType;
import rest.pojos.*;
import rest.utils.UserOperations.UserCreateOperations;
import rest.utils.UserOperations.UserGetOperations;
import rest.utils.UserOperations.SingleUserOperations;

import static io.restassured.RestAssured.given;

public class RestWrapper extends BaseTest {
    public UserCreateOperations createUser;
    public SingleUserOperations singleUserOperations;
    public UserGetOperations getUser;
    @Override
    public String getPath() {
        return "";
    }
    private RestWrapper(String token) {
        super(token);
        createUser = new UserCreateOperations(token);
        singleUserOperations = new SingleUserOperations(token);
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
