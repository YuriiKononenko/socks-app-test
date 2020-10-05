package service.user.login;

import api.service.user.login.LoginService;
import api.service.user.login.LoginServiceImpl;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.JUnit4;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;


public class LoginTests {

    private final LoginService loginService = new LoginServiceImpl();

    @Test
    public void verifyRegisteredUserIsAbleToLogin() {
        loginService.login("user", "password")
                .then()
                .statusCode(SC_OK);
    }

    @Test
    public void verifyResponseContainsLoggedInCookie() {
        String loggedInCookie = loginService.login("user", "password").cookie("logged_in");

        assertThat(loggedInCookie, is(not(blankOrNullString())));
    }

    @Test
    public void verifyUserWithInvalidPasswordIsUnableToLogin() {
        verifyLoginUnauthorized("user", "password1");
    }

    @Test
    public void verifyUserWithEmptyPasswordIsUnableToLogin() {
        verifyLoginUnauthorized("user", "");
    }

    @Test
    public void verifyUserWithInvalidUserNameIsUnableToLogin() {
        verifyLoginUnauthorized("s%3A6SIezfoGNVilgK8bc1Mzy0f57", "password");
    }

    @Test
    public void verifyUserWithEmptyUserNameIsUnableToLogin() {
        verifyLoginUnauthorized("", "password");
    }

    private void verifyLoginUnauthorized(String userName, String password) {
        loginService.login(userName, password)
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }


}
