package tests;

import models.UserDataModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ReqresInTests extends TestBase{
    private static final int USER_ID = 6;
    private static final String USER_FIRST_NAME = "Tracey";
    private static final int MAX_USER_ID = 6;
    private static final int PAGE_NUMBER = 1;
    private static final int USERS_PAGE_NUMBER = 2;
    private static final String USER_LAST_NAME = "Howell";
    private static final String REGISTRATION_EMAIL = "eve.holt@reqres.in";
    private static final String REGISTRATION_PASSWORD = "pistol";

    @Test
    @DisplayName("Check first name for single user with specific id")
    void UserDataContainsSpecificFirstName() {
        UserDataModel userData = steps.getUserDataWithId(USER_ID);
        assertThat(userData.getUserData().getFirstName()).isEqualTo(USER_FIRST_NAME);
    }

    @Test
    @DisplayName("Chek max user id from specific page")
    public void TheGreatestIdFromPageEqualsMaxId() {
        steps.verifyMaxIdFromPage(MAX_USER_ID, PAGE_NUMBER);
    }

    @Test
    @DisplayName("Check the list of users from current page contains specific last name")
    public void theListFromPageContainsSpecificLastName() {
        steps.theListFromPageContainsLastName(USERS_PAGE_NUMBER, USER_LAST_NAME);
    }

    @Test
    @DisplayName("Check the token after register a new user")
    public void tokenShouldBeAfterRegistration() {
        String token = steps.getRegistrationToken(REGISTRATION_EMAIL, REGISTRATION_PASSWORD);
        assertThat(token).isNotNull();
    }
}
