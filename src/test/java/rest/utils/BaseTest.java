package rest.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public abstract class BaseTest {
    protected static final String BASE_URL = "https://reqres.in/api";
    protected RequestSpecification REQ_SPEC;

    protected abstract String getPath();
    public BaseTest(String token) {
        REQ_SPEC = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setBasePath(getPath())
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + token)
                .build();
    }
}
