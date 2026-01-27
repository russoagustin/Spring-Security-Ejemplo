package com.lowlayer.app.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.lowlayer.app.dto.PostRequestDTO;
import com.lowlayer.app.dto.PostResponseDTO;
import com.lowlayer.app.services.interfaces.IpostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private final IpostService service;

    public PostController(IpostService service){
        this.service = service;
    }

    @GetMapping("/{id}")
    ResponseEntity<PostResponseDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok(service.findPostById(id));
    }

    @PostMapping
    ResponseEntity<?> createPost(@Valid @RequestBody PostRequestDTO post){
        PostResponseDTO createdPost = service.createPost(post);
        URI uri = UriComponentsBuilder.fromUriString("/api/post/{id}").buildAndExpand(createdPost.id()).toUri();        
        return ResponseEntity.created(uri).body(createdPost);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updatePost(@PathVariable Integer id,@Valid @RequestBody PostRequestDTO post){
        return ResponseEntity.ok(service.updatePost(id, post));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletePost(@PathVariable Integer id){
        service.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
