package specs;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;

public class Specs {
    public static RequestSpecification requestSpec = with()
            .baseUri("https://reqres.in")
            .basePath("/api")
            .contentType(ContentType.JSON)
            .filter(new AllureRestAssured())
            .log().uri();

    public static ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();
}
