package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Friendship;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.*;
import java.util.stream.Collectors;

import static ru.yandex.practicum.filmorate.model.Friendship.FriendshipStatus.CONFIRMED;
import static ru.yandex.practicum.filmorate.model.Friendship.FriendshipStatus.UNCONFIRMED;

@Service
public class UserService {

    private final UserStorage userStorage;

    public UserService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public void addFriend(int userId, int friendId) {
        User user = userStorage.getUserById(userId);
        User friend = userStorage.getUserById(friendId);

        if (user == null || friend == null)
            throw new NoSuchElementException("Пользователь не найден");

        user.getFriends().add(new Friendship(userId, friendId, UNCONFIRMED));
        friend.getFriends().add(new Friendship(friendId, userId, UNCONFIRMED));
    }

    public void removeFriend(int userId, int friendId) {
        User user = userStorage.getUserById(userId);
        User friend = userStorage.getUserById(friendId);

        user.getFriends().removeIf(f -> f.getFriendId() == friendId);
        friend.getFriends().removeIf(f -> f.getFriendId() == userId);
    }

    public List<User> getFriends(int userId) {
        User user = userStorage.getUserById(userId);
        return user.getFriends().stream()
                .map(f -> userStorage.getUserById(f.getFriendId()))
                .collect(Collectors.toList());
    }

    public List<User> getCommonFriends(int userId, int otherId) {
        User user = userStorage.getUserById(userId);
        User other = userStorage.getUserById(otherId);

        Set<Integer> commonIds = user.getFriends().stream()
                .map(Friendship::getFriendId)
                .collect(Collectors.toSet());

        commonIds.retainAll(other.getFriends().stream()
                .map(Friendship::getFriendId)
                .collect(Collectors.toSet()));

        return commonIds.stream()
                .map(userStorage::getUserById)
                .collect(Collectors.toList());
    }

    public Collection<User> getAllUsers() {
        return userStorage.getAllUsers();
    }

    public User addUser(User user) {
        return userStorage.addUser(user);
    }

    public User updateUser(User user) {
        return userStorage.updateUser(user);
    }

    public User getUserById(int id) {
        return userStorage.getUserById(id);
    }
}
