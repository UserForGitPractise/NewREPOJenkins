package rest.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matcher;

import static org.hamcrest.Matchers.*;

public abstract class BaseTest {
    protected static final String BASE_URL = "https://reqres.in/api";
    protected RequestSpecification REQ_SPEC;
    protected ResponseSpecification RES_SPEC;

    protected abstract String getPath();
    public BaseTest(String token) {
        RES_SPEC = new ResponseSpecBuilder()
                .expectStatusCode(Integer.parseInt(String.valueOf(startsWith("20"))))
                .expectBody(notNullValue())
                .log(LogDetail.ALL)
                .build();
        REQ_SPEC = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setBasePath(getPath())
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .addHeader("Authorization", "Bearer " + token)
                .build();
    }
}
