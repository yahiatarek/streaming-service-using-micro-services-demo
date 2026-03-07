package com.example.demo.Services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.Entity.User;
import com.example.demo.Entity.UserRepository;

@Component
public class UserDaoService {
    private UserRepository repository;

    public UserDaoService(UserRepository repository) {
        this.repository = repository;
    }

    public User getUser(int id) {
        return repository.findById(id).orElse(null);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public void addUser(User user) {
        repository.save(user);
    }

    public void updateUser(int userId, User user) {
        User existingUser = repository.findById(userId).orElse(null);
        if (existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setLastName(user.getLastName());
            existingUser.setBirthDate(user.getBirthDate());
        }
    }   

    public void deleteUser(int userId) {
        repository.deleteById(userId);
    }
}
