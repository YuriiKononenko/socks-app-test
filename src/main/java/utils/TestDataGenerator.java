package utils;

import api.model.register.RegisterModel;

import java.util.Date;

public final class TestDataGenerator {

    private TestDataGenerator() {
    }

    public static RegisterModel generateUserInformation() {
        long time = new Date().getTime();
        long threadId = Thread.currentThread().getId();

        String userName = "test" + time + threadId;
        String password = "Password1";
        String email = userName + "@i.ua";

        return new RegisterModel(userName, password, email);
    }

}
