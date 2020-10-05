Tests for `/register` and `/login` controllers of Socks microservice app.

###NOTE
For RegistrationTest.verifyEmptyPasswordIsNotAllowed() test is written to assert HTTP 500 Internal Server Error.
However, actual `/register` controller allows us to register with an empty password, which I don't think is expected. At
least I haven't found any information in API doc,that this field is not required. So in production case - bug would be
raised in such case.

For LoginTest.verifyRegisteredUserIsAbleToLogin() response does not contain documented body. Result would have been clarified,
if it had been a production case and.
