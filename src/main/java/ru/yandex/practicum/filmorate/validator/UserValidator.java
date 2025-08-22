package ru.yandex.practicum.filmorate.validator;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import java.time.LocalDate;

@Slf4j
public class UserValidator {

    public static void validate(User user) {
        if (user.getEmail() == null || !user.getEmail().contains("@")) {
            log.warn("Ошибка валидации email: {}", user.getEmail());
            throw new ValidationException("Email должен содержать символ @ и не должен быть пустым");
        }

        if (user.getLogin() == null || user.getLogin().isBlank() || user.getLogin().contains(" ")) {
            log.warn("Ошибка валидации логина: {}", user.getLogin());
            throw new ValidationException("Логин не может быть пустым и содержать пробелы");
        }

        if (user.getName() == null || user.getName().isBlank()) {
            log.info("Имя пользователя пустое, используется логин: {}", user.getLogin());
            user.setName(user.getLogin());
        }

        if (user.getBirthday() != null && user.getBirthday().isAfter(LocalDate.now())) {
            log.warn("Ошибка валидации даты рождения: {}", user.getBirthday());
            throw new ValidationException("Некорректная дата рождения");
        }
    }
}
