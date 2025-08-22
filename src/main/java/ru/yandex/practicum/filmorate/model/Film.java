package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class Film {
    private int id;

    @NotBlank(message = "Название фильма не может быть пустым")
    private String name;

    @Size(max = 200, message = "Описание фильма не может быть длиннее 200 символов")
    private String description;

    @NotNull(message = "Дата релиза не может быть null")
    private LocalDate releaseDate;

    @Positive(message = "Продолжительность фильма не может быть меньше нуля")
    private int duration;

    public void validateReleaseDate() {
        LocalDate earliestDate = LocalDate.of(1895, 12, 28);
        if (releaseDate.isBefore(earliestDate)) {
            throw new IllegalArgumentException(
                    "Дата релиза не может быть раньше 28 декабря 1895 года"
            );
        }
    }
}
