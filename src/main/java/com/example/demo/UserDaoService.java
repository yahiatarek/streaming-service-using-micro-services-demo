package com.example.demo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    public User getUser(int id) {
        Predicate<User> predicate = user -> user.getId() == id;
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    static {
        users.add(new User(1, "Yahia", "Tarsek", LocalDate.of(1990, 1, 1)));
        users.add(new User(2, "Younes", "Tarek", LocalDate.of(1990, 1, 2)));
        users.add(new User(3, "Yassine", "Tarek", LocalDate.of(1990, 1, 3)));
    }

    public List<User> getAllUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void updateUser(int userId, User user) {
        User existingUser = users.stream().filter(u -> u.getId() == userId).findFirst().orElse(null);
        if (existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setLastName(user.getLastName());
            existingUser.setBirthDate(user.getBirthDate());
        }
    }   

    public void deleteUser(int userId) {
        users.removeIf(user -> user.getId() == userId);
    }
}
