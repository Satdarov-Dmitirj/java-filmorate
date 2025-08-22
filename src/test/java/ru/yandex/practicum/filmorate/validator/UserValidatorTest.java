package ru.yandex.practicum.filmorate.validator;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserValidatorTest {

    @Test
    void testValidUserPasses() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setLogin("user123");
        user.setName("User");
        user.setBirthday(LocalDate.of(2000, 1, 1));

        UserValidator.validate(user);
    }

    @Test
    void testEmptyEmailThrowsException() {
        User user = new User();
        user.setEmail("");
        user.setLogin("user123");
        user.setName("User");
        user.setBirthday(LocalDate.of(2000, 1, 1));

        assertThrows(ValidationException.class, () -> UserValidator.validate(user));
    }

    @Test
    void testInvalidEmailThrowsException() {
        User user = new User();
        user.setEmail("invalidemail.com");
        user.setLogin("user123");
        user.setName("User");
        user.setBirthday(LocalDate.of(2000, 1, 1));

        assertThrows(ValidationException.class, () -> UserValidator.validate(user));
    }

    @Test
    void testEmptyLoginThrowsException() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setLogin("");
        user.setName("User");
        user.setBirthday(LocalDate.of(2000, 1, 1));

        assertThrows(ValidationException.class, () -> UserValidator.validate(user));
    }

    @Test
    void testLoginWithSpaceThrowsException() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setLogin("user 123");
        user.setName("User");
        user.setBirthday(LocalDate.of(2000, 1, 1));

        assertThrows(ValidationException.class, () -> UserValidator.validate(user));
    }

    @Test
    void testEmptyNameSetsLoginAsName() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setLogin("user123");
        user.setName("");
        user.setBirthday(LocalDate.of(2000, 1, 1));

        UserValidator.validate(user);
        assertEquals("user123", user.getName());
    }

    @Test
    void testBirthdayInFutureThrowsException() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setLogin("user123");
        user.setName("User");
        user.setBirthday(LocalDate.now().plusDays(1));

        assertThrows(ValidationException.class, () -> UserValidator.validate(user));
    }
}
