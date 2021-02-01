package kma.topic2.junit.validation;

import kma.topic2.junit.exceptions.ConstraintViolationException;
import kma.topic2.junit.exceptions.LoginExistsException;
import kma.topic2.junit.model.NewUser;
import kma.topic2.junit.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UserValidatorTest {

    private static final String FULL_NAME = "Sub Zero";
    private static final String LOGIN = "shrek3000";
    private static final String VALID_PASSWORD = "12345";

    private static final String EMPTY_PASSWORD = "";
    private static final String SHORT_PASSWORD = "12";
    private static final String LONG_PASSWORD = "123456789";
    private static final String NOT_MATCHING_REGEX_PASSWORD = "іівцфцфцвфц2у23.є\\.хї";

    @InjectMocks
    private UserValidator userValidator;

    @Mock
    private UserRepository userRepository;


    /*
     * Workaround to prevent `isLoginExists` freak out in tests due to the fact that the same login is used in every test
     */
    @BeforeEach
    void setUp() {
        Mockito.when(userRepository.isLoginExists(LOGIN)).thenReturn(false);
    }


    /*
     * New user should pass the validation and no errors should be thrown
     */
    @Test
    void shouldNotThrowErrorsOnNewUser() {
        NewUser user = NewUser.builder()
                .fullName(FULL_NAME)
                .login(LOGIN)
                .password(VALID_PASSWORD)
                .build();

        assertDoesNotThrow(() -> userValidator.validateNewUser(user));
    }


    /*
     * UserValidator should throw LoginExistsException when validating existing user
     */
    @Test
    void shouldThrowLoginExistsExceptionOnExistingUser() {
        // Override while from the setUp, because this test should definitely test such behaviour
        Mockito.when(userRepository.isLoginExists(LOGIN)).thenReturn(true);

        NewUser user = NewUser.builder()
                .fullName(FULL_NAME)
                .login(LOGIN)
                .password(VALID_PASSWORD)
                .build();

        LoginExistsException exception = assertThrows(LoginExistsException.class, () -> userValidator.validateNewUser(user));

        checkExceptionMessage(exception, String.format("Login %s already taken", user.getLogin()));
    }


    /*
     * UserValidator should throw ConstraintViolationException when password is too short, too long, is empty
     * or when does not match regex
     */
    @Test
    void shouldThrowConstraintViolationException() {
        // empty password
        NewUser emptyPasswordUser = NewUser.builder()
                .fullName(FULL_NAME)
                .login(LOGIN)
                .password(EMPTY_PASSWORD)
                .build();

        ConstraintViolationException exception1 = assertThrows(ConstraintViolationException.class, () -> userValidator.validateNewUser(emptyPasswordUser));

        checkExceptionMessage(exception1, "You have errors in you object");

        // short password
        NewUser shortPasswordUser = NewUser.builder()
                .fullName(FULL_NAME)
                .login(LOGIN)
                .password(SHORT_PASSWORD)
                .build();

        ConstraintViolationException exception2 = assertThrows(ConstraintViolationException.class, () -> userValidator.validateNewUser(shortPasswordUser));

        checkExceptionMessage(exception2, "You have errors in you object");

        // long password
        NewUser longPasswordUser = NewUser.builder()
                .fullName(FULL_NAME)
                .login(LOGIN)
                .password(LONG_PASSWORD)
                .build();

        ConstraintViolationException exception3 = assertThrows(ConstraintViolationException.class, () -> userValidator.validateNewUser(longPasswordUser));

        checkExceptionMessage(exception3, "You have errors in you object");

        // password does not match the regex
        NewUser notMatchingRegexPasswordUser = NewUser.builder()
                .fullName(FULL_NAME)
                .login(LOGIN)
                .password(NOT_MATCHING_REGEX_PASSWORD)
                .build();

        ConstraintViolationException exception4 = assertThrows(ConstraintViolationException.class, () -> userValidator.validateNewUser(notMatchingRegexPasswordUser));

        checkExceptionMessage(exception4, "You have errors in you object");
    }

    private void checkExceptionMessage(RuntimeException exception, String message) {
        assertThat(exception.getMessage()).isEqualTo(message);
    }
}
