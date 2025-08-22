package ru.yandex.practicum.filmorate.validator;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class FilmValidatorTest {

    @Test
    void testEmptyNameThrowsException() {
        Film film = new Film();
        film.setName(""); // пустое название
        film.setDescription("Описание фильма");
        film.setReleaseDate(LocalDate.of(2000, 1, 1));
        film.setDuration(120);

        assertThrows(ValidationException.class, () -> FilmValidator.validate(film));
    }

    @Test
    void testTooLongDescriptionThrowsException() {
        Film film = new Film();
        film.setName("Фильм");
        film.setDescription("A".repeat(201));
        film.setReleaseDate(LocalDate.of(2000, 1, 1));
        film.setDuration(120);

        assertThrows(ValidationException.class, () -> FilmValidator.validate(film));
    }

    @Test
    void testReleaseDateBefore18951228ThrowsException() {
        Film film = new Film();
        film.setName("Фильм");
        film.setDescription("Описание фильма");
        film.setReleaseDate(LocalDate.of(1800, 1, 1));
        film.setDuration(120);

        assertThrows(ValidationException.class, () -> FilmValidator.validate(film));
    }

    @Test
    void testNonPositiveDurationThrowsException() {
        Film film = new Film();
        film.setName("Фильм");
        film.setDescription("Описание фильма");
        film.setReleaseDate(LocalDate.of(2000, 1, 1));
        film.setDuration(0);

        assertThrows(ValidationException.class, () -> FilmValidator.validate(film));
    }

    @Test
    void testValidFilmDoesNotThrow() {
        Film film = new Film();
        film.setName("Фильм");
        film.setDescription("Описание фильма");
        film.setReleaseDate(LocalDate.of(2000, 1, 1));
        film.setDuration(120);


        FilmValidator.validate(film);
    }
}
