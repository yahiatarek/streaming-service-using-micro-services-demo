package com.example.demo.Controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.Entity.Posts;
import com.example.demo.Entity.User;
import com.example.demo.ExceptionHandlers.UserNotFoundException;
import com.example.demo.Services.UserDaoService;

import jakarta.validation.Valid;

@RestController
public class UserResource {
    private UserDaoService userDaoService;
    
    public UserResource(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }
    
    @GetMapping("/users/{id}")
    public EntityModel<User> getUser(@PathVariable int id) {
        User user = userDaoService.getUser(id);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());
        entityModel.add(linkTo.withRel("all-users"));
        return entityModel;
    }
    
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userDaoService.getAllUsers();
    }


    @PostMapping("/users")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        userDaoService.addUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(location).body(user);
    }

    @PatchMapping("/users/{id}")
    public void updateUser(@PathVariable int id, @RequestBody User user) {
        userDaoService.updateUser(id, user);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        User user = userDaoService.getUser(id);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        userDaoService.deleteUser(id);
        return ResponseEntity.ok("user with id " + id + " deleted");
    }

    @GetMapping("/users/{id}/posts")
    public List<Posts> getUserPosts(@PathVariable int id) {
        User user = userDaoService.getUser(id);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        return user.getPosts();
    }
}
