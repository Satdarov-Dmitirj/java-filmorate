package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private final Map<Integer, User> users = new HashMap<>();
    private int nextId = 1;

    @GetMapping
    public Collection<User> getAll() {
        return users.values();
    }

    @PostMapping
    public User create(@Valid @RequestBody User user) {
        user.setId(nextId++);
        users.put(user.getId(), user);

        log.info("Создан пользователь: {}", user);
        return user;
    }

    @PutMapping
    public User update(@Valid @RequestBody User user) {
        if (!users.containsKey(user.getId())) {
            log.warn("Попытка обновления несуществующего пользователя с id={}", user.getId());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Пользователь с id=" + user.getId() + " не найден");
        }

        users.put(user.getId(), user);
        log.info("Обновлён пользователь: {}", user);
        return user;
    }
}
