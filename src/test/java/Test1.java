import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class Test1 {

    @Test
    public static void test1 (){
        RestAssured.given().baseUri("https://reqres.in/api/users?page=2").when().get().then().statusCode(200);

    }

}
