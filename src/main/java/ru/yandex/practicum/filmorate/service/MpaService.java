package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.util.*;

@Service
public class MpaService {

    private final Map<Integer, Mpa> mpaMap = new HashMap<>();

    public MpaService() {
        mpaMap.put(1, new Mpa(1, "G"));
        mpaMap.put(2, new Mpa(2, "PG"));
        mpaMap.put(3, new Mpa(3, "PG-13"));
        mpaMap.put(4, new Mpa(4, "R"));
        mpaMap.put(5, new Mpa(5, "NC-17"));
    }

    public Mpa getMpaById(int id) {
        if (!mpaMap.containsKey(id)) {
            throw new NoSuchElementException("No static resource mpa/" + id + ".");
        }
        return mpaMap.get(id);
    }

    public Collection<Mpa> getAllMpa() {
        return mpaMap.values();
    }
}
