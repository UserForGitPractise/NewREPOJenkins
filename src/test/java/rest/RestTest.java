package rest;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class RestTest {

    @Test
    public static void test1 (){
        RestAssured.given().baseUri("https://reqres.in/api/users?page=1").when().get().then().statusCode(200);

    }

}
