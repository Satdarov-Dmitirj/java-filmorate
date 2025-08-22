package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {

    private final Map<Integer, Film> films = new HashMap<>();
    private int nextId = 1;

    @GetMapping
    public Collection<Film> getAll() {
        return films.values();
    }

    @PostMapping
    public Film create(@Valid @RequestBody Film film) {
        film.setId(nextId++);
        films.put(film.getId(), film);

        log.info("Создан фильм: {}", film);
        return film;
    }

    @PutMapping
    public Film update(@Valid @RequestBody Film film) {
        if (!films.containsKey(film.getId())) {
            log.warn("Попытка обновления несуществующего фильма с id={}", film.getId());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Фильм с id=" + film.getId() + " не найден");
        }

        films.put(film.getId(), film);
        log.info("Обновлён фильм: {}", film);
        return film;
    }
}
