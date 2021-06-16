package steps;

import io.qameta.allure.Step;
import models.UserDataModel;
import models.UserRegistrationModel;
import specs.Specs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

public class Steps {

    @Step("Get user data with id {id}")
    public UserDataModel getUserDataWithId(int id){
        return given(Specs.requestSpec)
                .when().get("/users/"+id)
                .then().spec(Specs.responseSpec)
                .log().body()
                .extract().as(UserDataModel.class);
    }

    @Step("Check max user id from page {page} equals maxId")
    public void verifyMaxIdFromPage(int maxId, int page) {
        given(Specs.requestSpec)
                .queryParam("page", page)
                .when().get("/users")
                .then().spec(Specs.responseSpec)
                .log().body()
                .body("data.collect{it.id}.max()", is(maxId));
    }

    @Step("Get the list of users from page {page} contains last name {lastName}")
    public void theListFromPageContainsLastName(int page, String lastName) {
        given(Specs.requestSpec).queryParam("page", page)
                .when().get("/users")
                .then().spec(Specs.responseSpec)
                .log().body()
                .body("data.findAll{it.last_name}.last_name", hasItem(lastName));
    }

    @Step("Get token after the new user registered successfully with email {email} and password {password}")
    public String getRegistrationToken(String email, String password) {
        return given().spec(Specs.requestSpec)
                .body(UserRegistrationModel.builder().email(email).password(password).build())
                .log().uri()
                .when().post("/register")
                .then().spec(Specs.responseSpec)
                .log().body()
                .extract()
                .path("token");
    }
}
