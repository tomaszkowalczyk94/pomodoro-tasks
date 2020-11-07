package org.tomaszkowalczyk94.pomodorotasksmanager.core;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RestUtil {

    static RequestSpecification buildRequestSpecification(int port) {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(String.format("http://localhost:%d/", port))
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }

    static <T> T createAndGetResource(RequestSpecification spec, String path, T bodyPayload, Class<T> responseClass) {
        return given()
                .spec(spec)
                .body(bodyPayload)
                .when()
                .post(path)
                .then()
                .statusCode(200)
                .extract().as(responseClass);
    }

    static <T> T getResource(RequestSpecification spec, String location, Class<T> responseClass) {
        return given()
                .spec(spec)
                .when()
                .get(location)
                .then()
                .statusCode(200)
                .extract().as(responseClass);
    }

}
