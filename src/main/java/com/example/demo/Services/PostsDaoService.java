package com.example.demo.Services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.Entity.Posts;
import com.example.demo.Entity.PostsRepository;

@Component
public class PostsDaoService {
    private PostsRepository repository;

    public PostsDaoService(PostsRepository repository) {
        this.repository = repository;
    }

    public Posts getPost(int id) {
        return repository.findById(id).orElse(null);
    }

    public List<Posts> getAllPosts() {
        return repository.findAll();
    }

    public void addPost(Posts post) {
        repository.save(post);
    }

    public void updatePost(int postId, Posts post) {
        Posts existingPost = repository.findById(postId).orElse(null);
        if (existingPost != null) {
            existingPost.setTitle(post.getTitle());
            existingPost.setContent(post.getContent());
        }
    }   

    public void deletePost(int postId) {
        repository.deleteById(postId);
    }
}
