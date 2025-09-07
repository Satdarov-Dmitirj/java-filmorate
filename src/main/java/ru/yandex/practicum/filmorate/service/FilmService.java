package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FilmService {

    private final FilmStorage filmStorage;
    private final UserStorage userStorage;

    public FilmService(FilmStorage filmStorage, UserStorage userStorage) {
        this.filmStorage = filmStorage;
        this.userStorage = userStorage;
    }

    public void addLike(int filmId, int userId) {
        if (!filmStorage.getAllFilms().stream().anyMatch(f -> f.getId() == filmId)) {
            throw new NoSuchElementException("Фильм с id=" + filmId + " не найден");
        }
        if (!userStorage.getAllUsers().stream().anyMatch(u -> u.getId() == userId)) {
            throw new NoSuchElementException("Пользователь с id=" + userId + " не найден");
        }

        Film film = filmStorage.getFilmById(filmId);
        film.getLikes().add(userId);
    }

    public void removeLike(int filmId, int userId) {
        if (!filmStorage.getAllFilms().stream().anyMatch(f -> f.getId() == filmId)) {
            throw new NoSuchElementException("Фильм с id=" + filmId + " не найден");
        }
        if (!userStorage.getAllUsers().stream().anyMatch(u -> u.getId() == userId)) {
            throw new NoSuchElementException("Пользователь с id=" + userId + " не найден");
        }

        Film film = filmStorage.getFilmById(filmId);
        film.getLikes().remove(userId);
    }

    public List<Film> getPopularFilms(int count) {
        return filmStorage.getAllFilms().stream()
                .sorted((f1, f2) -> Integer.compare(f2.getLikes().size(), f1.getLikes().size()))
                .limit(count)
                .collect(Collectors.toList());
    }

    public Collection<Film> getAllFilms() {
        return filmStorage.getAllFilms();
    }

    public Film addFilm(Film film) {
        return filmStorage.addFilm(film);
    }

    public Film updateFilm(Film film) {
        return filmStorage.updateFilm(film);
    }

    public Film getFilmById(int id) {
        return filmStorage.getFilmById(id);
    }
}
