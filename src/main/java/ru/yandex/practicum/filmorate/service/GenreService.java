package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Genre;

import java.util.*;

@Service
public class GenreService {

    private final Map<Integer, Genre> genreMap = new HashMap<>();

    public GenreService() {
        genreMap.put(1, new Genre(1, "Комедия"));
        genreMap.put(2, new Genre(2, "Драма"));
        genreMap.put(3, new Genre(3, "Мультфильм"));
        genreMap.put(4, new Genre(4, "Триллер"));
        genreMap.put(5, new Genre(5, "Документальный"));
        genreMap.put(6, new Genre(6, "Боевик"));
    }

    public Genre getGenreById(int id) {
        if (!genreMap.containsKey(id)) {
            throw new NoSuchElementException("No static resource genres/" + id + ".");
        }
        return genreMap.get(id);
    }

    public Collection<Genre> getAllGenres() {
        return genreMap.values();
    }
}
