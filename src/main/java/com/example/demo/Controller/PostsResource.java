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
import com.example.demo.ExceptionHandlers.PostNotFoundException;
import com.example.demo.ExceptionHandlers.UserNotFoundException;
import com.example.demo.Services.PostsDaoService;
import com.example.demo.Services.UserDaoService;

import jakarta.validation.Valid;

@RestController
public class PostsResource {
    private PostsDaoService postsDaoService;
    private UserDaoService userDaoService;
    
    public PostsResource(PostsDaoService postsDaoService, UserDaoService userDaoService) {
        this.postsDaoService = postsDaoService;
        this.userDaoService = userDaoService;
    }
    
    @GetMapping("/posts/{id}")
    public EntityModel<Posts> getPost(@PathVariable int id) {
        Posts post = postsDaoService.getPost(id);
        if (post == null) {
            throw new PostNotFoundException("Post not found");
        }

        EntityModel<Posts> entityModel = EntityModel.of(post);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllPosts());
        entityModel.add(linkTo.withRel("all-posts"));
        return entityModel;
    }
    
    @GetMapping("/posts")
    public List<Posts> getAllPosts() {
        return postsDaoService.getAllPosts();
    }


    @PostMapping("/posts/user/{userId}")
    public ResponseEntity<Posts> addPost(@Valid @RequestBody Posts post, @PathVariable int userId) {
        User user = userDaoService.getUser(userId);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        post.setUser(user);
        postsDaoService.addPost(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(location).body(post);
    }

    @PatchMapping("/posts/{id}")
    public void updatePost(@PathVariable int id, @RequestBody Posts post) {
        postsDaoService.updatePost(id, post);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<String> deletePost(@PathVariable int id) {
        Posts post = postsDaoService.getPost(id);
        if (post == null) {
            throw new PostNotFoundException("Post not found");
        }
        postsDaoService.deletePost(id);
        return ResponseEntity.ok("post with id " + id + " deleted");
    }
}
