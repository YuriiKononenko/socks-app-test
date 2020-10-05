package service.user.register;

import api.model.register.RegisterModel;
import api.service.user.register.RegistrationService;
import api.service.user.register.RegistrationServiceImpl;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static utils.TestDataGenerator.generateUserInformation;


public class RegistrationTests {

    private final RegistrationService registrationService = new RegistrationServiceImpl();

    @Test
    public void verifySuccessfulResponseBodyContainsNotEmptyId() {
        RegisterModel userInfo = generateUserInformation();

        verifyRegistrationIsOk(userInfo)
                .assertThat()
                .body("id", is(not(blankOrNullString())));
    }

    @Test
    public void verifyEmptyEmailIsAllowed() {
        RegisterModel userInfo = generateUserInformation();
        userInfo.setEmail("");

        verifyRegistrationIsOk(userInfo);
    }

    @Test
    public void verifyUserNameCanOnlyBeUnique() {
        RegisterModel userInfo = generateUserInformation();
        userInfo.setUserName("user");

        verifyRegistrationServerError(userInfo);
    }

    @Test
    public void verifyEmptyPasswordIsNotAllowed() {
        RegisterModel userInfo = generateUserInformation();
        userInfo.setPassword("");

        verifyRegistrationServerError(userInfo);
    }

    @Test
    public void verifyEmptyUserNameIsNotAllowed() {
        RegisterModel userInfo = generateUserInformation();
        userInfo.setUserName("");

        verifyRegistrationServerError(userInfo);
    }

    private void verifyRegistrationServerError(RegisterModel userInfo) {
        registrationService.register(userInfo)
                .then()
                .assertThat()
                .statusCode(SC_INTERNAL_SERVER_ERROR);
    }

    private ValidatableResponse verifyRegistrationIsOk(RegisterModel userInfo) {
        return registrationService.register(userInfo)
                .then()
                .assertThat()
                .statusCode(SC_OK);
    }

}
