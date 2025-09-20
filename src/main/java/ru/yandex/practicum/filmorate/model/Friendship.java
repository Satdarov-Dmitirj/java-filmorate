package ru.yandex.practicum.filmorate.model;

import lombok.Data;

@Data
public class Friendship {
    private int userId;
    private int friendId;
    private FriendshipStatus status;

    public enum FriendshipStatus {
        UNCONFIRMED,
        CONFIRMED
    }
}
